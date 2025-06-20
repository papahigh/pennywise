package io.papahgh.pennywise

import io.papahgh.pennywise.data.model.BackgroundColor
import io.papahgh.pennywise.data.model.CategoryType
import io.papahgh.pennywise.data.room.CategoryEntity
import io.papahgh.pennywise.data.room.CustomIconEntity
import io.papahgh.pennywise.utils.now
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class ExampleTest : SharedTest() {
    @Test
    fun `room framework should work`() =
        runTest {
            context.db.categoryDao.create(
                CategoryEntity(
                    name = "Test Category",
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.now(),
                    type = CategoryType.Income,
                    icon =
                        CustomIconEntity(
                            iconSymbol = "iconSymbol",
                            background = BackgroundColor.Red,
                        ),
                    displayOrder = 1,
                ),
            )
            assertEquals(1, context.db.categoryDao.count())
        }
}
