package io.papahgh.pennywise.data

import io.papahgh.pennywise.data.model.PreferencesModel
import io.papahgh.pennywise.data.store.PreferencesStore
import io.papahgh.pennywise.data.store.toEntity
import io.papahgh.pennywise.data.store.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

interface PreferencesRepository {
    fun getPreferencesFlow(): Flow<PreferencesModel>

    suspend fun updatePreferences(model: PreferencesModel)
}

internal class DefaultPreferencesRepository(
    private val preferencesStore: PreferencesStore,
) : PreferencesRepository {
    override fun getPreferencesFlow(): Flow<PreferencesModel> = preferencesStore.valueFlow.map { it.toModel() }

    override suspend fun updatePreferences(model: PreferencesModel) = preferencesStore.updateValue(model.toEntity())
}

internal class InMemoryPreferencesRepository : PreferencesRepository {
    private val preferencesStore = MutableStateFlow(PreferencesModel.DEFAULT_VALUE)

    override fun getPreferencesFlow(): Flow<PreferencesModel> = preferencesStore

    override suspend fun updatePreferences(model: PreferencesModel) {
        preferencesStore.update { model }
    }
}
