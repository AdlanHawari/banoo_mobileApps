package com.dev.banoo10.feature_calculatorList.presentation.get_calculatorList

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dev.banoo10.core.presentation.Screen
import com.dev.banoo10.feature_calculatorList.presentation.component.TopBarCostum
import com.dev.banoo10.feature_calculatorList.presentation.get_calculatorList.component.CalculatorComponent
import com.dev.banoo10.ui.theme.Banoo10Theme
import com.dev.banoo10.ui.theme.Cyan600
import com.dev.banoo10.ui.theme.Cyan900
import com.dev.banoo10.ui.theme.Red
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CalcListScreen(
    navController: NavController,
    viewModel: CalcListViewModel = hiltViewModel()
) {
    val state = viewModel.calcListState.value
    val scaffoldState = rememberScaffoldState()
    val openDialog = remember { mutableStateOf(false)}

    var selectedCalcName by remember { mutableStateOf("")}
    var selectedCalcId by remember { mutableStateOf("")}

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is CalcListViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is CalcListViewModel.UiEvent.CalcDetails -> {

                }

                is CalcListViewModel.UiEvent.AddCalculator -> {
                    navController.navigate(Screen.AddCalculatorScreen.route)
                }

                is CalcListViewModel.UiEvent.ToAccount -> {
                    navController.navigate(Screen.AccountScreen.route)
                }
            }
        }

    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
                 TopBarCostum(
                     title = state.topBarTitle,
                     actionButtonAsIcon = !state.isDeleteMenu,
                     dropDownOptions = state.dropdownItem,
                     onClickDropDownItem = listOf(
                         {viewModel.onEvent(CalcListEvent.ToAccountEvent)},
                         {viewModel.onEvent(CalcListEvent.DeleteCalc)}
                     ),
                     actionButtonAsText = state.isDeleteMenu,
                     actionButtonText = "BATALKAN",
                     onClickTextButton = {viewModel.onEvent(CalcListEvent.CancelDeleteCalc) },
                     backgroundColor = if (state.isDeleteMenu) Red
                            else MaterialTheme.colors.primary,
                     contentColor = Color.White,

                 )
        },
        floatingActionButton =  {
            if(!state.isDeleteMenu){
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

        }

    ) {
        Box(
            modifier = Modifier.fillMaxSize()

        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                ,
            ){
                items( state.calculators){ calculator ->
//                    Log.e("${calculator.feedcalcName} istarted",calculator.isStarted.toString())

                    CalculatorComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            .clickable {
                                if (!state.isDeleteMenu) {
                                    navController.navigate(
                                        Screen.CalcDetailsScreen.route + "/${calculator.calcId}"
                                    )
                                }
//                                else {
//                                    selectedCalc = calculator.feedcalcName
//                                    openDialog.value = true
//                                }
                            },
                        name = calculator.feedcalcName,
                        timeText = if (calculator.isStarted) {
                                        if (calculator.isDone) {
                                            "Sudah selesai"
                                        } else {
                                            "Hari ke ${calculator.deltaDay}"
                                        }
                                    } else "Dimulai ${calculator.deltaDay} hari lagi",
                        textColor = if (!calculator.isDone) Color.Black
                        else Color.LightGray,
                        isDelete = state.isDeleteMenu,
                        onDelete = {
                            selectedCalcName = calculator.feedcalcName
                            selectedCalcId = calculator.calcId
                            openDialog.value = true
//                            viewModel.onEvent(CalcListEvent.DeleteSelectedCalc(calculator.calcId))
                        }
                    )

                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier
                            .padding(10.dp)
                    )

                }
            }
//            Column(
//                modifier = Modifier.fillMaxSize(),
////            verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
////                Text(text = "Anda belum memiliki jadwal pakan. Buat sekarang ")
//
//
//            }


        }



    }

    if (openDialog.value){
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = {
                    Text(
                        text = "Perhatian",
                        style = MaterialTheme.typography.h6
                    )
            },
            text = {
//                Text(text = "Anda akan menghapus $selectedCalcName. Apakah anda yakin?")
                Text(
                    text = buildAnnotatedString {
                        append("Anda akan menghapus ")
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                        ){
                            append(selectedCalcName)
                        }
                        append(". Apakah anda yakin?")
                    },
                    style = MaterialTheme.typography.body1
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        viewModel.onEvent(CalcListEvent.DeleteSelectedCalc(selectedCalcId))
                    }) {
                    Text(text = "Ya, hapus",
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Bold,
                            color = Red

                        ))
                }

            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }) {
                    Text(text = "Batal",
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Bold,
                            color = Cyan900

                        )
                    )
                }
            },
        )
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