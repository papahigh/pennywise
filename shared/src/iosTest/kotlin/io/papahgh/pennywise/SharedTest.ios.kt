@file:Suppress("ktlint:standard:filename")

package io.papahgh.pennywise

import io.papahgh.pennywise.config.PENNYWISE_DB_FILE_NAME
import io.papahgh.pennywise.config.PennywiseContext
import io.papahgh.pennywise.config.getDocumentDirectory
import io.papahgh.pennywise.data.InMemoryPreferencesRepository
import io.papahgh.pennywise.data.PreferencesRepository
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.dsl.module
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL

actual abstract class BaseSharedTest

actual object TestContext {
    private lateinit var _current: PennywiseContext
    actual val current: PennywiseContext
        get() = _current

    actual fun setUp() {
        _current =
            PennywiseContext.of {
                val overrides =
                    module {
                        single<PreferencesRepository> { InMemoryPreferencesRepository() }
                    }

                modules(overrides)
            }
    }

    actual fun tearDown() {
        _current.close()
        listOf(DB_FILE, "$DB_FILE-wal", "$DB_FILE-shm", "$DB_FILE.lck").forEach {
            deleteFile(it)
        }
    }
}

private val DB_FILE = "${getDocumentDirectory()}/$PENNYWISE_DB_FILE_NAME"

@OptIn(ExperimentalForeignApi::class)
private fun deleteFile(path: String) {
    NSFileManager.defaultManager.removeItemAtURL(
        NSURL(fileURLWithPath = path),
        null,
    )
}
