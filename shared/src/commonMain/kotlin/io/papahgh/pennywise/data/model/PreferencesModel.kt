package io.papahgh.pennywise.data.model

data class PreferencesModel(
    val defaultCurrency: CurrencyCode,
) {
    companion object {
        val DEFAULTS = PreferencesModel(CurrencyCode.USD)
    }
}
