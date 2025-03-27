package nfv.ui_kit.components.buttons.keypad.keypad

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun CredentialDot(
    modifier: Modifier = Modifier,
    isFilled: Boolean
) {
    val strokeColor by animateColorAsState(
        targetValue = if (isFilled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
        animationSpec = tween(500)
    )
    val strokeWidth by animateDpAsState(
        targetValue = if (isFilled) 10.dp else 2.dp,
        animationSpec = tween(500)
    )

    Box(
        modifier = modifier
            .size(20.dp)
            .clip(CircleShape)
            .border(width = strokeWidth, color = strokeColor, shape = CircleShape)
    )
}