package com.data.local

import androidx.room.AutoMigration
import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import androidx.sqlite.execSQL
import com.data.models.BloodPressureDb
import com.data.models.UserDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO


@Database(
    entities = [UserDb::class, BloodPressureDb::class],
    version = 3,
    autoMigrations = [AutoMigration(from = 1, to = 2)],
    exportSchema = true
)
@ConstructedBy(DatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getBloodPressureDao(): BloodPressureDao
}

// Room compiler generates the `actual` implementations
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object DatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

fun getAppDatabase(builder: RoomDatabase.Builder<AppDatabase>): AppDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .addMigrations(MIGRATION_2_3)
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

//internal val MIGRATION_1_2 = object: Migration(1, 2) {
//    override fun migrate(connection: SQLiteConnection) {
//        super.migrate(connection)
//        connection.execSQL("\"CREATE TABLE IF NOT EXISTS blood_pressure (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `systolic` INTEGER NOT NULL, `diastolic` INTEGER NOT NULL, `date` TEXT NOT NULL, `time` TEXT NOT NULL, `userId` INTEGER NOT NULL)\"")
//    }
//}

internal val MIGRATION_2_3 = object: Migration(2, 3) {
    override fun migrate(connection: SQLiteConnection) {
        super.migrate(connection)

        connection.execSQL ("ALTER TABLE blood_pressure DROP COLUMN dateTime TEXT")
        connection.execSQL ("ALTER TABLE blood_pressure ADD COLUMN date TEXT")
        connection.execSQL ("ALTER TABLE blood_pressure ADD COLUMN time TEXT")
    }
}
