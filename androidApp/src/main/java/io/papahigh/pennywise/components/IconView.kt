package io.papahigh.pennywise.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.papahgh.pennywise.data.model.BackgroundColor
import io.papahgh.pennywise.data.model.IconModel
import io.papahigh.pennywise.theme.PennywiseTheme

@Composable
fun IconView(
    iconModel: IconModel,
    modifier: Modifier = Modifier,
    iconSize: IconSize = IconSize.MEDIUM,
) {
    IconView(
        iconSymbol = iconModel.iconSymbol,
        background = iconModel.background,
        modifier = modifier,
        iconSize = iconSize,
    )
}

@Composable
fun IconView(
    modifier: Modifier = Modifier,
    background: BackgroundColor,
    iconSymbol: String = "",
    iconSize: IconSize = IconSize.MEDIUM,
    content: @Composable BoxScope.() -> Unit = { IconText(iconSymbol, iconSize) },
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .size(iconSize.shapeSize)
                .clip(CircleShape)
                .background(Color(background.value))
                .drawBehind { drawCircle(iconGlowBrush) },
    ) {
        content()
    }
}

@Composable
private fun IconText(
    iconSymbol: String,
    iconSize: IconSize,
) {
    Text(
        text = iconSymbol,
        color = Color.White,
        fontWeight = FontWeight.SemiBold,
        fontSize = iconSize.fontSize,
    )
}

enum class IconSize(
    val shapeSize: Dp,
    val fontSize: TextUnit,
) {
    SMALL(44.dp, 16.sp),
    MEDIUM(52.dp, 20.sp),
    LARGE(128.dp, 48.sp),
}

private val iconGlowBrush =
    Brush.verticalGradient(
        listOf(Color(0x65ffffff), Color.Transparent),
    )

@Composable
@PreviewLightDark
private fun IconViewSmallPreview() {
    PennywiseTheme {
        LazyVerticalGrid(
            columns = GridCells.Fixed(8),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            items(BackgroundColor.entries) {
                Box {
                    IconView(
                        iconSymbol = "NP",
                        background = it,
                        iconSize = IconSize.SMALL,
                    )
                }
            }
        }
    }
}

@Composable
@PreviewLightDark
private fun IconViewMediumPreview() {
    PennywiseTheme {
        LazyVerticalGrid(
            columns = GridCells.Fixed(6),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            items(BackgroundColor.entries) {
                Box {
                    IconView(
                        iconSymbol = "NP",
                        background = it,
                        iconSize = IconSize.MEDIUM,
                    )
                }
            }
        }
    }
}

@Composable
@PreviewLightDark
private fun IconViewLargePreview() {
    PennywiseTheme {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            items(BackgroundColor.entries) {
                Box {
                    IconView(
                        iconSymbol = "NP",
                        background = it,
                        iconSize = IconSize.LARGE,
                    )
                }
            }
        }
    }
}
