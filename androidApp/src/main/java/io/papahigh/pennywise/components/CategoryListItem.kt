package io.papahigh.pennywise.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.papahgh.pennywise.data.model.CategoryDetails
import io.papahgh.pennywise.data.model.CategoryType
import io.papahgh.pennywise.data.model.now
import io.papahgh.pennywise.data.model.randomCategoryIcon
import io.papahigh.pennywise.theme.PennywiseTheme
import kotlinx.datetime.LocalDateTime
import kotlin.random.Random

@Composable
fun CategoryListItem(
    category: CategoryDetails,
    modifier: Modifier = Modifier,
    trailingContent: @Composable () -> Unit = {},
) {
    ListItem(
        leadingContent = {
            IconView(iconModel = category.icon)
        },
        headlineContent = {
            Text(category.name)
        },
        supportingContent = {
            TransactionsCount(category.transactionsCount)
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
            (1..100).map {
                CategoryDetails(
                    id = it,
                    name = "Category ${it + 1}",
                    icon = randomCategoryIcon(),
                    type = CategoryType.INCOME,
                    parent = null,
                    hasChildren = false,
                    displayOrder = 1,
                    transactionsCount = Random.nextInt(10000),
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.now(),
                )
            }
        LazyColumn {
            items(items = items, key = { it.id }) {
                CategoryListItem(category = it) { Text("extras") }
            }
        }
    }
}
