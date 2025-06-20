package io.papahgh.pennywise.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun LocalDateTime.Companion.now(): LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
