package com.dev.banoo10.feature_calculatorList.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dev.banoo10.feature_auth.presentation.otp_form.OtpFormScreen
import com.dev.banoo10.ui.theme.Banoo10Theme

@Composable
fun CalcListScreen(
//    viewModel: CalcListViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Card(
//            elevation = 8.dp,
//            shape = CircleShape,
//            modifier = Modifier.size(100.dp)
//        ) {
//
//        }
        Box(
            modifier = Modifier.size(100.dp)
                .shadow(elevation = 8.dp, shape = CircleShape, clip = true)
                .background(color = Color.White)
        ) {

        }


    }


//    val calcState = viewModel.calcListState.value

//    Row(modifier = Modifier.fillMaxSize(),
//    verticalAlignment = Alignment.CenterVertically,
//    horizontalArrangement = Arrangement.Center) {
////        Text(text = "Calc List of ${calcState.tkn}")
//        Text(text = "Calc List Screen")
//
//    }

}

@Preview(showBackground = true)
@Composable
fun CalcListPreview() {
    Banoo10Theme {
        CalcListScreen()
    }

}