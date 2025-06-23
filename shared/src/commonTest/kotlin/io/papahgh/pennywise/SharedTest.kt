package io.papahgh.pennywise

import io.papahgh.pennywise.config.PennywiseContext
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

expect abstract class BaseSharedTest()

abstract class SharedTest : BaseSharedTest() {
    private val context: PennywiseContext
        get() = TestContext.current

    internal inline fun <reified T : Any> inject(
        qualifier: Qualifier? = null,
        noinline parameters: ParametersDefinition? = null,
    ): T = context.inject(T::class, qualifier, parameters)

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
