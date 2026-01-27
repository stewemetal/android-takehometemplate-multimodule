package com.stewemetal.takehometemplate.shell.datasource

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.koin.core.annotation.Factory

/**
 * Fake implementation of [SharedPreferencesDataSource] for testing purposes.
 *
 * Uses an in-memory map to simulate SharedPreferences behavior without requiring Android framework.
 */
@Factory
class FakePrefsDataSource : SharedPreferencesDataSource {

    private val storage = mutableMapOf<String, Any?>()
    private val listeners = mutableListOf<Pair<String, () -> Unit>>()

    override fun getString(key: String, defaultValue: String?): String? =
        storage[key] as? String ?: defaultValue

    override fun getStringSet(key: String, defaultValue: Set<String>): Set<String> =
        @Suppress("UNCHECKED_CAST")
        (storage[key] as? Set<String>) ?: defaultValue

    override fun getLong(key: String, defaultValue: Long): Long =
        storage[key] as? Long ?: defaultValue

    override fun getInt(key: String, defaultValue: Int): Int =
        storage[key] as? Int ?: defaultValue

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean =
        storage[key] as? Boolean ?: defaultValue

    override fun getFloat(key: String, defaultValue: Float): Float =
        storage[key] as? Float ?: defaultValue

    override fun <T> getKeyFlow(key: String, provider: (SharedPreferencesDataSource) -> T): Flow<T> =
        callbackFlow {
            val listener: () -> Unit = {
                trySend(provider(this@FakePrefsDataSource))
            }

            listeners.add(key to listener)

            // Emit the initial value
            send(provider(this@FakePrefsDataSource))

            awaitClose {
                listeners.remove(key to listener)
            }
        }

    override fun putString(key: String, value: String?) {
        storage[key] = value
        notifyListeners(key)
    }

    override fun putStringSet(key: String, value: Set<String>) {
        storage[key] = value.toSet() // Create a copy to prevent external modifications
        notifyListeners(key)
    }

    override fun putLong(key: String, value: Long) {
        storage[key] = value
        notifyListeners(key)
    }

    override fun putInt(key: String, value: Int) {
        storage[key] = value
        notifyListeners(key)
    }

    override fun putBoolean(key: String, value: Boolean) {
        storage[key] = value
        notifyListeners(key)
    }

    override fun putFloat(key: String, value: Float) {
        storage[key] = value
        notifyListeners(key)
    }

    override fun clear() {
        val keys = storage.keys.toList()
        storage.clear()
        keys.forEach { key -> notifyListeners(key) }
    }

    override fun hasValue(key: String): Boolean =
        storage.containsKey(key)

    override fun remove(key: String) {
        storage.remove(key)
        notifyListeners(key)
    }

    private fun notifyListeners(key: String) {
        listeners
            .filter { it.first == key }
            .forEach { it.second() }
    }
}
