package com.dev.banoo10.feature_auth.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.dev.banoo10.R
import com.dev.banoo10.core.presentation.Screen
import com.dev.banoo10.feature_auth.presentation.phone_form.PhoneFormEvent
import com.dev.banoo10.feature_auth.presentation.phone_form.PhoneFormViewModel
import com.dev.banoo10.feature_auth.presentation.phone_form.component.CostumTextField
import com.dev.banoo10.ui.theme.Banoo10Theme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PhoneFormScreen(
    navController: NavController,
    viewModel: PhoneFormViewModel = hiltViewModel()
) {
    val phoneState = viewModel.phoneUser.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is PhoneFormViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is  PhoneFormViewModel.UiEvent.SendPhone -> {
                    Log.e("pf screen",event.phone)
                    navController.navigate(
                        Screen.OtpFormScreen.route + "/${event.phone}"
//                        Screen.OtpFormScreen.route + "/80909809"
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
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_teks),
                    contentDescription = "Otp Image",
                    modifier = Modifier
//                        .width(100.dp)
                        .height(40.dp)
                )
                Spacer(modifier = Modifier.height(40.dp))
                Text(text = "Masukkan nomor telepon")
                Spacer(modifier = Modifier.height(40.dp))
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),

                    //                .size(width = 200.dp, height = 40.dp),
                    shape = RoundedCornerShape(20),
                    elevation = 10.dp
                )
                {
                    Row( modifier = Modifier
                        //                    .height(IntrinsicSize.Min)
                        .fillMaxHeight()
                        .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){


                        Text(
                            text = "+62",
                            //                    style = MaterialTheme.typography.h2
                        )
                        Spacer(modifier = Modifier.width(10.dp))

                        Divider( modifier = Modifier
                            .fillMaxHeight()
                            .width(2.dp)
                            .padding(vertical = 10.dp)

                            //                    color = Color.LightGray
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        //                    BasicTextField(value = , onValueChange = )
                        //                    Text(text = "contoh: 824567819")
                        CostumTextField(
                            text = phoneState.text,
                            hint = phoneState.hint,
                            singleLine = true,
                            onValueChange = {
                                viewModel.onEvent(PhoneFormEvent.EnteredPhone(it))
                            },
                            onFocusChange = {
                                viewModel.onEvent(PhoneFormEvent.ChangePhoneFocus(it))
                            },
                            isHintVisible = phoneState.isHintVisible,
                            textStyle = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .fillMaxHeight(),


                            )
                    }

                }

                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    onClick = { viewModel.onEvent(PhoneFormEvent.SendPhone)},
                    modifier = Modifier.fillMaxWidth(),
                    enabled = phoneState.isButtonEnable

                ) {
                    Text(text = "Login")


                }

                Spacer(modifier = Modifier.height(100.dp))


            }

            if (phoneState.isLoading){
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




}


//@Preview(showBackground = true)
//@Composable
//fun PhonePreview() {
//    Banoo10Theme {
//        PhoneFormScreen(
//
//        )
//
//    }
//
//}
