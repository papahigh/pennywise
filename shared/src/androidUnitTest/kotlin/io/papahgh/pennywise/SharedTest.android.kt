@file:Suppress("ktlint:standard:filename")

package io.papahgh.pennywise

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import io.papahgh.pennywise.config.PennywiseContext
import io.papahgh.pennywise.config.PennywiseDatabase
import io.papahgh.pennywise.config.PennywiseTypeConverters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.dsl.onClose
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
actual abstract class BaseSharedTest

actual object TestContext {
    private lateinit var _current: PennywiseContext
    actual val current: PennywiseContext
        get() = _current

    @OptIn(ExperimentalCoroutinesApi::class)
    actual fun setUp() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val androidContext = instrumentation.targetContext
        _current =
            PennywiseContext.of {
                val overrides =
                    module {
                        androidContext(androidContext)
                        single { CoroutineScope(UnconfinedTestDispatcher() + SupervisorJob()) }
                        single {
                            Room
                                .inMemoryDatabaseBuilder(androidContext, PennywiseDatabase::class.java)
                                .addTypeConverter(PennywiseTypeConverters())
                                .build()
                        }.onClose { it?.close() }
                    }

                modules(overrides)
            }
    }

    actual fun tearDown() {
        _current.close()
    }
}
