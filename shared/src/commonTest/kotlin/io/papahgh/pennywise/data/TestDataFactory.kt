package io.papahgh.pennywise.data

import io.papahgh.pennywise.data.model.AccountFormData
import io.papahgh.pennywise.data.model.CategoryFormData
import io.papahgh.pennywise.data.model.CategorySummary
import io.papahgh.pennywise.data.model.CategoryType
import io.papahgh.pennywise.data.model.CurrencyCode
import io.papahgh.pennywise.data.model.TransactionFormData
import io.papahgh.pennywise.data.model.now
import io.papahgh.pennywise.data.model.randomAccountIcon
import io.papahgh.pennywise.data.model.randomCategoryIcon
import kotlinx.datetime.LocalDateTime

object TestDataFactory {
    val accounts =
        listOf(
            AccountFormData(
                icon = randomAccountIcon(),
                name = "Checking",
                currency = CurrencyCode.USD,
                excluded = false,
                initialValueCents = 101,
            ),
            AccountFormData(
                icon = randomAccountIcon(),
                name = "Home Savings",
                currency = CurrencyCode.RUB,
                excluded = false,
                initialValueCents = 202,
            ),
        )

    val categories =
        listOf(
            CategoryFormData(
                icon = randomCategoryIcon(),
                name = "INCOME",
                type = CategoryType.INCOME,
                parent = null,
            ),
            CategoryFormData(
                icon = randomCategoryIcon(),
                name = "EXPENSE",
                type = CategoryType.EXPENSE,
                parent = null,
            ),
        )

    val transactions =
        listOf(
            TransactionFormData(
                category = categories[0].toSummary(1),
                timestamp = LocalDateTime.now(),
                valueCents = 1001,
                description = "description",
            ),
            TransactionFormData(
                category = categories[1].toSummary(2),
                timestamp = LocalDateTime.now(),
                valueCents = -1001,
                description = "description2",
            ),
        )

    private fun CategoryFormData.toSummary(id: Int) =
        CategorySummary(
            id = id,
            name = name,
            icon = icon,
            type = type,
        )
}
