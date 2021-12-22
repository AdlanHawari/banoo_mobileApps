package com.dev.banoo10.feature_calculatorList.presentation.add_calculator.presentation

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dev.banoo10.core.presentation.Screen
import com.dev.banoo10.feature_calculatorList.presentation.add_calculator.presentation.component.DropDownComponent
import com.dev.banoo10.feature_calculatorList.presentation.component.TopBarCostum
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddCalculatorScreen(
    navController: NavController,
    viewModel: AddCalculatorViewModel = hiltViewModel()
) {
    val scafffoldState = rememberScaffoldState()
    val state = viewModel.addCalcstate.value
    val context = LocalContext.current

    val space = 20

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val mMonth: String
            val mDay: String
            if(month<10) mMonth = "0${month+1}"
            else mMonth = (month+1).toString()
            if(dayOfMonth<10) mDay = "0$dayOfMonth"
            else mDay = dayOfMonth.toString()
            viewModel.onEvent(AddCalculatorEvent.SetDateStart("$mDay/$mMonth/$year"))
        }, state.year, state.month, state.day
    )

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is AddCalculatorViewModel.UiEvent.ShowSnackbar -> {
                    scafffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is AddCalculatorViewModel.UiEvent.ShowDatePicker -> {
                    datePickerDialog.show()

                }

                is AddCalculatorViewModel.UiEvent.ToDetailScreen ->{
                    navController.navigate(
                        Screen.CalcDetailsScreen.route + "/${event.calcId}"
                    )
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scafffoldState,
        topBar = {
            TopBarCostum(
                backButton = true,
                title = "Tambah Jadwal Pakan Baru",
                backgroundColor = Color.White,
                contentColor = Color.Black,
                navController = navController

            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp, vertical = 40.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start

            ) {
                Text(text = state.cult_name.label)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.cult_name.value,
                    onValueChange = {
                        if (it.length<35)viewModel.onEvent(AddCalculatorEvent.EnteredNamaBudidaya(it))
                    },
                    singleLine = true)
                Spacer(modifier = Modifier.height(space.dp))

                DropDownComponent(
                    label = state.spesies.label,
                    isToggled = false,
                    placeholder = state.spesies.placeholder,
                    options = state.spesies.options,
                    onSelectedChange = ({viewModel.onEvent(AddCalculatorEvent.EnteredSpesies(it))})
                )

                Spacer(modifier = Modifier.height(space.dp))

                Text(text = state.berat_tebar.label)
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                    
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(.4f),
                        value = state.berat_tebar.value,
                        onValueChange = {
                            viewModel.onEvent(AddCalculatorEvent.EnteredBeratTebar(it))
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "kg")
                }
                Spacer(modifier = Modifier.height(space.dp))

                DropDownComponent(
                    label = state.dosis.label,
                    isToggled = false,
                    placeholder = state.dosis.placeholder,
                    options = state.dosis.options,
                    onSelectedChange = ({viewModel.onEvent(AddCalculatorEvent.EnteredDosis(it))})
                )
                Spacer(modifier = Modifier.height(space.dp))

                Text(text = state.jadwal.label)
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(text = state.date)
                    Spacer(modifier = Modifier.width(10.dp))

                    Box(
                        modifier = Modifier
//                            .size(width = 60.dp, height = 40.dp)
                            .border(
                                color = Color.LightGray,
                                width = 2.dp,
                                shape = MaterialTheme.shapes.small
                            )
                            .clickable {
                                viewModel.onEvent(AddCalculatorEvent.DatePickerPressed)
                            }
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                    ) {
                        Row(
//                            modifier = Modifier.fillMaxSize(),
//                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(imageVector = Icons.Default.DateRange, contentDescription = "calendar icon")
//                                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "dropdown icon")
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "dropdown icon")

                        }

                    }
//                    Button(onClick = {
//
//                    }) {
//                        Text(text = "Set Date")
//                    }
                }
                Spacer(modifier = Modifier.height(40.dp))


                Button(
                    modifier = Modifier
                        .fillMaxWidth(.7f)
                        .align(CenterHorizontally)
                    ,

                    onClick = {
                        viewModel.onEvent(AddCalculatorEvent.CreateCalculation)
                    },
                    enabled = if (state.cult_name.value.isNotEmpty() && state.berat_tebar.value.isNotEmpty() && state.dosis.value.isNotEmpty() && state.date.isNotEmpty())true
                    else false
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = "Buat perhitungan pakan",
                        style = MaterialTheme.typography.button
                    )
                }
            }

            if (state.isLoading){
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
//
//
//@Preview(showBackground = true)
//@Composable
//fun AddPreview() {
//    Banoo10Theme {
//        AddCalculatorScreen()
//    }
//
//}