package io.papahigh.pennywise.components

import android.icu.util.Currency
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.papahgh.pennywise.data.model.BackgroundColor
import io.papahigh.pennywise.theme.PennywiseTheme

@Composable
fun CurrencyList(
    items: List<Currency>,
    modifier: Modifier = Modifier,
    onSelect: (Currency) -> Unit,
) {
    LazyColumn(modifier) {
        items(items) {
            CurrencyListItem(
                currency = it,
                modifier = Modifier.clickable { onSelect(it) },
            ) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
private fun CurrencyListItem(
    currency: Currency,
    modifier: Modifier = Modifier,
    iconBackground: BackgroundColor = BackgroundColor.AMBER,
    trailingContent: @Composable () -> Unit = {},
) {
    ListItem(
        headlineContent = {
            Text(currency.displayName)
        },
        supportingContent = {
            Text(currency.currencyCode)
        },
        leadingContent = {
            IconView(
                iconSymbol = currency.symbol,
                background = iconBackground,
            )
        },
        trailingContent = trailingContent,
        modifier = modifier,
    )
}

@Composable
@PreviewLightDark
private fun CurrencySelectorPreview() {
    PennywiseTheme {
        CurrencyList(
            items = rememberAvailableCurrencies(),
            onSelect = {},
        )
    }
}
