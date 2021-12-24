package com.dev.banoo10.feature_calculatorList.presentation.calculator_details.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dev.banoo10.feature_calculatorList.presentation.get_calculatorList.CalcListViewModel

@Composable
fun CalcDetailsScreen(
    navController: NavController,
    viewModel: CalcDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.calcDetailsState.value

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = state.id)
        
    }

}