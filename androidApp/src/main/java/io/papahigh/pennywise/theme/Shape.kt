package io.papahigh.pennywise.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val shapes =
    Shapes(
        extraSmall = CutCornerShape(6.dp),
        small = RoundedCornerShape(8.dp),
        medium = RoundedCornerShape(14.dp),
        large = CutCornerShape(18.dp),
        extraLarge = RoundedCornerShape(24.dp),
    )
