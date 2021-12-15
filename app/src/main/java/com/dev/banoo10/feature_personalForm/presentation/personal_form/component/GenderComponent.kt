package com.dev.banoo10.feature_personalForm.presentation.personal_form.component

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.dev.banoo10.R
import androidx.compose.material.MaterialTheme.colors as colors1


@Composable
fun GenderComponent(
    question: String,
    strokeWidth: Dp,
    onFilled: (gender: String) -> Unit
) {

//    var isMale by remember {
//        mutableStateOf("")
//    }

    val genderNum = 2

    var result by remember {
        mutableStateOf("")
    }


    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    LaunchedEffect(key1 = result){
        onFilled(result)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = question)
        Spacer(modifier = Modifier.height(40.dp))

        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            (0 until genderNum).forEach { index ->
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier.onSizeChanged { size = it }
                ) {
                    Canvas(modifier = Modifier
                        .size(140.dp)
                        .shadow(elevation = 10.dp, shape = CircleShape)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            if (index < 1) result = "Pria"
                            else result = "Wanita"
                        }
                    ){
                        drawCircle(
                            color = Color.White,
                            center = this.center,
                            radius = (size.width-14).toFloat() /2f,
//                    style = Stroke(5.dp.toPx(), cap = StrokeCap.Round),
                        )
                        if (result.equals("Pria")){
                            if(index<1){
                                drawCircle(
                                    color = Color.Green,
                                    center = this.center,
                                    radius = (size.width-14).toFloat() /2f,
                                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
                                )
                            }
                        }
                        if(result.equals("Wanita")){
                            if (index>0){
                                drawCircle(
                                    color = Color.Green,
                                    center = this.center,
                                    radius = (size.width-14).toFloat() /2f,
                                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
                                )
                            }
                        }
                    }
                    Image(painter = if (index<1) painterResource(R.drawable.male)
                        else painterResource(R.drawable.female),
                        contentDescription = "male",
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                    )
                }

            }


//            Box(contentAlignment = Alignment.Center,
//                modifier = Modifier.onSizeChanged { size = it}
//
//            ) {
//                Canvas(modifier = Modifier
//                    .size(140.dp)
//                    .shadow(elevation = 10.dp, shape = CircleShape)
//                    .clickable(
//                        indication = null,
//                        interactionSource = remember { MutableInteractionSource() }
//                    ) { result = "Wanita" }
//
//                ){
//                    drawCircle(
//                        color = Color.White,
//                        center = this.center,
//                        radius = (size.width-14).toFloat() /2f,
////                    style = Stroke(5.dp.toPx(), cap = StrokeCap.Round),
//                    )
//
//                    if (result.equals("Wanita")){
//                        drawCircle(
//                            color = Color.Green,
//                            center = this.center,
//                            radius = (size.width-14).toFloat() /2f,
//                            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
//                        )
//                    }
////                drawCircle(
////                    color = Color.Green,
////                    center = this.center,
////                    radius = (size.width-14).toFloat() /2f,
////                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
////                )
//
//
//                }
//                Image(painter = painterResource(R.drawable.female),
//                    contentDescription = "female",
//                    modifier = Modifier
//                        .height(100.dp)
//                        .width(100.dp)
////                        .clickable(
////                            indication = null,
////                            interactionSource = remember { MutableInteractionSource()}
////                        ){  },
//                )
//
//            }


        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = result)
//        Log.e("gendercomponent", "oi")
        
    }
    

}