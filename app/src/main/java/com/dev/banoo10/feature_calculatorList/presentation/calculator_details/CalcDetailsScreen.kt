package com.dev.banoo10.feature_calculatorList.presentation.calculator_details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dev.banoo10.calendar_view.presentation.CalendarComponent
import com.dev.banoo10.calendar_view.presentation.CalendarViewComponent
import com.dev.banoo10.feature_calculatorList.presentation.component.TopBarCostum
import com.dev.banoo10.ui.theme.Cyan800
import com.dev.banoo10.ui.theme.Red
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.collectLatest

//@ExperimentalComposeUiApi
//@ExperimentalPagerApi
@ExperimentalPagerApi
@Composable
fun CalcDetailsScreen(
    navController: NavController,
    viewModel: CalcDetailsViewModel = hiltViewModel()
) {

    val state = viewModel.calcDetailsState.value

    val scaffoldState = rememberScaffoldState()
    val pagerState = rememberPagerState()

    LaunchedEffect(key1 = true){
//        Log.e("startAt","hahahah")
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is CalcDetailsViewModel.UiEvent.ShowSnackbar -> {
                    Log.e("ef", "mashok")
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
//
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarCostum(
                title = state.name,
//                title = "ujang",
                actionButtonAsIcon = true,
                backButton = true,
                navController = navController
            )
        }

    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
//            Text(text = "mantab")
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

            Column() {
//                CalendarViewComponent(startAt = state.calendarList[3])

                HorizontalPager(
                    count = state.calendarList.size,
                    state = pagerState
                ) { page ->
//                    Column(){

                    CalendarComponent(
                        calendarString = state.calendarList[page],
                        backgroundColor = if (currentPage%2>0) Cyan800
                    else Color.LightGray

                    )
//                    Spacer(modifier = Modifier.height(40.dp))
//                    Text(text = "Page $page")
//                    }
                }
//                state.calendarList.forEachIndexed { index, it ->
//                    CalendarComponent(
//                        calendarString = it,
//                        backgroundColor = if(index%2>0) Cyan800
//                        else Red)
//                    Text(text = "this is $it")
//                }
//                state.calendarList.forEachIndexed{ index, it ->
//                    Log.e("it",it)
//                    CalendarComponent(calendarString = it, backgroundColor = Cyan800)
//
//                }




                Spacer(modifier = Modifier.height(40.dp))
                Text(text = state.startAt)

            }






        }

    }


}