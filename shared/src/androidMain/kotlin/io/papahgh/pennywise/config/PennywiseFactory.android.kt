@file:Suppress("ktlint:standard:filename")

package io.papahgh.pennywise.config

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import android.content.Context as AndroidContext

actual class DefaultPennywiseFactory(
    private val androidContext: AndroidContext,
) : PennywiseFactory {
    actual override fun createDatabase(): PennywiseDatabase {
        val dbFile = androidContext.getDatabasePath(PENNYWISE_DB_FILE_NAME)
        return Room
            .databaseBuilder<PennywiseDatabase>(
                name = dbFile.absolutePath,
                context = androidContext,
            )
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .addTypeConverter(PennywiseTypeConverters())
            .build()
    }
}
