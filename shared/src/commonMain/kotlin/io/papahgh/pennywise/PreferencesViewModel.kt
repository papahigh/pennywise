package io.papahgh.pennywise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.papahgh.pennywise.data.PreferencesRepository
import io.papahgh.pennywise.data.model.CurrencyCode
import io.papahgh.pennywise.data.model.PreferencesModel
import io.papahgh.pennywise.data.model.Theme
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PreferencesViewModel(
    private val preferencesRepository: PreferencesRepository,
) : ViewModel() {

    val uiState = preferencesRepository.getPreferencesFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            PreferencesModel.DEFAULT_VALUE,
        )

    fun onCurrentThemeChange(theme: Theme) =
        update(uiState.value.copy(currentTheme = theme))

    fun onOnboardingComplete() =
        update(uiState.value.copy(isOnboardingComplete = true))

    fun onDefaultCurrencyChange(defaultCurrency: CurrencyCode) =
        update(uiState.value.copy(defaultCurrency = defaultCurrency))

    private fun update(model: PreferencesModel) {
        viewModelScope.launch {
            preferencesRepository.updatePreferences(
                model,
            )
        }
    }
}
