package io.papahgh.pennywise.data.model

data class CategoryFormData(
    val icon: IconModel,
    val name: String,
    val type: CategoryType,
    val parent: CategorySummary?,
)
