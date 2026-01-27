package com.stewemetal.takehometemplate.shell.datasource

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AndroidPrefsDataSourceTest {

    private lateinit var dataSource: AndroidPrefsDataSource

    @Before
    fun setup() {
        dataSource = AndroidPrefsDataSource(
            context = ApplicationProvider.getApplicationContext(),
            preferencesName = "test_prefs_${System.currentTimeMillis()}"
        )
        // Clear any existing data
        dataSource.clear()
    }

    @Test
    fun getString_returnsDefaultValue_whenKeyDoesNotExist() {
        val result = dataSource.getString("nonexistent", "default")
        assertEquals("default", result)
    }

    @Test
    fun getString_returnsStoredValue_whenKeyExists() {
        dataSource.putString("key", "value")
        val result = dataSource.getString("key", "default")
        assertEquals("value", result)
    }

    @Test
    fun putString_storesNullValue() {
        dataSource.putString("key", null)
        val result = dataSource.getString("key", "default")
        assertNull(result)
    }

    @Test
    fun getInt_returnsDefaultValue_whenKeyDoesNotExist() {
        val result = dataSource.getInt("nonexistent", 42)
        assertEquals(42, result)
    }

    @Test
    fun getInt_returnsStoredValue_whenKeyExists() {
        dataSource.putInt("key", 123)
        val result = dataSource.getInt("key", 42)
        assertEquals(123, result)
    }

    @Test
    fun getLong_returnsDefaultValue_whenKeyDoesNotExist() {
        val result = dataSource.getLong("nonexistent", 42L)
        assertEquals(42L, result)
    }

    @Test
    fun getLong_returnsStoredValue_whenKeyExists() {
        dataSource.putLong("key", 123L)
        val result = dataSource.getLong("key", 42L)
        assertEquals(123L, result)
    }

    @Test
    fun getFloat_returnsDefaultValue_whenKeyDoesNotExist() {
        val result = dataSource.getFloat("nonexistent", 42.0f)
        assertEquals(42.0f, result)
    }

    @Test
    fun getFloat_returnsStoredValue_whenKeyExists() {
        dataSource.putFloat("key", 123.5f)
        val result = dataSource.getFloat("key", 42.0f)
        assertEquals(123.5f, result)
    }

    @Test
    fun getBoolean_returnsDefaultValue_whenKeyDoesNotExist() {
        val result = dataSource.getBoolean("nonexistent", true)
        assertTrue(result)
    }

    @Test
    fun getBoolean_returnsStoredValue_whenKeyExists() {
        dataSource.putBoolean("key", false)
        val result = dataSource.getBoolean("key", true)
        assertFalse(result)
    }

    @Test
    fun getStringSet_returnsDefaultValue_whenKeyDoesNotExist() {
        val default = setOf("a", "b")
        val result = dataSource.getStringSet("nonexistent", default)
        assertEquals(default, result)
    }

    @Test
    fun getStringSet_returnsStoredValue_whenKeyExists() {
        val value = setOf("x", "y", "z")
        dataSource.putStringSet("key", value)
        val result = dataSource.getStringSet("key", emptySet())
        assertEquals(value, result)
    }

    @Test
    fun hasValue_returnsFalse_whenKeyDoesNotExist() {
        assertFalse(dataSource.hasValue("nonexistent"))
    }

    @Test
    fun hasValue_returnsTrue_whenKeyExists() {
        dataSource.putString("key", "value")
        assertTrue(dataSource.hasValue("key"))
    }

    @Test
    fun remove_deletesStoredValue() {
        dataSource.putString("key", "value")
        assertTrue(dataSource.hasValue("key"))

        dataSource.remove("key")
        assertFalse(dataSource.hasValue("key"))
    }

    @Test
    fun clear_removesAllStoredValues() {
        dataSource.putString("key1", "value1")
        dataSource.putInt("key2", 123)
        dataSource.putBoolean("key3", true)

        assertTrue(dataSource.hasValue("key1"))
        assertTrue(dataSource.hasValue("key2"))
        assertTrue(dataSource.hasValue("key3"))

        dataSource.clear()

        assertFalse(dataSource.hasValue("key1"))
        assertFalse(dataSource.hasValue("key2"))
        assertFalse(dataSource.hasValue("key3"))
    }

    @Test
    fun getKeyFlow_emitsInitialValue() = runTest {
        dataSource.putInt("key", 100)

        val flow = dataSource.getKeyFlow("key") { it.getInt("key", 0) }
        val firstValue = flow.first()

        assertEquals(100, firstValue)
    }

    @Test
    fun getKeyFlow_emitsUpdatedValue_whenKeyChanges() = runTest {
        dataSource.putInt("key", 100)

        val flow = dataSource.getKeyFlow("key") { it.getInt("key", 0) }
        val values = mutableListOf<Int>()

        val job = launch {
            flow.take(3).toList().let { values.addAll(it) }
        }

        // Give time for initial emission
        kotlinx.coroutines.delay(100)

        dataSource.putInt("key", 200)
        kotlinx.coroutines.delay(100)

        dataSource.putInt("key", 300)
        kotlinx.coroutines.delay(100)

        job.join()

        assertEquals(listOf(100, 200, 300), values)
    }

    @Test
    fun getKeyFlow_doesNotEmit_whenDifferentKeyChanges() = runTest {
        dataSource.putInt("key1", 100)

        val flow = dataSource.getKeyFlow("key1") { it.getInt("key1", 0) }
        val values = mutableListOf<Int>()

        val job = launch {
            flow.take(1).toList().let { values.addAll(it) }
        }

        // Give time for initial emission
        kotlinx.coroutines.delay(100)

        // Change a different key
        dataSource.putInt("key2", 200)
        kotlinx.coroutines.delay(100)

        job.join()

        // Should only have the initial value
        assertEquals(listOf(100), values)
    }
}
