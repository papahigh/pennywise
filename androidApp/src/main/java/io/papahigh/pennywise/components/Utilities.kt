package io.papahigh.pennywise.components

import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.icu.util.ULocale
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.intl.Locale
import io.papahgh.pennywise.data.model.CurrencyCode

@Composable
fun rememberAvailableCurrencies() =
    remember {
        CurrencyCode.entries
            .map { it.toCurrency() }
            .sortedBy { it.currencyCode }
    }

@Composable
fun rememberCurrency(currencyCode: CurrencyCode) = remember(currencyCode) { currencyCode.toCurrency() }

@Composable
fun rememberCurrentLocale(): ULocale {
    val locale = Locale.current
    return remember(locale) {
        ULocale.forLanguageTag(locale.toLanguageTag())
    }
}

@Composable
fun rememberFormattedValue(
    currency: Currency,
    valueCents: Int,
): String {
    val locale = rememberCurrentLocale()
    return remember(locale, currency, valueCents) {
        val format = NumberFormat.getCurrencyInstance(locale)
        format.currency = currency
        format.format(valueCents / 100.0)
    }
}

@Composable
fun rememberFormattedValue(value: Int): String {
    val locale = rememberCurrentLocale()
    return remember(locale) {
        NumberFormat.getNumberInstance().format(value)
    }
}

fun CurrencyCode.toCurrency(): Currency = Currency.getInstance(name)
