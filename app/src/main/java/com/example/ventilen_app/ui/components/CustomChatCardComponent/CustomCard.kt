package com.example.ventilen_app.ui.components.CustomChatCardComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.theme.CustomColorScheme

/**
 * @author Chrsitian, Nikolaj, Marcus
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomCard(
    title: String = "København",
    bodyText: String = "Latest",
    abbreviation: String = "K",
    backgroundColor: Color = CustomColorScheme.Orange,
    hasRoundCorners: Boolean = true,
    cardModifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = cardModifier
            .fillMaxWidth(),
        onClick = onClick,
        shape = if (hasRoundCorners) RoundedCornerShape(12.dp) else RectangleShape
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .drawBehind {
                        drawCircle(
                            color = backgroundColor,
                            radius = this.size.height / 2
                        )
                    }
                    ,
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = abbreviation,
                    style = MaterialTheme.typography.displayLarge,
                    color = CustomColorScheme.OffWhite
                )
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = bodyText.take(30) + "...",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
