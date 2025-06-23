package io.papahgh.pennywise.data.model

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal fun LocalDateTime.Companion.now(): LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

internal fun randomAccountIcon() =
    IconModel(
        iconSymbol = arrayOf("💰", "💵").random(),
        background = randomBackground(),
    )

internal fun randomCategoryIcon() =
    IconModel(
        iconSymbol = arrayOf("💰", "💵").random(),
        background = randomBackground(),
    )

internal fun randomBackground() = BackgroundColor.entries.toTypedArray().random()
