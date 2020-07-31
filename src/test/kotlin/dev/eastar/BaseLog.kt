package dev.eastar

import org.junit.After
import org.junit.Before

abstract class BaseLog {
    @Before
    fun testBefore() = Log.w("start")

    @After
    fun testAfter() = Log.w("end")

}
