package com.dev.banoo10.feature_calculatorList.presentation.get_calculatorList.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorComponent(
    name: String,
    timeText: String,
    textColor: Color,
    modifier: Modifier,
    isDelete: Boolean = false,
    onDelete: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.body1.copy(
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = timeText,
                style = MaterialTheme.typography.body2.copy(
                    color = textColor,
                    fontStyle = FontStyle.Italic
                )
            )
        }
        if (isDelete){
            IconButton(onClick = {
                onDelete()
            }
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "icon delete")
            }

        }
    }
    
    
    
}