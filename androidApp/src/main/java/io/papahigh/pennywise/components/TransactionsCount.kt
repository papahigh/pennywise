package io.papahigh.pennywise.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.papahigh.pennywise.R
import io.papahigh.pennywise.theme.PennywiseTheme

@Composable
fun TransactionsCount(
    count: Int,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.secondary,
    style: TextStyle = LocalTextStyle.current,
) {
    val formatted = rememberFormattedValue(count)
    Text(
        text = pluralStringResource(R.plurals.transactionCount, count, formatted),
        modifier = modifier,
        color = color,
        style = style,
    )
}

@Composable
@PreviewLightDark
fun TransactionCountPreview() {
    PennywiseTheme {
        Column {
            listOf(0, 1, 3, 99, 10001).forEach {
                TransactionsCount(it)
            }
        }
    }
}
