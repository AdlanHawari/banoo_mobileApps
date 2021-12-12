package com.dev.banoo10.feature_auth.presentation.otp_form

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dev.banoo10.core.presentation.Screen
import com.dev.banoo10.feature_auth.presentation.otp_form.component.OTPTextField
import com.dev.banoo10.feature_auth.presentation.otp_form.component.OneDigitOTP
import com.dev.banoo10.feature_auth.presentation.phone_form.PhoneFormEvent
import com.dev.banoo10.feature_auth.presentation.phone_form.PhoneFormViewModel
import com.dev.banoo10.ui.theme.Banoo10Theme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OtpFormScreen(
    navController: NavController,
    viewModel: OtpFormViewModel = hiltViewModel()
) {

    val otpState = viewModel.otpFormState.value
    val scaffoldState = rememberScaffoldState()
    val otpVal: String? = null

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is OtpFormViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is  OtpFormViewModel.UiEvent.SendOTP -> {
                    navController.navigate(
                        Screen.CalcListScreen.route +"/${event.token}"
//                        Screen.OtpFormScreen.route + "/80909809"
                    )
                }
                is OtpFormViewModel.UiEvent.ToPersonalForm -> {
                    navController.navigate(
                        Screen.WelcomeScreen.route
                    )
                }
            }
        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text = "Kode OTP dikirimkan ke +62${otpState.phone} melalui SMS.")
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Masukkan OTP untuk verifikasi nomor anda")
                Spacer(modifier = Modifier.height(40.dp))

                Row() {
                    
//                    OneDigitOTP(
//                        text = "8",
//                        onValueChange = {},
//                        textStyle = TextStyle(
//                            textAlign = TextAlign.Center,
//                            fontSize = 40.sp
//                        ),
//                        singleLine = true,
//
//
//                    )
//                    Spacer(Modifier.width(10.dp))
//                    OneDigitOTP()
//                    Spacer(Modifier.width(10.dp))
//                    OneDigitOTP()
//                    Spacer(Modifier.width(10.dp))
//                    OneDigitOTP()

                }
                
                OTPTextField(length = 6){
                    getOpt -> otpVal
                    viewModel.onEvent(OtpFormEvent.EnteredOTP(getOpt))
//                    Log.e("otp",getOpt)
                }

                Spacer(modifier = Modifier.height(40.dp))

                Row(horizontalArrangement = Arrangement.Start){
                    Text(text = "Kirim ulang OTP")
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = otpState.time,
                        Modifier.clickable {
                            if (otpState.isTimerFinish) {
                                viewModel.onEvent(OtpFormEvent.ResendOTP)
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false

                ) {
                    Text(text = "Kirim")


                }

            }

            if (otpState.isLoading){
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray.copy(alpha = .6f)),
                    contentAlignment = Alignment.Center
                ) {
                    Box(modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                    ){
                        CircularProgressIndicator(modifier = Modifier
                            .align(Alignment.Center)
                            .padding(20.dp))
                    }


                }

            }
        }
    }

//    Row(modifier = Modifier
//        .fillMaxSize(),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.Center
//        ) {
//        Text(text = "OTP Form")
//
//    }
}

//@Preview(showBackground = true)
//@Composable
//fun OTPPreview() {
//    Banoo10Theme {
//        OtpFormScreen(
//
//        )
//
//    }
//
//}