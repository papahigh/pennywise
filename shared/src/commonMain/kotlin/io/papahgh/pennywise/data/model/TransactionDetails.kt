package io.papahgh.pennywise.data.model

import kotlinx.datetime.LocalDateTime

data class TransactionDetails(
    val id: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val timestamp: LocalDateTime,
    val category: CategorySummary,
    val valueCents: Int,
    val description: String?,
)
