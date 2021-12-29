package com.dev.banoo10.calendar_view.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dev.banoo10.core.constants.DatePattern
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CalendarComponent(
    calendarString: String,
    backgroundColor: Color
) {
    var state by remember{ mutableStateOf(CalendarComponentState())}
    var day = 1

    val calendar = Calendar.getInstance()
    val simpleDF = SimpleDateFormat(DatePattern.DATEONLY)
    val titleDF = SimpleDateFormat(DatePattern.DATEYEAR, Locale.getDefault())
    val currentCalendar = simpleDF.parse(calendarString)
    calendar.setTime(currentCalendar)
    state = state.copy(monthName = titleDF.format(calendar.getTime()))
//    val monthName = titleDF.format(calendar.getTime())

    state = state.copy(totalWeek = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH))
//    val totalWeek = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH)
    state = state.copy(maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
//    val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    calendar.set(Calendar.DAY_OF_MONTH, 1)
    state = state.copy(
        firstPos = DatePosition(
            day = calendar.get(Calendar.DAY_OF_WEEK),
            week = calendar.get(Calendar.WEEK_OF_MONTH)
        )
    )
//    val firstDay = calendar.get(Calendar.DAY_OF_WEEK)
//    val firstDayWeek = calendar.get(Calendar.WEEK_OF_MONTH)


    Box(
        modifier = Modifier.background(backgroundColor)

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
//                Text(text = "Desember 2021")
                Text(text = state.monthName)
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

                                }
                            }
                        }
                    }
                }
            }
        }
    }


}