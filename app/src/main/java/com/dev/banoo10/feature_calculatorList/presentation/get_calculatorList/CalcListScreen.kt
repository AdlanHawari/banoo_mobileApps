package com.dev.banoo10.feature_calculatorList.presentation.get_calculatorList

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dev.banoo10.core.presentation.Screen
import com.dev.banoo10.feature_calculatorList.presentation.component.TopBarCostum
import com.dev.banoo10.ui.theme.Banoo10Theme
import com.dev.banoo10.ui.theme.Cyan600
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CalcListScreen(
    navController: NavController,
    viewModel: CalcListViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is CalcListViewModel.UiEvent.ShowSnackbar -> {

                }

                is CalcListViewModel.UiEvent.CalcDetails -> {

                }

                is CalcListViewModel.UiEvent.AddCalculator -> {
                    navController.navigate(Screen.AddCalculatorScreen.route)
                }
            }
        }

    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
                 TopBarCostum(
                     title = "Daftar Budidaya Anda",
                     actionButton = true
                 )
//            CustomTopBar(
//                backButton = false,
//                title = "Daftar Budidaya Anda",
//                rightIcon = Icons.Default.Menu,
//
//            )
        },
        floatingActionButton =  {
            FloatingActionButton(
                onClick = {
                          viewModel.onEvent(CalcListEvent.AddCalc)
                },
                backgroundColor = Cyan600

            ) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = "add calc",
                    tint = Color.White
                )

            }
        }

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Anda belum memiliki jadwal pakan. Buat sekarang ")


        }


    }

}

//@Preview(showBackground = true)
//@Composable
//fun CalcListPreview() {
//    Banoo10Theme {
//        CalcListScreen(
//
//        )
//    }
//
//}