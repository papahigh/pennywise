package io.papahgh.pennywise

import androidx.lifecycle.viewModelScope
import io.papahgh.pennywise.data.AccountRepository
import io.papahgh.pennywise.data.model.AccountFormData
import io.papahgh.pennywise.data.model.CurrencyCode
import io.papahgh.pennywise.data.model.randomAccountIcon
import kotlinx.coroutines.launch

class AccountCreateViewModel(
    private val accountRepository: AccountRepository,
) : AccountFormViewModel(tempEmptyState()) {
    override fun onSubmit() {
        viewModelScope.launch {
            accountRepository.createAccount(formData)
        }
    }
}

private fun tempEmptyState() =
    AccountFormData(
        icon = randomAccountIcon(),
        name = "",
        currency = CurrencyCode.USD,
        excluded = false,
        initialValueCents = 0,
    )
