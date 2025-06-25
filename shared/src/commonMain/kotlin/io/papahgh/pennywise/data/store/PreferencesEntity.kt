package io.papahgh.pennywise.data.store

import io.papahgh.pennywise.data.model.CurrencyCode
import io.papahgh.pennywise.data.model.PreferencesModel
import io.papahgh.pennywise.data.model.Theme
import kotlinx.serialization.Serializable

@Serializable
internal data class PreferencesEntity(
    val currentTheme: Theme,
    val defaultCurrency: CurrencyCode,
    val isOnboardingComplete: Boolean = false,
) {
    companion object {
        val DEFAULT_VALUE = PreferencesModel.DEFAULT_VALUE.toEntity()
    }
}

internal fun PreferencesEntity.toModel() =
    PreferencesModel(
        currentTheme = currentTheme,
        defaultCurrency = defaultCurrency,
        isOnboardingComplete = isOnboardingComplete,
    )

internal fun PreferencesModel.toEntity() =
    PreferencesEntity(
        currentTheme = currentTheme,
        defaultCurrency = defaultCurrency,
        isOnboardingComplete = isOnboardingComplete,
    )
