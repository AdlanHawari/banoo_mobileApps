package com.dev.banoo10.feature_delete.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dev.banoo10.feature_auth.presentation.login.LoginViewModel
import com.dev.banoo10.ui.theme.Banoo10Theme

@Composable
fun DeleteScreen(
    viewModel: DeleteViewModel = hiltViewModel()
) {


    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

            Button(
                onClick = {viewModel.onEvent(DeleteEvent.ShowClicked)}) {
                Text(text = "Show")

            }
            Spacer(modifier = Modifier.height(40.dp))
            Button(onClick = {viewModel.onEvent(DeleteEvent.LogoutClicked)},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
            ) {
                Text(text = "Logout", color = Color.White)

            }


    }
//


}

@Preview(showBackground = true)
@Composable
fun DeleteScreenPreview() {
    Banoo10Theme {
        DeleteScreen()

    }

}