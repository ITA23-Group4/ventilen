package com.example.ventilen_app.ui.components


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomNewsCard(
    title: String,
    bodyText: String = "",
    abbreviation: String,
    backgroundColor: Color,
    hasRoundCorners: Boolean = true,
    // Expanded functions
    isExpanded: Boolean,
    onCardClick: () -> Unit,
    onDeleteNews: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        onClick = onCardClick,
        shape = if (hasRoundCorners) RoundedCornerShape(12.dp) else RectangleShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column(

            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .drawBehind {
                            drawCircle(
                                color = backgroundColor,
                                radius = this.size.height / 2
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = abbreviation,
                        style = MaterialTheme.typography.displayLarge,
                        color = CustomColorScheme.OffWhite
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.labelMedium
                    )

                    if (isExpanded) {
                        IconButton(onClick = onDeleteNews ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Remove news",
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }

                }

                // TODO: Should probably be hoisted :)
                if (isExpanded) {
                    Text(
                        text = bodyText,
                        style = MaterialTheme.typography.bodySmall
                    )
                } else {
                    Text(
                        text = bodyText.take(90) + "...",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

