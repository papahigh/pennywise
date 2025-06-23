package io.papahgh.pennywise.data.room

import androidx.room.Embedded
import androidx.room.Relation
import io.papahgh.pennywise.data.model.CategoryDetails
import io.papahgh.pennywise.data.model.CategoryFormData

internal data class CategoryProjection(
    @Embedded
    val category: CategoryEntity,
    val hasChildren: Boolean,
    @Relation(parentColumn = "id", entityColumn = "parent_id")
    val parentCategory: CategoryEntity? = null,
    val transactionsCount: Int,
)

internal fun CategoryProjection.toModelDetails() =
    CategoryDetails(
        id = category.id,
        createdAt = category.createdAt,
        updatedAt = category.updatedAt,
        icon = category.icon.toModel(),
        name = category.name,
        type = category.type,
        parent = parentCategory?.toModelSummary(),
        hasChildren = hasChildren,
        displayOrder = category.displayOrder,
        transactionsCount = transactionsCount,
    )

internal fun CategoryProjection.toFormData() =
    CategoryFormData(
        icon = category.icon.toModel(),
        name = category.name,
        type = category.type,
        parent = parentCategory?.toModelSummary(),
    )
