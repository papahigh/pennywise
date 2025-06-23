package io.papahgh.pennywise.data

import io.papahgh.pennywise.SharedTest
import io.papahgh.pennywise.data.model.BackgroundColor
import io.papahgh.pennywise.data.model.CategoryType
import io.papahgh.pennywise.data.model.IconModel
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class CategoryRepositoryTest : SharedTest() {
    val repository: CategoryRepository
        get() = context.categories

    @Test
    fun `should create category`() =
        runTest {
            val expected = TestDataFactory.categories.first()
            repository.createCategory(expected)

            val actual = repository.findForUpdate(1)

            assertNotNull(actual, "Category not created")
            assertEquals(expected.name, actual.name)
            assertEquals(expected.icon, actual.icon)
            assertEquals(expected.type, actual.type)
            assertNull(expected.parent)
        }

    @Test
    fun `should update category`() =
        runTest {
            val original = TestDataFactory.categories.first()
            repository.createCategory(original)

            val expected =
                original.copy(
                    name = "Updated Name",
                    type = CategoryType.EXPENSE,
                    icon = IconModel("x", BackgroundColor.INDIGO),
                    parent = null,
                )
            repository.updateCategory(1, expected)
            val actual = repository.findForUpdate(1)

            assertNotNull(actual, "Category not found")
            assertEquals(expected.name, actual.name)
            assertEquals(expected.icon, actual.icon)
            assertEquals(expected.type, actual.type)
            assertNull(expected.parent)
        }

    @Test
    fun `should delete category`() =
        runTest {
            repository.createCategory(TestDataFactory.categories.first())
            assertEquals(1, context.db.categoryDao.count())
            repository.deleteCategory(1)
            assertEquals(0, context.db.categoryDao.count())
        }
}
