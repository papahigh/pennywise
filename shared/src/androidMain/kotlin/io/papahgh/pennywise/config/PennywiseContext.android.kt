package io.papahgh.pennywise.config

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.papahgh.pennywise.data.store.PreferencesStore
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.dsl.onClose

internal actual val platformModule =
    module {
        single {
            val preferencesPath = androidContext().filesDir.resolve("preferences.json")
            PreferencesStore(preferencesPath.absolutePath, get(), get())
        }
        single {
            val androidContext = androidContext()
            val dbFile = androidContext.getDatabasePath(PENNYWISE_DB_FILE_NAME)
            Room
                .databaseBuilder<PennywiseDatabase>(
                    name = dbFile.absolutePath,
                    context = androidContext,
                ).setDriver(BundledSQLiteDriver())
                .setQueryCoroutineContext(Dispatchers.IO)
                .addTypeConverter(PennywiseTypeConverters())
                .build()
        }.onClose { it?.close() }
    }
