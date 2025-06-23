package io.papahgh.pennywise.data.room

import androidx.room.Embedded
import io.papahgh.pennywise.data.model.AccountDetails

internal data class AccountProjection(
    @Embedded
    val account: AccountEntity,
    val valueCents: Int,
)

internal fun AccountProjection.toModelDetails() =
    AccountDetails(
        id = account.id,
        createdAt = account.createdAt,
        updatedAt = account.updatedAt,
        icon = account.icon.toModel(),
        name = account.name,
        currency = account.currency,
        excluded = account.excluded,
        valueCents = valueCents,
        displayOrder = account.displayOrder,
    )
