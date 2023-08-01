package com.stewemetal.takehometemplate.shell.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stewemetal.takehometemplate.shell.database.entity.ExampleDatabaseEntity

@Database(
    entities = [
        ExampleDatabaseEntity::class,
    ],
    version = 1,
)
abstract class TakeHomeTemplateDatabase : RoomDatabase() {
    // Add DAO accessors
}
