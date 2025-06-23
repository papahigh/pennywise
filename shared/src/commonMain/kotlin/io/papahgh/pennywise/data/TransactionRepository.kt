package io.papahgh.pennywise.data

import io.papahgh.pennywise.data.model.TransactionFormData
import io.papahgh.pennywise.data.model.now
import io.papahgh.pennywise.data.room.TransactionDao
import io.papahgh.pennywise.data.room.TransactionEntity
import io.papahgh.pennywise.data.room.toFormData
import kotlinx.datetime.LocalDateTime

interface TransactionRepository {
    suspend fun findForUpdate(id: Int): TransactionFormData?

    suspend fun createTransaction(
        accountId: Int,
        formData: TransactionFormData,
    )

    suspend fun updateTransaction(
        id: Int,
        formData: TransactionFormData,
    )

    suspend fun deleteTransaction(id: Int)
}

internal class DefaultTransactionRepository(
    private val transactionDao: TransactionDao,
) : TransactionRepository {
    override suspend fun findForUpdate(id: Int): TransactionFormData? = transactionDao.findProjection(id)?.toFormData()

    override suspend fun createTransaction(
        accountId: Int,
        formData: TransactionFormData,
    ) = transactionDao.create(
        TransactionEntity(
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            accountId = accountId,
            categoryId = formData.category.id,
            timestamp = formData.timestamp,
            valueCents = formData.valueCents,
            description = formData.description,
        ),
    )

    override suspend fun updateTransaction(
        id: Int,
        formData: TransactionFormData,
    ) = transactionDao.update(id) { entity ->
        entity.copy(
            updatedAt = LocalDateTime.now(),
            categoryId = formData.category.id,
            timestamp = formData.timestamp,
            valueCents = formData.valueCents,
            description = formData.description,
        )
    }

    override suspend fun deleteTransaction(id: Int) = transactionDao.delete(id)
}
