package io.papahgh.pennywise.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
internal abstract class AccountDao {
    @Query("SELECT * FROM account WHERE id = :id")
    abstract suspend fun findAccount(id: Int): AccountEntity?

    @Query(
        """
        SELECT a.*,
               a.initial_value_cents +
               (SELECT SUM(t.value_cents) FROM transaction_record t WHERE t.account_id = a.id) as valueCents
        FROM account a
        WHERE a.id = :id
        """,
    )
    @Transaction
    abstract suspend fun findProjection(id: Int): AccountProjection?

    @Transaction
    open suspend fun create(factory: (Int) -> AccountEntity) = create(factory(getNextDisplayOrder()))

    @Transaction
    open suspend fun update(
        id: Int,
        mapper: (AccountEntity) -> AccountEntity,
    ) {
        findAccount(id)?.let { mapper(it) }?.let { update(it) }
    }

    @Transaction
    open suspend fun delete(id: Int) {
        findAccount(id)?.let { delete(it) }
    }

    //
    // internal
    //

    @Query("SELECT COUNT(*) FROM account")
    internal abstract suspend fun count(): Int

    //
    // protected
    //

    @Insert
    protected abstract suspend fun create(account: AccountEntity)

    @Update
    protected abstract suspend fun update(account: AccountEntity)

    @Delete
    protected abstract suspend fun delete(account: AccountEntity)

    @Query("SELECT MAX(display_order) + 1 FROM account")
    protected abstract suspend fun getNextDisplayOrder(): Int
}
