package io.papahgh.pennywise.data.room

import androidx.room.ColumnInfo
import io.papahgh.pennywise.data.model.BackgroundColor
import io.papahgh.pennywise.data.model.IconModel

internal data class IconEntity(
    @ColumnInfo(name = "symbol")
    val iconSymbol: String,
    @ColumnInfo(name = "background")
    val background: BackgroundColor,
)

internal fun IconEntity.toModel() =
    IconModel(
        iconSymbol = iconSymbol,
        background = background,
    )

internal fun IconModel.toEntity() =
    IconEntity(
        iconSymbol = iconSymbol,
        background = background,
    )
