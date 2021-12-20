package com.dev.banoo10.feature_delete.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dev.banoo10.core.presentation.Screen
import com.dev.banoo10.feature_auth.presentation.login.LoginViewModel
import com.dev.banoo10.feature_personalForm.presentation.personal_form.PersonalFormViewModel
import com.dev.banoo10.ui.theme.Banoo10Theme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DeleteScreen(
    navController: NavController,
    viewModel: DeleteViewModel = hiltViewModel()
) {


    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true){
//        Log.e("isFilled", isFilled.toString())
        scaffoldState.snackbarHostState.showSnackbar(
            message = "asu"
        )
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is DeleteViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is DeleteViewModel.UiEvent.LoggedOut -> {
                    navController.navigate(Screen.PhoneFormScreen.route)
                }
            }

        }
    }

    Scaffold(
        scaffoldState = scaffoldState

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
            Spacer(modifier = Modifier.height(40.dp))
            Button(onClick = {viewModel.onEvent(DeleteEvent.ProfileClicked)},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Magenta)
            ) {
                Text(text = "Profile", color = Color.White)

            }


        }

    }


//


}

//@Preview(showBackground = true)
//@Composable
//fun DeleteScreenPreview() {
//    Banoo10Theme {
//        DeleteScreen()
//
//    }
//
//}