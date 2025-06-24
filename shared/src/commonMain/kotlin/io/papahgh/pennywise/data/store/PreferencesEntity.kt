package io.papahgh.pennywise.data.store

import io.papahgh.pennywise.data.model.CurrencyCode
import io.papahgh.pennywise.data.model.PreferencesModel
import kotlinx.serialization.Serializable

@Serializable
internal data class PreferencesEntity(
    val defaultCurrency: CurrencyCode,
)

internal fun PreferencesEntity.toModel() = PreferencesModel(defaultCurrency = defaultCurrency)

internal fun PreferencesModel.toEntity() = PreferencesEntity(defaultCurrency = defaultCurrency)
