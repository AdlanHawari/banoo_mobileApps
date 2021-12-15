package com.dev.banoo10.feature_personalForm.presentation.personal_form.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier

@Composable
fun NameComponent(
    subtitle: String,
    text: String,
    onValueChange: (String) -> Unit,
    onFilled: (gender: String) -> Unit
) {

    LaunchedEffect(key1 = text){
        onFilled(text)
    }

    Column() {
        Text(text = subtitle)
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.body2,
            singleLine = true,
            maxLines = 1,
        )
    }

}