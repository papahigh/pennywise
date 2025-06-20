package io.papahgh.pennywise.data.room

import androidx.room.ColumnInfo
import io.papahgh.pennywise.data.model.BackgroundColor

data class IconEntity(
    @ColumnInfo(name = "symbol")
    val iconSymbol: String,
    @ColumnInfo(name = "background")
    val background: BackgroundColor,
)
