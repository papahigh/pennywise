package io.papahgh.pennywise.data

import io.papahgh.pennywise.SharedTest
import io.papahgh.pennywise.config.PennywiseDatabase
import io.papahgh.pennywise.data.model.now
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class TransactionRepositoryTest : SharedTest() {
    val accountRepository by lazy { inject<AccountRepository>() }
    val categoryRepository by lazy { inject<CategoryRepository>() }
    val transactionRepository by lazy { inject<TransactionRepository>() }
    val db by lazy { inject<PennywiseDatabase>() }

    @Test
    fun `should create transaction`() =
        runTest {
            accountRepository.createAccount(TestDataFactory.accounts.first())
            categoryRepository.createCategory(TestDataFactory.categories.first())

            val expected = TestDataFactory.transactions.first()
            transactionRepository.createTransaction(1, expected)

            val actual = transactionRepository.findForUpdate(1)

            assertNotNull(actual, "Transaction not created")
            assertEquals(expected.category, actual.category)
            assertEquals(expected.timestamp, actual.timestamp)
            assertEquals(expected.valueCents, actual.valueCents)
            assertEquals(expected.description, actual.description)
        }

    @Test
    fun `should update transaction`() =
        runTest {
            accountRepository.createAccount(TestDataFactory.accounts.first())
            categoryRepository.createCategory(TestDataFactory.categories.first())

            val original = TestDataFactory.transactions.first()
            transactionRepository.createTransaction(1, original)

            val expected =
                original.copy(
                    valueCents = 2000,
                    description = "Updated Description",
                    timestamp = LocalDateTime.now(),
                )

            transactionRepository.updateTransaction(1, expected)
            val actual = transactionRepository.findForUpdate(1)

            assertNotNull(actual, "Transaction not found")
            assertEquals(expected.category, actual.category)
            assertEquals(expected.timestamp, actual.timestamp)
            assertEquals(expected.valueCents, actual.valueCents)
            assertEquals(expected.description, actual.description)
        }

    @Test
    fun `should delete transaction`() =
        runTest {
            accountRepository.createAccount(TestDataFactory.accounts.first())
            categoryRepository.createCategory(TestDataFactory.categories.first())
            transactionRepository.createTransaction(1, TestDataFactory.transactions.first())
            assertEquals(1, db.transactionDao.count())
            transactionRepository.deleteTransaction(1)
            assertEquals(0, db.transactionDao.count())
        }
}
