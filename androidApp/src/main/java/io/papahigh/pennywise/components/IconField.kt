package io.papahigh.pennywise.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.papahgh.pennywise.data.model.BackgroundColor
import io.papahigh.pennywise.R
import io.papahigh.pennywise.theme.PennywiseTheme

@Composable
fun IconField() {
    var showSheet by remember { mutableStateOf(false) }
    Button(onClick = { showSheet = !showSheet }) {
        TODO()
    }
}

@Composable
private fun IconPreview(
    iconSymbol: String,
    background: BackgroundColor,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        IconView(
            iconSymbol = iconSymbol,
            background = background,
            iconSize = IconSize.LARGE,
        )
    }
}

@Composable
private fun IconSymbolChooser(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        FormLabel(text = stringResource(R.string.iconEditor_iconSymbol_label))
    }
}

@Composable
private fun IconColorChooser(
    value: BackgroundColor,
    onChange: (BackgroundColor) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        FormLabel(text = stringResource(R.string.iconEditor_iconColor_label))
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Adaptive(IconSize.SMALL.shapeSize + 6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            items(BackgroundColor.entries, key = { it }) {
                Box {
                    IconView(
                        background = it,
                        iconSize = IconSize.SMALL,
                        modifier = Modifier.clickable { onChange(it) },
                    ) {
                        if (value == it) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = stringResource(R.string.iconEditor_iconColor_description),
                                modifier = Modifier.size(26.dp),
                                tint = Color.White,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@PreviewLightDark
private fun IconPreviewPreview() {
    PreviewContent {
        IconPreview("NP", BackgroundColor.BLUE)
    }
}

@Composable
@PreviewLightDark
fun IconColorChooserPreview() {
    PreviewContent {
        IconColorChooser(
            value = BackgroundColor.ZINC,
            onChange = {},
        )
    }
}

@Composable
@PreviewLightDark
private fun IconSymbolChooserPreview() {
    PreviewContent {
        IconSymbolChooser(
            value = "NP",
            onChange = {},
        )
    }
}

@Composable
private fun PreviewContent(content: @Composable () -> Unit) {
    PennywiseTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Box(
                modifier =
                    Modifier
                        .width(400.dp)
                        .padding(vertical = 16.dp),
            ) {
                content()
            }
        }
    }
}
