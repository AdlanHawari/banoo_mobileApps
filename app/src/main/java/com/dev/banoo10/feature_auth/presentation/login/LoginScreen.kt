package com.dev.banoo10.feature_auth.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dev.banoo10.core.presentation.Screen

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {


//    val screenState = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true){

        if (viewModel.state.value.token == "-1"){
            navController.navigate(
                Screen.PhoneFormScreen.route
            )

        }else{
            navController.navigate(
                Screen.CalcListScreen.route + "/${viewModel.state.value.token}"
            )
        }

//        viewModel.eventFlow.collectLatest { event ->
//            when(event){
//                is LoginViewModel.UiEvent.ShowSnackbar -> {
//                    scaffoldState.snackbarHostState.showSnackbar(
//                        message = event.message
//                    )
//                }
//                is LoginViewModel.UiEvent.NextScreen -> {
//                    navController.navigate(
//                        Screen.PhoneFormScreen.route
//                    )
//                }
//            }
//        }
    }

    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()

//            Button(
//                onClick = {viewModel.onEvent(LoginEvent.LoginClicked)},
//                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
//            ) {
//                Text(text = "Login", color = Color.White)
//
//
//            }

//            Button(
//                onClick = {viewModel.onEvent(LoginEvent.LoginClicked)},
//                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
//            ) {
//                Text(text = "Login", color = Color.White)
//
//
//            }
//            Spacer(modifier = Modifier.height(40.dp))
//            Button(
//                onClick = {viewModel.onEvent(LoginEvent.ShowClicked)}) {
//                Text(text = "Show")
//
//            }
//            Spacer(modifier = Modifier.height(40.dp))
//            Button(onClick = {viewModel.onEvent(LoginEvent.LogoutClicked)},
//                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
//            ) {
//                Text(text = "Logout", color = Color.White)
//
//            }

        
    }
    
}
//
//@Preview(showBackground = true)
//@Composable
//fun LoginPreview() {
//    Banoo10Theme {
//        LoginScreen()
//
//    }
//
//}
