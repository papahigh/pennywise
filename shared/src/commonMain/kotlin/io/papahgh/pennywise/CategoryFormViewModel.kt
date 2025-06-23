package io.papahgh.pennywise

import androidx.lifecycle.ViewModel
import io.papahgh.pennywise.data.model.BackgroundColor
import io.papahgh.pennywise.data.model.CategoryFormData
import io.papahgh.pennywise.data.model.CategorySummary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class CategoryFormViewModel(
    initialValue: CategoryFormData,
) : ViewModel() {
    private val _uiState = MutableStateFlow(initialValue)
    val uiState: StateFlow<CategoryFormData> = _uiState.asStateFlow()

    protected var formData: CategoryFormData
        get() = _uiState.value
        set(value) {
            _uiState.update { value }
        }

    abstract fun onSubmit()

    fun isValid() = formData.name.isNotBlank()

    fun onNameChange(name: String) = _uiState.update { it.copy(name = name) }

    fun onParentChange(parent: CategorySummary?) = _uiState.update { it.copy(parent = parent) }

    fun onIconSymbolChange(iconSymbol: String) = _uiState.update { it.copy(icon = it.icon.copy(iconSymbol = iconSymbol)) }

    fun onIconBackgroundChange(iconBackground: BackgroundColor) =
        _uiState.update { it.copy(icon = it.icon.copy(background = iconBackground)) }
}
