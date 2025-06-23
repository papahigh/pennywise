package io.papahgh.pennywise.data.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.papahgh.pennywise.data.model.AccountFormData
import io.papahgh.pennywise.data.model.AccountSummary
import io.papahgh.pennywise.data.model.CurrencyCode
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "account")
internal data class AccountEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "created_at")
    val createdAt: LocalDateTime,
    @ColumnInfo(name = "updated_at")
    val updatedAt: LocalDateTime,
    @ColumnInfo(name = "name")
    val name: String,
    @Embedded(prefix = "icon_")
    val icon: IconEntity,
    @ColumnInfo(name = "currency")
    val currency: CurrencyCode,
    @ColumnInfo(name = "archived")
    val archived: Boolean,
    @ColumnInfo(name = "excluded")
    val excluded: Boolean,
    @ColumnInfo(name = "display_order")
    val displayOrder: Int,
    @ColumnInfo(name = "initial_value_cents")
    val initialValueCents: Int,
)

internal fun AccountEntity.toModelSummary() =
    AccountSummary(
        id = id,
        name = name,
        icon = icon.toModel(),
        currency = currency,
    )

internal fun AccountEntity.toFormData() =
    AccountFormData(
        name = name,
        icon = icon.toModel(),
        currency = currency,
        excluded = excluded,
        initialValueCents = initialValueCents,
    )
