package com.stewemetal.takehometemplate.shell.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [],
    version = 1,
)
abstract class TakeHomeTemplateDatabase : RoomDatabase() {
    // Add DAO accessors
}
