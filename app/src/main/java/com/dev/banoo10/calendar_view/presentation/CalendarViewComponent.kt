package com.dev.banoo10.calendar_view.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dev.banoo10.feature_auth.presentation.phone_form.PhoneFormViewModel
import com.dev.banoo10.ui.theme.Banoo10Theme

@Composable
fun CalendarViewComponent(
    viewModel: CalendarViewViewModel = hiltViewModel()
) {
    var day: Int = 1

    val state = viewModel.calendarState.value
    Box(

    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            //month
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Left Arrow")
                Text(text = "Desember 2021")
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Right Arrow")

            }
            Spacer(modifier = Modifier.height(4.dp))

            //hari
            Column(

            ) {
                //nama hari
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly

                ) {
                    Text(text = "MIN", textAlign = TextAlign.Center, modifier = Modifier.weight(1/7.toFloat()))
                    Text(text = "SEN", textAlign = TextAlign.Center, modifier = Modifier.weight(1/7.toFloat()))
                    Text(text = "SEL", textAlign = TextAlign.Center, modifier = Modifier.weight(1/7.toFloat()))
                    Text(text = "RAB", textAlign = TextAlign.Center, modifier = Modifier.weight(1/7.toFloat()))
                    Text(text = "KAM", textAlign = TextAlign.Center, modifier = Modifier.weight(1/7.toFloat()))
                    Text(text = "JUM", textAlign = TextAlign.Center, modifier = Modifier.weight(1/7.toFloat()))
                    Text(text = "SAB", textAlign = TextAlign.Center, modifier = Modifier.weight(1/7.toFloat()))
                }
                //tanggal
                Column(

                ) {
                    (0 until state.totalWeek).forEach { week ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
//                            modifier = Modifier.width(IntrinsicSize.Min),
                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.SpaceEvenly

                        ) {
                            (0 until 7).forEach { dayOfWeek ->
//                                day = week * 7 + dayOfWeek + 1

                                Box(
                                    modifier = if(day.equals(state.currDate)) {
                                        Modifier
                                            .weight(1 / 7.toFloat()) //should be maxwidth divided by 7
                                            .background(Color.Green)
                                            .clip(shape = MaterialTheme.shapes.large)
                                    }else Modifier
                                        .weight(1 / 7.toFloat()) //should be maxwidth divided by 7

                                    ,
                                    contentAlignment = Alignment.Center
                                ) {
                                    if(!(week+1).equals(state.firstPos.week) || dayOfWeek+1 >=state.firstPos.day){
//                                    Text(text = "$")
                                        if (day <= state.maxDay) {

                                            Text(text = day.toString())
                                            day++
                                        }
//                                    day
                                    }
                                    else{
//                                        Text(text = "")
//                                    day = week * 7 + dayOfWeek + 1
//                                    if (day <= state.maxDay) {
//                                        Text(text = day.toString())
//                                        day++
//                                    }
                                    }

                                }

//
//                                if((week+1).equals(state.firstPos.week) && dayOfWeek+1 <state.firstPos.day){
//                                    Text(text = "$")
////                                    day
//                                }
//                                else{
////                                    day = week * 7 + dayOfWeek + 1
//                                    if (day <= state.maxDay) {
//                                        Text(text = day.toString())
//                                        day++
//                                    }
//                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarPreview() {
    Banoo10Theme {
        CalendarViewComponent(

        )

    }

}