package io.papahgh.pennywise.data.model

import kotlinx.datetime.LocalDateTime

data class CategoryDetails(
    val id: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val name: String,
    val icon: IconModel,
    val type: CategoryType,
    val parent: CategorySummary?,
    val hasChildren: Boolean,
    val displayOrder: Int,
    val transactionsCount: Int,
)
