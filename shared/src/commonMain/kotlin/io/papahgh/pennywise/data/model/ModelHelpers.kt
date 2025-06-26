package io.papahgh.pennywise.data.model

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun LocalDateTime.Companion.now(): LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

fun randomAccountIcon() =
    IconModel(
        iconSymbol = arrayOf("💰", "💵").random(),
        background = randomBackground(),
    )

fun randomCategoryIcon() =
    IconModel(
        iconSymbol = arrayOf("💰", "💵").random(),
        background = randomBackground(),
    )

fun randomBackground() = BackgroundColor.entries.random()
