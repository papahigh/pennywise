package io.papahgh.pennywise.data.room

import androidx.room.Embedded
import androidx.room.Relation
import io.papahgh.pennywise.data.model.TransactionDetails
import io.papahgh.pennywise.data.model.TransactionFormData

internal data class TransactionProjection(
    @Embedded
    val transaction: TransactionEntity,
    @Relation(parentColumn = "category_id", entityColumn = "id")
    val category: CategoryEntity,
)

internal fun TransactionProjection.toModelDetails() =
    TransactionDetails(
        id = transaction.id,
        createdAt = transaction.createdAt,
        updatedAt = transaction.updatedAt,
        category = category.toModelSummary(),
        timestamp = transaction.timestamp,
        valueCents = transaction.valueCents,
        description = transaction.description,
    )

internal fun TransactionProjection.toFormData() =
    TransactionFormData(
        category = category.toModelSummary(),
        timestamp = transaction.timestamp,
        valueCents = transaction.valueCents,
        description = transaction.description,
    )
