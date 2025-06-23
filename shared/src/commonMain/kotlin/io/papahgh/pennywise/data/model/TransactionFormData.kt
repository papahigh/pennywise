package io.papahgh.pennywise.data.model

import kotlinx.datetime.LocalDateTime

data class TransactionFormData(
    val category: CategorySummary,
    val timestamp: LocalDateTime,
    val valueCents: Int,
    val description: String,
)
