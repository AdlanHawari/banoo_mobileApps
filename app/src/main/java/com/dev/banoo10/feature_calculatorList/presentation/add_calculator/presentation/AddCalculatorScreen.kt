package com.dev.banoo10.feature_calculatorList.presentation.add_calculator.presentation

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.dev.banoo10.feature_calculatorList.presentation.component.TopBarCostum
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.flow.collectLatest
import kotlin.coroutines.coroutineContext

@Composable
fun AddCalculatorScreen(
    navController: NavController,
    viewModel: AddCalculatorViewModel = hiltViewModel()
) {
    val scafffoldState = rememberScaffoldState()
    val state = viewModel.addCalcstate.value
    val context = LocalContext.current

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
                is AddCalculatorViewModel.UiEvent.ShowDatePicker -> {
                    datePickerDialog.show()

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
            modifier = Modifier.fillMaxSize()
        ) {
            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp, vertical = 40.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start

            ) {
                Text(text = "Nama budidaya")
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = {})
                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Deskripsi")
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = {})
                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Berat tebar")
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = {})
                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Dosis pakan")
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Jadwal tebar")
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Selected Date: ${state.date}")
                Button(onClick = {
                    viewModel.onEvent(AddCalculatorEvent.DatePickerPressed)
                }) {
                    Text(text = "Set Date")
                }

                Button(onClick = {
                    viewModel.onEvent(AddCalculatorEvent.CreateCalculation)
                }) {
                    Text(text = "Buat perhitungan pakan")
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