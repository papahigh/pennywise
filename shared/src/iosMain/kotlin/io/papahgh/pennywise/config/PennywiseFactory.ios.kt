package io.papahgh.pennywise.config

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual class DefaultPennywiseFactory : PennywiseFactory {
    actual override fun createDatabase(): PennywiseDatabase {
        val dbFile = "${getDirectory()}/$PENNYWISE_DB_FILE_NAME"
        return Room
            .databaseBuilder<PennywiseDatabase>(name = dbFile)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .addTypeConverter(PennywiseTypeConverters())
            .build()
    }
}

@OptIn(ExperimentalForeignApi::class)
internal fun getDirectory(): String {
    val documentDirectory: NSURL? =
        NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
    return requireNotNull(documentDirectory?.path)
}
