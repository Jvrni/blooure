package com.blooure

import com.blooure.features.home.HomeViewModel
import com.blooure.features.home.contract.HomeContract
import com.blooure.features.home.models.HomeItems
import com.domain.bloodPressure.BloodPressureRepository
import com.domain.bloodPressure.GetBloodPressures
import com.domain.models.BloodPressure
import com.domain.models.User
import com.domain.user.GetUsers
import com.domain.user.UserRepository
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val userRepository = mock<UserRepository>(MockMode.strict)
    private val bloodPressureRepository = mock<BloodPressureRepository>(MockMode.strict)

    private val getUsers = GetUsers(userRepository)
    private val getBloodPressures = GetBloodPressures(bloodPressureRepository)

    private val viewModel = HomeViewModel(getUsers, getBloodPressures)

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onStart should update state from request success`() = runTest {
        // Given
        val fakeUsers = listOf(
            User(1, "John Doe"),
            User(2, "Jane Doe")
        )

        val fakeBloodPressures = listOf(
            BloodPressure(1, 120, 80, "2023-01-01", "Rest", userId = 1),
            BloodPressure(2, 130, 85, "2023-01-02", "Rest", userId = 2)
        )

        val expectedHomeItems = listOf(
            HomeItems(fakeUsers[0], fakeBloodPressures[0]),
            HomeItems(fakeUsers[1], fakeBloodPressures[1])
        )

        everySuspend { userRepository.getUsers() } returns flowOf(fakeUsers)
        everySuspend { bloodPressureRepository.getBloodPressures() } returns flowOf(fakeBloodPressures)

        // When
        viewModel.event(HomeContract.Event.OnStart)

        // Then
        val state = viewModel.state.value
        assertEquals(false, state.isBottomSheetVisible)
        assertEquals(expectedHomeItems, state.items)
    }

    @Test
    fun `onStart should update state from request error`() = runTest {
        // Given
        everySuspend { userRepository.getUsers() } returns flowOf(emptyList<User>())
        everySuspend { bloodPressureRepository.getBloodPressures() } returns flowOf(emptyList<BloodPressure>())

        // When
        viewModel.event(HomeContract.Event.OnStart)

        // Then
        val state = viewModel.state.value
        assertEquals(false, state.isBottomSheetVisible)
        assertEquals(emptyList<HomeItems>(), state.items)
    }

    @Test
    fun `onClickAddButton should update state from bottom sheet`() = runTest {
        // Given

        // When
        viewModel.event(HomeContract.Event.ShowBottomSheet(true))

        // Then
        val state = viewModel.state.value
        assertEquals(true, state.isBottomSheetVisible)
    }
}