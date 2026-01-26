package com.stewemetal.takehometemplate.shell.datasource

import kotlinx.coroutines.flow.Flow

/**
 * Abstraction for SharedPreferences operations.
 *
 * Provides a type-safe interface for storing and retrieving key-value pairs
 * from Android's SharedPreferences.
 */
@Suppress("TooManyFunctions")
interface SharedPreferencesDataSource {

    fun getString(key: String, defaultValue: String?): String?
    fun getStringSet(key: String, defaultValue: Set<String>): Set<String>
    fun getLong(key: String, defaultValue: Long): Long
    fun getInt(key: String, defaultValue: Int): Int
    fun getBoolean(key: String, defaultValue: Boolean): Boolean
    fun getFloat(key: String, defaultValue: Float): Float

    fun <T> getKeyFlow(key: String, provider: (SharedPreferencesDataSource) -> T): Flow<T>

    fun putString(key: String, value: String?)
    fun putStringSet(key: String, value: Set<String>)
    fun putLong(key: String, value: Long)
    fun putInt(key: String, value: Int)
    fun putBoolean(key: String, value: Boolean)
    fun putFloat(key: String, value: Float)

    fun clear()
    fun hasValue(key: String): Boolean
    fun remove(key: String)
}
