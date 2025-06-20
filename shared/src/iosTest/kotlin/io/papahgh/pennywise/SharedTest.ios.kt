@file:Suppress("ktlint:standard:filename")

package io.papahgh.pennywise

import io.papahgh.pennywise.config.DefaultPennywiseFactory
import io.papahgh.pennywise.config.PENNYWISE_DB_FILE_NAME
import io.papahgh.pennywise.config.PennywiseContext
import io.papahgh.pennywise.config.getDirectory
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL

actual abstract class BaseSharedTest

actual object TestContext {
    private lateinit var _current: PennywiseContext
    actual val current: PennywiseContext
        get() = _current

    actual fun setUp() {
        _current = PennywiseContext(DefaultPennywiseFactory())
    }

    actual fun tearDown() {
        listOf(DB_FILE, "$DB_FILE-wal", "$DB_FILE-shm", "$DB_FILE.lck").forEach {
            deleteFile(it)
        }
    }
}

private val DB_FILE = "${getDirectory()}/$PENNYWISE_DB_FILE_NAME"

@OptIn(ExperimentalForeignApi::class)
private fun deleteFile(path: String) {
    NSFileManager.defaultManager.removeItemAtURL(
        NSURL(fileURLWithPath = path),
        null,
    )
}
