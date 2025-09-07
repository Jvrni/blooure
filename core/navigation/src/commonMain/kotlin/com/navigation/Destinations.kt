package com.navigation

import kotlinx.serialization.Serializable

/**
 * Sealed class representing the different navigation destinations within the application.
 *
 * This class serves as a type-safe way to define and manage the various screens or states
 * that the user can navigate to. Each data object within this sealed class represents a
 * distinct destination in the application's navigation graph.
 *
 * By using a sealed class, we ensure that all possible destinations are known at compile time,
 * which allows for exhaustive `when` statements and improved code safety.
 *
 * The `@Serializable` annotation from the kotlinx.serialization library enables these destinations
 * to be easily serialized and deserialized, which is useful for saving navigation state or passing
 * destinations as arguments.
 */
@Serializable
sealed class Destinations {

    @Serializable
    data object Splash : Destinations()

}
