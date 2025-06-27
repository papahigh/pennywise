package io.papahigh.pennywise.components

import android.icu.util.Currency
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.papahgh.pennywise.data.model.CurrencyCode
import io.papahigh.pennywise.theme.PennywiseTheme

@Composable
fun CurrencyValue(
    currencyCode: CurrencyCode,
    valueCents: Int,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.secondary,
    style: TextStyle = LocalTextStyle.current,
) {
    val currency = rememberCurrency(currencyCode)
    CurrencyValue(
        currency = currency,
        valueCents = valueCents,
        modifier = modifier,
        color = color,
        style = style,
    )
}

@Composable
fun CurrencyValue(
    currency: Currency,
    valueCents: Int,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.secondary,
    style: TextStyle = LocalTextStyle.current,
) {
    Text(
        text = rememberFormattedValue(currency, valueCents),
        modifier = modifier,
        color = color,
        style = style,
    )
}

@Composable
@PreviewLightDark
fun CurrencyValuePreview() {
    PennywiseTheme {
        Column {
            listOf(-1_111_233_01, 0, 1, 100, 199, 1_000_000_99, 1_999_999_11).forEach {
                CurrencyValue(CurrencyCode.USD, it)
            }
        }
    }
}
