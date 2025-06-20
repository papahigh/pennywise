package io.papahgh.pennywise.data

import io.papahgh.pennywise.data.room.CategoryDao

interface CategoryRepository

class DefaultCategoryRepository(
    private val categoryDao: CategoryDao,
) : CategoryRepository
