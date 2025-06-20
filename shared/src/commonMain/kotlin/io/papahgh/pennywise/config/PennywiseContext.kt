package io.papahgh.pennywise.config

import io.papahgh.pennywise.data.AccountRepository
import io.papahgh.pennywise.data.CategoryRepository
import io.papahgh.pennywise.data.DefaultAccountRepository
import io.papahgh.pennywise.data.DefaultCategoryRepository
import io.papahgh.pennywise.data.DefaultTransactionRepository
import io.papahgh.pennywise.data.TransactionRepository
import kotlin.getValue

class PennywiseContext(
    private val factory: PennywiseFactory,
) {
    val db: PennywiseDatabase by lazy { factory.createDatabase() }
    val accounts: AccountRepository by lazy { DefaultAccountRepository(db.accountDao) }
    val categories: CategoryRepository by lazy { DefaultCategoryRepository(db.categoryDao) }
    val transactions: TransactionRepository by lazy { DefaultTransactionRepository(db.transactionDao) }
}
