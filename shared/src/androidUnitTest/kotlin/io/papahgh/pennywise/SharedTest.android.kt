@file:Suppress("ktlint:standard:filename")

package io.papahgh.pennywise

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import io.papahgh.pennywise.config.DefaultPennywiseFactory
import io.papahgh.pennywise.config.PennywiseContext
import io.papahgh.pennywise.config.PennywiseDatabase
import io.papahgh.pennywise.config.PennywiseFactory
import io.papahgh.pennywise.config.PennywiseTypeConverters
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
actual abstract class BaseSharedTest

actual object TestContext {
    private lateinit var _current: PennywiseContext
    actual val current: PennywiseContext
        get() = _current

    actual fun setUp() {
        val androidContext = instrumentation.targetContext
        val delegateFactory = DefaultPennywiseFactory(androidContext)
        _current =
            PennywiseContext(
                object : PennywiseFactory by delegateFactory {
                    override fun createDatabase() =
                        Room
                            .inMemoryDatabaseBuilder(androidContext, PennywiseDatabase::class.java)
                            .addTypeConverter(PennywiseTypeConverters())
                            .build()
                },
            )
    }

    actual fun tearDown() {
        _current.db.close()
    }
}

private val instrumentation = InstrumentationRegistry.getInstrumentation()
