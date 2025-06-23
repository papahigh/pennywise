package io.papahgh.pennywise.config

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.ProvidedTypeConverter
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import io.papahgh.pennywise.data.room.AccountDao
import io.papahgh.pennywise.data.room.AccountEntity
import io.papahgh.pennywise.data.room.CategoryDao
import io.papahgh.pennywise.data.room.CategoryEntity
import io.papahgh.pennywise.data.room.TransactionDao
import io.papahgh.pennywise.data.room.TransactionEntity
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalDateTime.Formats.ISO
import kotlinx.datetime.format

@Database(
    version = 1,
    entities = [
        AccountEntity::class,
        CategoryEntity::class,
        TransactionEntity::class,
    ],
)
@TypeConverters(PennywiseTypeConverters::class)
@ConstructedBy(PennywiseDatabaseConstructor::class)
abstract class PennywiseDatabase : RoomDatabase() {
    internal abstract val accountDao: AccountDao
    internal abstract val categoryDao: CategoryDao
    internal abstract val transactionDao: TransactionDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
internal expect object PennywiseDatabaseConstructor : RoomDatabaseConstructor<PennywiseDatabase> {
    override fun initialize(): PennywiseDatabase
}

@ProvidedTypeConverter
internal class PennywiseTypeConverters {
    @TypeConverter
    fun fromLocalDatetime(dateTime: LocalDateTime?): String? = dateTime?.format(ISO)

    @TypeConverter
    fun toLocalDateTime(rawValue: String?): LocalDateTime? = rawValue?.let { LocalDateTime.parse(it, ISO) }
}

internal const val PENNYWISE_DB_FILE_NAME = "pennywise.db"
