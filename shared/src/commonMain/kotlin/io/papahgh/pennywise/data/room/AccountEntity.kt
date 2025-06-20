package io.papahgh.pennywise.data.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "account")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "created_at")
    val createdAt: LocalDateTime,
    @ColumnInfo(name = "updated_at")
    val updatedAt: LocalDateTime,
    @ColumnInfo(name = "name")
    val name: String,
    @Embedded(prefix = "icon_")
    val icon: CustomIconEntity,
    @ColumnInfo(name = "currency")
    val currency: String,
    @ColumnInfo(name = "archived")
    val archived: Boolean,
    @ColumnInfo(name = "excluded")
    val excluded: Boolean,
)
