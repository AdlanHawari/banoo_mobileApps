package com.dev.banoo10.feature_calculatorList.presentation.calculator_details

import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dev.banoo10.calendar_view.presentation.CalendarViewComponent
import com.dev.banoo10.feature_calculatorList.presentation.component.TopBarCostum
import com.dev.banoo10.feature_calculatorList.presentation.get_calculatorList.CalcListViewModel

@Composable
fun CalcDetailsScreen(
    navController: NavController,
    viewModel: CalcDetailsViewModel = hiltViewModel()
) {

    val state = viewModel.calcDetailsState.value
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            TopBarCostum(
                title = state.name,
                actionButtonAsIcon = true,
                backButton = true,
                navController = navController
            )
        }

    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            Text(text = "mantab")
            if(state.isLoading){
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

            CalendarViewComponent()



        }

    }


}