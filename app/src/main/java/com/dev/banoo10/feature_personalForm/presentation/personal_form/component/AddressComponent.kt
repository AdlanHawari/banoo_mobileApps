package com.dev.banoo10.feature_personalForm.presentation.personal_form.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddressComponent(
    question: String,
    text: String,
    focusState: Boolean,
    onValueChange: (String) -> Unit,
    onFilled: (address: String) -> Unit
) {

    LaunchedEffect(key1 = text){
        onFilled(text)
    }
    val focusRequester = FocusRequester()

    LaunchedEffect(key1 = focusState){
        if (focusState){
            focusRequester.requestFocus()
        }else{
            focusRequester.freeFocus()
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = question)
//        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.body2,
            singleLine = false,
            maxLines = 4,
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .focusRequester(focusRequester),
        )

    }

}