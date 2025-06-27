package io.papahigh.pennywise.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.papahgh.pennywise.data.model.now
import io.papahigh.pennywise.theme.PennywiseTheme
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.char

@Composable
fun Time(
    value: LocalDateTime,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.secondary,
    style: TextStyle = LocalTextStyle.current,
) {
    val formatted =
        remember(value) {
            value.format(Formats.TIME_FORMAT)
        }
    Text(
        text = formatted,
        modifier = modifier,
        color = color,
        style = style,
    )
}

private object Formats {
    val TIME_FORMAT by lazy {
        LocalDateTime.Format {
            hour()
            char(':')
            minute()
        }
    }
}

@Composable
@PreviewLightDark
private fun TimePreview() {
    PennywiseTheme {
        Time(LocalDateTime.now())
    }
}
