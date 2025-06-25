package io.papahgh.pennywise.data.model

data class PreferencesModel(
    val currentTheme: Theme,
    val defaultCurrency: CurrencyCode,
    val isOnboardingComplete: Boolean,
) {
    companion object {
        val DEFAULT_VALUE = PreferencesModel(
            currentTheme = Theme.SYSTEM,
            defaultCurrency = CurrencyCode.USD,
            isOnboardingComplete = false,
        )
    }
}

enum class Theme {
    LIGHT,
    DARK,
    SYSTEM,
}
