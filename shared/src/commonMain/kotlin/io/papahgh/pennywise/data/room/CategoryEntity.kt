package io.papahgh.pennywise.data.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import io.papahgh.pennywise.data.model.CategorySummary
import io.papahgh.pennywise.data.model.CategoryType
import kotlinx.datetime.LocalDateTime

@Entity(
    tableName = "category",
    indices = [
        Index(value = ["parent_id"]),
    ],
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["parent_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
internal data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "created_at")
    val createdAt: LocalDateTime,
    @ColumnInfo(name = "updated_at")
    val updatedAt: LocalDateTime,
    @ColumnInfo(name = "name")
    val name: String,
    @Embedded(prefix = "icon_")
    val icon: IconEntity,
    @ColumnInfo(name = "type")
    val type: CategoryType,
    @ColumnInfo(name = "parent_id")
    val parentId: Int? = null,
    @ColumnInfo(name = "display_order")
    val displayOrder: Int,
)

internal fun CategoryEntity.toModelSummary() =
    CategorySummary(
        id = id,
        name = name,
        icon = icon.toModel(),
        type = type,
    )
