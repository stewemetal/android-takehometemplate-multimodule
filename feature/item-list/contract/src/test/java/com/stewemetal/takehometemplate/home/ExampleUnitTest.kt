package com.stewemetal.takehometemplate.home

import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
//        assertEquals(4, 2 + 2)
        val x = "(?=.+:feature)(.+)(?<=:impl)".toRegex()
        assertTrue("project ':feature:home:impl'".contains(x))
    }
}
