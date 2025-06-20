package io.papahgh.pennywise.data.model

import kotlinx.datetime.LocalDateTime

data class AccountDetails(
    val id: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val icon: IconModel,
    val name: String,
    val currency: String,
    val excluded: Boolean,
    val valueCents: Int,
    val displayOrder: Int,
)
