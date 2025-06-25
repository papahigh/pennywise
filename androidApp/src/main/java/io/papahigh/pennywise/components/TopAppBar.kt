package io.papahigh.pennywise.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.papahigh.pennywise.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(title: String = stringResource(R.string.app_name)) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
            )
        },
    )
}
