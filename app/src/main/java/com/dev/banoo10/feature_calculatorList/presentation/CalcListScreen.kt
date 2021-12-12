package com.dev.banoo10.feature_calculatorList.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dev.banoo10.feature_auth.presentation.otp_form.OtpFormScreen
import com.dev.banoo10.ui.theme.Banoo10Theme

@Composable
fun CalcListScreen(
    viewModel: CalcListViewModel = hiltViewModel()
) {

    val calcState = viewModel.calcListState.value

    Row(modifier = Modifier.fillMaxSize(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center) {
        Text(text = "Calc List of ${calcState.tkn}")

    }

}

@Preview(showBackground = true)
@Composable
fun CalcListPreview() {
    Banoo10Theme {
        CalcListScreen(

        )

    }

}