package io.papahgh.pennywise.data

import io.papahgh.pennywise.data.model.CategoryFormData
import io.papahgh.pennywise.data.model.now
import io.papahgh.pennywise.data.room.CategoryDao
import io.papahgh.pennywise.data.room.CategoryEntity
import io.papahgh.pennywise.data.room.toEntity
import io.papahgh.pennywise.data.room.toFormData
import kotlinx.datetime.LocalDateTime

interface CategoryRepository {
    suspend fun findForUpdate(id: Int): CategoryFormData?

    suspend fun createCategory(formData: CategoryFormData)

    suspend fun updateCategory(
        id: Int,
        formData: CategoryFormData,
    )

    suspend fun deleteCategory(id: Int)
}

internal class DefaultCategoryRepository(
    private val categoryDao: CategoryDao,
) : CategoryRepository {
    override suspend fun findForUpdate(id: Int): CategoryFormData? = categoryDao.findProjection(id)?.toFormData()

    override suspend fun createCategory(formData: CategoryFormData) =
        categoryDao.create { displayOrder ->
            CategoryEntity(
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
                icon = formData.icon.toEntity(),
                name = formData.name,
                type = formData.type,
                displayOrder = displayOrder,
            )
        }

    override suspend fun updateCategory(
        id: Int,
        formData: CategoryFormData,
    ) = categoryDao.update(id) { entity ->
        entity.copy(
            updatedAt = LocalDateTime.now(),
            icon = formData.icon.toEntity(),
            name = formData.name,
            type = formData.type,
        )
    }

    override suspend fun deleteCategory(id: Int) = categoryDao.delete(id)
}
