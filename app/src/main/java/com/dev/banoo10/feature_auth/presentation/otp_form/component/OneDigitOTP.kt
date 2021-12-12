package com.dev.banoo10.feature_auth.presentation.otp_form.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OneDigitOTP(
    text: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine : Boolean = false,
) {
    Card(modifier = Modifier
        .width(64.dp)
        .height(64.dp),
        //                .size(width = 200.dp, height = 40.dp),
        shape = RoundedCornerShape(20),
        elevation = 10.dp
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {
//            Text(text = "wew")
            BasicTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = text,
                onValueChange = onValueChange,
                singleLine = singleLine,
                textStyle = textStyle,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

//        Column(modifier = Modifier.padding(2.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//            ){
//            BasicTextField(
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                value = "9",
//                onValueChange = {},
//                singleLine = true,
//                textStyle = MaterialTheme.typography.h3,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .onFocusChanged {
////                    onFocusChange(it)
//                    }
//                ,
//
//            )
//        }
        
    }
    
}