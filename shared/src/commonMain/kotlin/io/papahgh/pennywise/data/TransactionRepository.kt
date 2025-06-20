package io.papahgh.pennywise.data

import io.papahgh.pennywise.data.room.TransactionDao

interface TransactionRepository

class DefaultTransactionRepository(
    private val transactionDao: TransactionDao,
) : TransactionRepository
