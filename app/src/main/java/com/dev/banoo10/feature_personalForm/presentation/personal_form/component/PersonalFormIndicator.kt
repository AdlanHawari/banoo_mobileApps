package com.dev.banoo10.feature_personalForm.presentation.personal_form.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.banoo10.R
import com.dev.banoo10.feature_personalForm.presentation.personal_form.personalFormItem
import com.dev.banoo10.ui.theme.Banoo10Theme
import kotlinx.coroutines.delay

@Composable
fun PersonalFormIndicator(
    index:Int,
    totalPage: Int
) {

//    var pagination by remember{
//        mutableStateOf(currentPage)
//    }

//    var value by remember{
//        mutableStateOf(0.16f)
//    }
    var currentPage = index + 1

    var value = currentPage  / totalPage .toFloat()

    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

//    LaunchedEffect(key1 = currentPage,key2 = totalPage) {
//        value = pagination / totalPage .toFloat()
////        delay(1000L)
////        if(pagination<totalPage){
////            pagination +=1
////        }else{
////            pagination = 1
////        }
////        value = (currentPage / totalPage).toFloat()
//
//
//    }

    Row(modifier = Modifier
        .padding(20.dp)
        .fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceBetween
    ){
//        Text(text = "This is $currentPage")
        Box(
            contentAlignment = Alignment.Center
            ,modifier = Modifier.onSizeChanged { size = it
            }
//                .padding(vertical = 10.dp)
        ) {
            Canvas(modifier = Modifier.size(80.dp)){
                drawCircle(
                    color = Color.LightGray,
                    center = this.center,
                    radius = size.width.toFloat() /2f,
                    style = Stroke(5.dp.toPx(), cap = StrokeCap.Round)

                )
                drawArc(
                    color = Color.Green,
                    useCenter = false,
                    startAngle = -90f,
                    sweepAngle = 360f * value,
                    size = Size(size.width.toFloat(), size.height.toFloat()),
                    style = Stroke(5.dp.toPx(), cap = StrokeCap.Round)
                )
            }
            if(currentPage < totalPage){
                Text(
                    text = currentPage.toString(),
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            else{
                Image(painter = painterResource(id = R.drawable.icon_checkmark),
                    contentDescription = "checkmark")
//                Icon(Icons.Filled.Check, contentDescription = "checked", tint = Color.Green)
            }

//            Text(
//                text = if (currentPage < totalPage) currentPage.toString()
//                else "Selesai",
//                fontSize = if (currentPage < totalPage) 44.sp
//                else 18.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black
//            )

        }
        Spacer(modifier = Modifier.width(20.dp))
        Column(
            horizontalAlignment = Alignment.Start
            ) {
            Text(text = personalFormItem[index].title, style = MaterialTheme.typography.h4)

            Text(text = if (currentPage<totalPage) "Berikutnya: ${personalFormItem[currentPage].title}"
                else "")
//            Text(text = "Berikutnya: ${personalFormItem[currentPage].title}",
//                style = MaterialTheme.typography.body1)

        }
    }
    
}

@Preview(showBackground = true)
@Composable
fun TopBarView() {
    Banoo10Theme() {
        PersonalFormIndicator(1,6)

    }

}