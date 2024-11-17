import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomCard(
    borderColor: Color = Color(0xFF977CB1),
    backgroundColor: Color = Color(0xFFF8F6FF),
    contentPadding: Dp = 16.dp,
    borderWidth: Dp = 1.dp,
    isDashed: Boolean = false,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = backgroundColor),
            modifier = Modifier
                .padding(borderWidth)
                .then(
                    if (isDashed) Modifier else Modifier.border(
                        BorderStroke(borderWidth, borderColor),
                        shape = RoundedCornerShape(8.dp)
                    )
                )
        ) {
            Box(modifier = Modifier.padding(contentPadding)) {
                content()
            }
        }

        if (isDashed) {
            Canvas(modifier = Modifier.matchParentSize()) {
                val stroke = Stroke(
                    width = borderWidth.toPx(),
                    pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(
                        floatArrayOf(20f, 10f), 0f
                    )
                )
                drawRoundRect(
                    color = borderColor,
                    size = size,
                    style = stroke,
                    cornerRadius = CornerRadius(8.dp.toPx())
                )
            }
        }
    }
}
