package io.papahgh.pennywise.data

import io.papahgh.pennywise.data.model.AccountFormData
import io.papahgh.pennywise.data.model.now
import io.papahgh.pennywise.data.room.AccountDao
import io.papahgh.pennywise.data.room.AccountEntity
import io.papahgh.pennywise.data.room.toEntity
import io.papahgh.pennywise.data.room.toFormData
import kotlinx.datetime.LocalDateTime

interface AccountRepository {
    suspend fun findForUpdate(id: Int): AccountFormData?

    suspend fun createAccount(formData: AccountFormData)

    suspend fun updateAccount(
        id: Int,
        formData: AccountFormData,
    )

    suspend fun toggleArchive(id: Int)

    suspend fun deleteAccount(id: Int)
}

internal class DefaultAccountRepository(
    private val accountDao: AccountDao,
) : AccountRepository {
    override suspend fun findForUpdate(id: Int): AccountFormData? = accountDao.findAccount(id)?.toFormData()

    override suspend fun createAccount(formData: AccountFormData) =
        accountDao.create { displayOrder ->
            AccountEntity(
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
                icon = formData.icon.toEntity(),
                name = formData.name,
                currency = formData.currency,
                excluded = formData.excluded,
                archived = false,
                displayOrder = displayOrder,
                initialValueCents = formData.initialValueCents,
            )
        }

    override suspend fun updateAccount(
        id: Int,
        formData: AccountFormData,
    ) = accountDao.update(id) { entity ->
        entity.copy(
            updatedAt = LocalDateTime.now(),
            icon = formData.icon.toEntity(),
            name = formData.name,
            currency = formData.currency,
            excluded = formData.excluded,
            initialValueCents = formData.initialValueCents,
        )
    }

    override suspend fun toggleArchive(id: Int) =
        accountDao.update(id) { entity ->
            entity.copy(archived = !entity.archived)
        }

    override suspend fun deleteAccount(id: Int) = accountDao.delete(id)
}
