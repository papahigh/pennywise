package io.papahigh.pennywise.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.papahigh.pennywise.R
import androidx.compose.material3.TopAppBar as MaterialTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(title: String = stringResource(R.string.appName)) {
    MaterialTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.displaySmall,
            )
        },
    )
}
