package io.papahgh.pennywise.data.model

data class CategorySummary(
    val id: Int,
    val name: String,
    val icon: IconModel,
    val type: CategoryType,
)
