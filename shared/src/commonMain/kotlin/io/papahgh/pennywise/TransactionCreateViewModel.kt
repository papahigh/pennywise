package io.papahgh.pennywise

import androidx.lifecycle.viewModelScope
import io.papahgh.pennywise.data.TransactionRepository
import io.papahgh.pennywise.data.model.CategorySummary
import io.papahgh.pennywise.data.model.TransactionFormData
import io.papahgh.pennywise.data.model.now
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime

class TransactionCreateViewModel(
    private val accountId: Int,
    private val category: CategorySummary,
    private val transactionRepository: TransactionRepository,
) : TransactionFormViewModel(tempEmptyState(category)) {

    override fun onSubmit() {
        viewModelScope.launch {
            transactionRepository.createTransaction(accountId, formData)
        }
    }
}

private fun tempEmptyState(category: CategorySummary) =
    TransactionFormData(
        category = category,
        timestamp = LocalDateTime.now(),
        valueCents = 0,
        description = "",
    )
