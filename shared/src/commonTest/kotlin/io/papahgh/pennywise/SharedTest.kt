package io.papahgh.pennywise

import io.papahgh.pennywise.config.PennywiseContext
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

expect abstract class BaseSharedTest()

abstract class SharedTest : BaseSharedTest() {
    val context: PennywiseContext
        get() = TestContext.current

    @BeforeTest
    fun setUp() {
        TestContext.setUp()
    }

    @AfterTest
    fun tearDown() {
        TestContext.tearDown()
    }
}

expect object TestContext {
    val current: PennywiseContext

    fun setUp()

    fun tearDown()
}
