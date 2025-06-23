package io.papahgh.pennywise

import androidx.lifecycle.ViewModel
import io.papahgh.pennywise.data.model.CategorySummary
import io.papahgh.pennywise.data.model.TransactionFormData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDateTime

abstract class TransactionFormViewModel(
    initialValue: TransactionFormData,
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialValue)
    val uiState = _uiState.asStateFlow()

    protected var formData: TransactionFormData
        get() = _uiState.value
        set(value) {
            _uiState.value = value
        }

    abstract fun onSubmit()

    fun isValid() = formData.valueCents > 0

    fun onCategoryChange(category: CategorySummary) =
        _uiState.update { it.copy(category = category) }

    fun onTimestampChange(timestamp: LocalDateTime) =
        _uiState.update { it.copy(timestamp = timestamp) }

    fun onValueCentsChange(valueCents: Int) =
        _uiState.update { it.copy(valueCents = valueCents) }

    fun onDescriptionChange(description: String) =
        _uiState.update { it.copy(description = description) }
}
