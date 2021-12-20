package com.dev.banoo10.feature_calculatorList.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun CustomTopBar(
    backgroundColor: Color = MaterialTheme.colors.primary,
    backButton: Boolean = false,
    title: String = "",
    rightIcon: ImageVector,
    rightText: String = "",
    textStyle: TextStyle = MaterialTheme.typography.h5
) {
    Box(
        modifier = Modifier
//            .padding(horizontal = 4.dp)
            .height(60.dp)
            .background(color = backgroundColor)
            .fillMaxWidth(),
    ) {
//        Row(
//
//        ) {
//            if (backButton){
//                Icon(imageVector = Icons.Default.ArrowBack,
//                    contentDescription = "back arrow")
//            }
//
//            Text(
//                text = title,
//            )
//            if (rightIcon != null){
//                Icon(imageVector = rightIcon,
//                    contentDescription = "menu icon")
//
//            }
//
//        }



    }


}