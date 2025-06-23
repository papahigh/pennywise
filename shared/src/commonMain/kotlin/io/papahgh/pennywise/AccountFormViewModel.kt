package io.papahgh.pennywise

import androidx.lifecycle.ViewModel
import io.papahgh.pennywise.data.model.AccountFormData
import io.papahgh.pennywise.data.model.BackgroundColor
import io.papahgh.pennywise.data.model.CurrencyCode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class AccountFormViewModel(initialValue: AccountFormData) : ViewModel() {
    private val _uiState = MutableStateFlow(initialValue)
    val uiState: StateFlow<AccountFormData> = _uiState.asStateFlow()

    protected var formData: AccountFormData
        get() = _uiState.value
        set(value) {
            _uiState.update { value }
        }

    abstract fun onSubmit()

    fun isValid() = formData.name.isNotBlank()

    fun onNameChange(name: String) =
        _uiState.update { it.copy(name = name) }

    fun onCurrencyChange(currency: CurrencyCode) =
        _uiState.update { it.copy(currency = currency) }

    fun onExcludedChange(excluded: Boolean) =
        _uiState.update { it.copy(excluded = excluded) }

    fun onIconSymbolChange(iconSymbol: String) =
        _uiState.update { it.copy(icon = it.icon.copy(iconSymbol = iconSymbol)) }

    fun onIconBackgroundChange(iconBackground: BackgroundColor) =
        _uiState.update { it.copy(icon = it.icon.copy(background = iconBackground)) }

    fun onInitialValueChange(initialValueCents: Int) =
        _uiState.update { it.copy(initialValueCents = initialValueCents) }
}