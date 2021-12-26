package com.dev.banoo10.core.presentation

sealed class Screen(val route: String){
    object LoginScreen: Screen("login_screen")
    object PhoneFormScreen: Screen("phoneform_screen")
    object ExampleScreen: Screen("example_screen")
    object OtpFormScreen: Screen("otpform_screen")
    object CalcListScreen: Screen("calclist_screen")
    object WelcomeScreen: Screen("welcome_screen")
    object DeleteScreen: Screen("delete_screen")
    object PersonalFormScreen: Screen("personalform_screen")
    object AddCalculatorScreen: Screen("addcalculator_screen")
    object CalcDetailsScreen: Screen("calculatordetails_screen")
    object AccountScreen: Screen("account_screen")
    object CalendarScreen: Screen("calendar_screen")
}
