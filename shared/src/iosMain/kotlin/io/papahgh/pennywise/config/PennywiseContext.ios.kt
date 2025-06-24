package io.papahgh.pennywise.config

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.papahgh.pennywise.data.store.PreferencesStore
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

internal actual val platformModule =
    module {
        single {
            PreferencesStore("${getDocumentDirectory()}/preferences.json", get(), get())
        }
        single {
            val dbFile = "${getDocumentDirectory()}/$PENNYWISE_DB_FILE_NAME"
            Room
                .databaseBuilder<PennywiseDatabase>(name = dbFile)
                .setDriver(BundledSQLiteDriver())
                .setQueryCoroutineContext(Dispatchers.IO)
                .addTypeConverter(PennywiseTypeConverters())
                .build()
        }
    }

@OptIn(ExperimentalForeignApi::class)
internal fun getDocumentDirectory(): String {
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
