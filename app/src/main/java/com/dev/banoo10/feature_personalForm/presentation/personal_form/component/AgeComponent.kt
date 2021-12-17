package com.dev.banoo10.feature_personalForm.presentation.personal_form.component

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AgeComponent(
    question: String,
    text: String,
    focusState: Boolean,
    onValueChange: (String) -> Unit,
    onFilled: (age: String) -> Unit

) {
    val focusRequester = FocusRequester()

    LaunchedEffect(key1 = focusState){
        if (focusState){
            focusRequester.requestFocus()
        }else{
            focusRequester.freeFocus()
        }
    }

    LaunchedEffect(key1 = text){
        onFilled(text)
    }
    LaunchedEffect(key1 = true){
//        focusRequester.requestFocus()
        Log.e("page age","rendered")
    }

//    DisposableEffect(Unit) {
//        focusRequester.requestFocus()
//        onDispose { }
//    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = question)
        Spacer(modifier = Modifier.height(40.dp))

        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier
                .width(60.dp)
                .drawBehind {
                    val strokeWidth = Stroke.DefaultMiter
                    val y = size.height
                    //                val y = size.height - strokeWidth / 2

                    drawLine(
                        Color.Gray,
                        Offset(0f, y),
                        Offset(size.width, y),
                        strokeWidth
                    )
                }


            ){
                BasicTextField(

                    value = text,
                    onValueChange = onValueChange,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5.copy(textAlign = TextAlign.Center),
                    modifier = Modifier
                        .fillMaxWidth()

                        .focusRequester(focusRequester),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "tahun")

        }

    }

}