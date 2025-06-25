package io.papahgh.pennywise.data

import io.papahgh.pennywise.SharedTest
import io.papahgh.pennywise.data.model.CurrencyCode
import io.papahgh.pennywise.data.model.PreferencesModel
import io.papahgh.pennywise.data.model.Theme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class PreferencesRepositoryTest : SharedTest() {
    val repository by lazy { inject<PreferencesRepository>() }

    @Test
    fun `should provide default values`() =
        runTest {
            val actual = repository.getPreferencesFlow().first()
            assertEquals(PreferencesModel.DEFAULT_VALUE, actual)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should update preferences`() =
        runTest {
            val expectedValues = listOf(PreferencesModel.DEFAULT_VALUE)

            val actualValues = mutableListOf<PreferencesModel>()
            val job =
                launch {
                    repository.getPreferencesFlow().collect { actualValues.add(it) }
                }

            advanceUntilIdle()
            assertEquals(expectedValues, actualValues)

            val updatedModel =
                PreferencesModel(
                    currentTheme = Theme.DARK,
                    defaultCurrency = CurrencyCode.CNY,
                    isOnboardingComplete = true,
                )
            repository.updatePreferences(updatedModel)

            advanceUntilIdle()
            assertEquals(expectedValues + updatedModel, actualValues)

            job.cancel()
        }
}
