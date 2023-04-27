package ir.wave.composecalculator.Utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(
    symbol: String,
    modifier: Modifier,
    color: Color ,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(3.dp)
            .clip(CircleShape)
            .clickable { onClick() }
            .then(modifier)
    ) {
        Text(
            text = symbol,
            fontSize = 26.sp,
            color = color
        )
    }
}

@Composable
fun PainterCalculatorButton(
    symbol: Painter,
    modifier: Modifier,
    color: Color = Color.White,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(3.dp)
            .clip(CircleShape)
            .clickable { onClick() }
            .then(modifier)
    ) {
        Image(
            painter = symbol,
            colorFilter = ColorFilter.tint(color),
            contentDescription = null
        )
    }
}