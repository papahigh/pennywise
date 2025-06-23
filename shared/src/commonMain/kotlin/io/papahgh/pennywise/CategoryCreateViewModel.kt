package io.papahgh.pennywise

import androidx.lifecycle.viewModelScope
import io.papahgh.pennywise.data.CategoryRepository
import io.papahgh.pennywise.data.model.CategoryFormData
import io.papahgh.pennywise.data.model.CategoryType
import io.papahgh.pennywise.data.model.randomCategoryIcon
import kotlinx.coroutines.launch

class CategoryCreateViewModel(
    categoryType: CategoryType,
    private val repository: CategoryRepository,
) : CategoryFormViewModel(tempEmptyState(categoryType)) {
    override fun onSubmit() {
        viewModelScope.launch {
            repository.createCategory(formData)
        }
    }
}

private fun tempEmptyState(type: CategoryType) =
    CategoryFormData(
        icon = randomCategoryIcon(),
        name = "",
        type = type,
        parent = null,
    )
