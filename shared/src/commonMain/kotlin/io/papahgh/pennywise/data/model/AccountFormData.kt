package io.papahgh.pennywise.data.model

data class AccountFormData(
    val icon: IconModel,
    val name: String,
    val currency: CurrencyCode,
    val excluded: Boolean,
    val initialValueCents: Int,
)
