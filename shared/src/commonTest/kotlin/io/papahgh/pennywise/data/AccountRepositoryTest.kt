package io.papahgh.pennywise.data

import io.papahgh.pennywise.SharedTest
import io.papahgh.pennywise.data.model.BackgroundColor
import io.papahgh.pennywise.data.model.CurrencyCode
import io.papahgh.pennywise.data.model.IconModel
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class AccountRepositoryTest : SharedTest() {
    val repository: AccountRepository
        get() = context.accounts

    @Test
    fun `should create account`() =
        runTest {
            val expected = TestDataFactory.accounts.first()
            repository.createAccount(expected)

            val actual = repository.findForUpdate(1)

            assertNotNull(actual, "Account not created")
            assertEquals(expected.name, actual.name)
            assertEquals(expected.icon, actual.icon)
            assertEquals(expected.currency, actual.currency)
            assertEquals(expected.excluded, actual.excluded)
            assertEquals(expected.initialValueCents, actual.initialValueCents)
        }

    @Test
    fun `should update account`() =
        runTest {
            val original = TestDataFactory.accounts.first()
            repository.createAccount(original)

            val expected =
                original.copy(
                    name = "Updated Name",
                    currency = CurrencyCode.EUR,
                    icon = IconModel("x", BackgroundColor.INDIGO),
                    excluded = true,
                    initialValueCents = 123,
                )
            repository.updateAccount(1, expected)
            val actual = repository.findForUpdate(1)

            assertNotNull(actual, "Account not found")
            assertEquals(expected.name, actual.name)
            assertEquals(expected.icon, actual.icon)
            assertEquals(expected.currency, actual.currency)
            assertEquals(expected.excluded, actual.excluded)
            assertEquals(expected.initialValueCents, actual.initialValueCents)
        }

    @Test
    fun `should delete account`() =
        runTest {
            repository.createAccount(TestDataFactory.accounts.first())
            assertEquals(1, context.db.accountDao.count())
            repository.deleteAccount(1)
            assertEquals(0, context.db.accountDao.count())
        }
}
