package com.dev.banoo10.feature_calculatorList.presentation.calculator_details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dev.banoo10.calendar_view.presentation.CalendarComponent
import com.dev.banoo10.calendar_view.presentation.CalendarViewComponent
import com.dev.banoo10.core.presentation.Screen
import com.dev.banoo10.feature_calculatorList.presentation.component.TopBarCostum
import com.dev.banoo10.ui.theme.Cyan600
import com.dev.banoo10.ui.theme.Cyan800
import com.dev.banoo10.ui.theme.DarkBlue
import com.dev.banoo10.ui.theme.Red
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true){
//        Log.e("startAt","hahahah")
        if (state.todayCalendarPage>=0){
            scope.launch {
                pagerState.animateScrollToPage(state.todayCalendarPage)
            }

        }
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
                backDestination = Screen.CalcListScreen.route,
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

            Column(
//                verticalArrangement = Arrangement.Top
            ) {
//                CalendarViewComponent(startAt = state.calendarList[3])

                HorizontalPager(
                    modifier = Modifier
                        .background(Cyan600)
                        .fillMaxHeight(.55f),
                    verticalAlignment = Alignment.Top,
                    count = state.calendarList.size,
                    state = pagerState
                ) { page ->
//                    Column(){

                    CalendarComponent(
                        calendarString = state.calendarList[page],
                        backgroundColor = Cyan600,
                        contentColor = Color.White,
                        todayDate = if (page.equals(state.todayCalendarPage)) state.todayDate
                        else null,
                        onDateClicked = {
                            viewModel.onEvent(CalcDetailsEvent.DatePick(it))
                            Log.e("date",it.toString())
                        }


                    )
                }
                Text(text = "mantap")

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround

                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Text(text = "Dimulai pada")
                        Spacer(Modifier.height(20.dp))
                        Text(text = state.startAt)

                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Panen")
                        Spacer(Modifier.height(20.dp))
                        Text(text = state.endAt)

                    }

                }
                Spacer(Modifier.height(20.dp))

                Card(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                        .height(110.dp),
                    shape = RoundedCornerShape(20),
                    elevation = 10.dp

                ) {
                    Row(
//                        modifier = Modifier.padding(20.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(80.dp)
                                .clip(shape = RoundedCornerShape(20))
                                .background(DarkBlue),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            
                        ) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(text = "Hari ke", color = Color.White, style = MaterialTheme.typography.body2)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = if(state.index>=0)state.feedData[state.index].day.toString()
                                else "",
                                color = Color.White, style = MaterialTheme.typography.h4)

                        }
                        Spacer(modifier = Modifier.width(4.dp))

                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Jumlah pakan",
                                fontSize = 10.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = if(state.index >=0)"${String.format("%.1f", state.feedData[state.index].pakan_harian)} kg"
                                    else "",

                                style = MaterialTheme.typography.h4.copy(
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.Bold
                                )
                            )

                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.LightGray),
                            horizontalAlignment = Alignment.End,

                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start

                            ) {
                                Text(text = "Penyerapan")
                                Text(text = if(state.index >=0)"${String.format("%.1f", state.feedData[state.index].penyerapan)} kg"
                                else "")

                            }
                            Row(
                                horizontalArrangement = Arrangement.Start

                            ) {
                                Text(text = "Berat akhir")
                                Text(text = if(state.index >=0)"${String.format("%.1f", state.feedData[state.index].berat_akhir)} kg"
                                else "")

                            }

                        }

                    }



                }

            }






        }

    }


}