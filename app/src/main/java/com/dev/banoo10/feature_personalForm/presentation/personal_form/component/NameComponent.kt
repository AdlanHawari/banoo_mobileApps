package com.dev.banoo10.feature_personalForm.presentation.personal_form.component

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp

@Composable
fun NameComponent(
    subtitle: String,
    text: String,
    focusState: Boolean,
    onValueChange: (String) -> Unit,
    onFilled: (gender: String) -> Unit
) {

    val focusRequester = FocusRequester()

    LaunchedEffect(key1 = text){
        onFilled(text)
    }

    LaunchedEffect(key1 = focusState){
        if (focusState){
            focusRequester.requestFocus()
        }else{
            focusRequester.freeFocus()
        }
    }

    LaunchedEffect(key1 = true){
//        focusRequester.requestFocus()
        Log.e("page name","rendered")
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = subtitle)
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .focusRequester(focusRequester)
                .fillMaxWidth(),
            value = text,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.body2,
            singleLine = true,
            maxLines = 1,
        )
    }

}