package com.stewemetal.takehometemplate.shell.datasource

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

/**
 * Production implementation of [SharedPreferencesDataSource] backed by Android's SharedPreferences.
 *
 * @param context Android context for accessing SharedPreferences
 * @param preferencesName Name of the SharedPreferences file
 */
@Suppress("TooManyFunctions")
@Factory
class AndroidPrefsDataSource(
    context: Context,
    @InjectedParam preferencesName: String,
) : SharedPreferencesDataSource {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        preferencesName,
        Context.MODE_PRIVATE
    )

    override fun getString(key: String, defaultValue: String?): String? =
        sharedPreferences.getString(key, defaultValue)

    override fun getStringSet(key: String, defaultValue: Set<String>): Set<String> =
        sharedPreferences.getStringSet(key, defaultValue) ?: defaultValue

    override fun getLong(key: String, defaultValue: Long): Long =
        sharedPreferences.getLong(key, defaultValue)

    override fun getInt(key: String, defaultValue: Int): Int =
        sharedPreferences.getInt(key, defaultValue)

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean =
        sharedPreferences.getBoolean(key, defaultValue)

    override fun getFloat(key: String, defaultValue: Float): Float =
        sharedPreferences.getFloat(key, defaultValue)

    override fun <T> getKeyFlow(key: String, provider: (SharedPreferencesDataSource) -> T): Flow<T> =
        callbackFlow {
            val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, changedKey ->
                if (changedKey == key) {
                    trySend(provider(this@AndroidPrefsDataSource))
                }
            }

            sharedPreferences.registerOnSharedPreferenceChangeListener(listener)

            // Emit the initial value
            send(provider(this@AndroidPrefsDataSource))

            awaitClose {
                sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
            }
        }

    override fun putString(key: String, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun putStringSet(key: String, value: Set<String>) {
        sharedPreferences.edit().putStringSet(key, value).apply()
    }

    override fun putLong(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    override fun putInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    override fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun putFloat(key: String, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    override fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    override fun hasValue(key: String): Boolean =
        sharedPreferences.contains(key)

    override fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}
