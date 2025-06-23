package io.papahgh.pennywise.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
internal abstract class TransactionDao {
    @Query("SELECT * FROM transaction_record WHERE id = :id")
    abstract suspend fun findTransaction(id: Int): TransactionEntity?

    @Query("SELECT * FROM transaction_record WHERE id = :id")
    @Transaction
    abstract suspend fun findProjection(id: Int): TransactionProjection?

    @Insert
    abstract suspend fun create(transaction: TransactionEntity)

    @Transaction
    open suspend fun update(
        id: Int,
        mapper: (TransactionEntity) -> TransactionEntity,
    ) {
        findTransaction(id)?.let { mapper(it) }?.let { update(it) }
    }

    @Transaction
    open suspend fun delete(id: Int) {
        findTransaction(id)?.let { delete(it) }
    }

    //
    // internal
    //

    @Query("SELECT COUNT(*) FROM transaction_record")
    internal abstract suspend fun count(): Int

    //
    // protected
    //

    @Update
    protected abstract suspend fun update(transaction: TransactionEntity)

    @Delete
    protected abstract suspend fun delete(transaction: TransactionEntity)
}
