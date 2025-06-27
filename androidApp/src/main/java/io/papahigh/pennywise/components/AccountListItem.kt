package io.papahigh.pennywise.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.papahgh.pennywise.data.model.AccountDetails
import io.papahgh.pennywise.data.model.CurrencyCode
import io.papahgh.pennywise.data.model.now
import io.papahgh.pennywise.data.model.randomAccountIcon
import io.papahigh.pennywise.theme.PennywiseTheme
import kotlinx.datetime.LocalDateTime
import kotlin.random.Random

@Composable
fun AccountListItem(
    account: AccountDetails,
    modifier: Modifier = Modifier,
    trailingContent: @Composable () -> Unit = {},
) {
    ListItem(
        headlineContent = { Text(account.name) },
        supportingContent = {
            CurrencyValue(
                currencyCode = account.currency,
                valueCents = account.valueCents,
            )
        },
        leadingContent = {
            IconView(account.icon)
        },
        trailingContent = trailingContent,
        modifier = modifier,
    )
}

@Composable
@PreviewLightDark
private fun ListItemPreview() {
    PennywiseTheme {
        val items =
            (1..8).map {
                AccountDetails(
                    id = it,
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.now(),
                    icon = randomAccountIcon(),
                    name = "Account $it",
                    currency = CurrencyCode.USD,
                    excluded = false,
                    valueCents = Random.nextInt(-10000, 10000),
                    displayOrder = it,
                )
            }
        LazyColumn {
            items(items, key = { it.id }) { it ->
                AccountListItem(account = it) { Text("extras") }
            }
        }
    }
}
