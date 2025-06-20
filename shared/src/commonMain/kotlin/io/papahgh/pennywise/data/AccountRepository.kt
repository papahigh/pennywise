package io.papahgh.pennywise.data

import io.papahgh.pennywise.data.room.AccountDao

interface AccountRepository

class DefaultAccountRepository(
    private val accountDao: AccountDao,
) : AccountRepository
