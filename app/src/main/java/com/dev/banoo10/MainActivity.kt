package com.dev.banoo10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dev.banoo10.calendar_view.presentation.CalendarViewComponent
import com.dev.banoo10.core.presentation.Screen
import com.dev.banoo10.feature_account.presentation.AccountScreen
import com.dev.banoo10.feature_auth.presentation.ExampleScreen
import com.dev.banoo10.feature_auth.presentation.login.LoginScreen
import com.dev.banoo10.feature_auth.presentation.PhoneFormScreen
import com.dev.banoo10.feature_auth.presentation.otp_form.OtpFormScreen
import com.dev.banoo10.feature_calculatorList.presentation.add_calculator.AddCalculatorScreen
import com.dev.banoo10.feature_calculatorList.presentation.calculator_details.CalcDetailsScreen
import com.dev.banoo10.feature_calculatorList.presentation.get_calculatorList.CalcListScreen
import com.dev.banoo10.feature_delete.presentation.DeleteScreen
import com.dev.banoo10.feature_personalForm.presentation.WelcomeScreen
import com.dev.banoo10.feature_personalForm.presentation.personal_form.PersonalFormScreen
import com.dev.banoo10.ui.theme.Banoo10Theme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_Banoo10)
        setContent {
            Banoo10Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
//                        startDestination = Screen.CalendarScreen.route
                        startDestination = Screen.LoginScreen.route
//                        startDestination = Screen.PhoneFormScreen.route
//                        startDestination = Screen.DeleteScreen.route
//                        startDestination = Screen.OtpFormScreen.route
//                        startDestination = Screen.WelcomeScreen.route
//                        startDestination = Screen.PersonalFormScreen.route
//                          startDestination = Screen.CalcListScreen.route
//                          startDestination = Screen.AddCalculatorScreen.route
//                        startDestination = Screen.ExampleScreen.route
                    ){
                        composable(
                            route = Screen.LoginScreen.route
                        ){
                            LoginScreen(navController = navController)
                        }
                        composable(
                            route = Screen.PhoneFormScreen.route
                        ){
                            PhoneFormScreen(navController = navController)
                        }
                        composable(
                            route = Screen.OtpFormScreen.route + "/{phone}"
                        ){
                            OtpFormScreen(navController = navController)
                        }
                        composable(
                            route = Screen.ExampleScreen.route
                        ){
                            ExampleScreen()
                        }
                        composable(
//                            route = Screen.CalcListScreen.route + "/{token}"
                            route = Screen.CalcListScreen.route
                        ){
                            CalcListScreen(navController = navController)
                        }
                        composable(
                            route = Screen.WelcomeScreen.route
                        ){
                            WelcomeScreen(navController = navController)
                        }
                        composable(
                            route = Screen.PersonalFormScreen.route
                        ){
                            PersonalFormScreen(navController = navController)
                        }
                        composable(
                            route = Screen.DeleteScreen.route
                        ){
                            DeleteScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddCalculatorScreen.route
                        ){
                            AddCalculatorScreen(navController = navController)
                        }
                        composable(
                            route = Screen.CalcDetailsScreen.route + "/{calcId}"
                        ){
                            CalcDetailsScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AccountScreen.route
                        ){
                            AccountScreen()
                        }
//                        composable(
//                            route = Screen.CalendarScreen.route
//                        ){
//                            CalendarViewComponent(
//                            )
//                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Banoo10Theme {
        Greeting("Android")
    }
}