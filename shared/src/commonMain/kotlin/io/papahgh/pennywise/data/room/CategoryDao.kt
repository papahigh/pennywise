package io.papahgh.pennywise.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
internal abstract class CategoryDao {
    @Query("SELECT * FROM category WHERE id = :id")
    abstract suspend fun findCategory(id: Int): CategoryEntity?

    @Query(
        """
        SELECT c.*,
               (SELECT count(*) FROM category cc WHERE cc.parent_id = c.id) > 0 AS hasChildren,
               (SELECT count(*)
                FROM transaction_record t
                WHERE t.category_id = c.id)                                      AS transactionsCount
        FROM category c
        WHERE c.id = :id
        """,
    )
    @Transaction
    abstract suspend fun findProjection(id: Int): CategoryProjection?

    @Transaction
    open suspend fun create(factory: (Int) -> CategoryEntity) = create(factory(getNextDisplayOrder()))

    @Transaction
    open suspend fun update(
        id: Int,
        mapper: (CategoryEntity) -> CategoryEntity,
    ) {
        findCategory(id)?.let { mapper(it) }?.let { update(it) }
    }

    @Transaction
    open suspend fun delete(id: Int) {
        findCategory(id)?.let { delete(it) }
    }

    //
    // internal
    //

    @Query("SELECT COUNT(*) FROM category")
    internal abstract suspend fun count(): Int

    //
    // protected
    //

    @Insert
    protected abstract suspend fun create(category: CategoryEntity)

    @Update
    protected abstract suspend fun update(category: CategoryEntity)

    @Delete
    protected abstract suspend fun delete(category: CategoryEntity)

    @Query("SELECT MAX(display_order) + 1 FROM category")
    protected abstract suspend fun getNextDisplayOrder(): Int
}
