package com.stewemetal.takehometemplate.shell.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "example_database_entity",
)
data class ExampleDatabaseEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "example_data")
    val exampleData: String,
)
