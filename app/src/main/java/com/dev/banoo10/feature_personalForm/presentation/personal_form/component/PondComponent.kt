package com.dev.banoo10.feature_personalForm.presentation.personal_form.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

//
//@Composable
//fun PondComponent(
//
//    onFilled: (address: String) -> Unit
//) {
//
//    dropDownMenu()
//
//}
//
@ExperimentalComposeUiApi
@Composable
fun PondComponent(
    question: String,
    options: List<String>,
    isToggled: Boolean,
    placeholder: String,
    text: List<String>,
    keyboardState: Boolean,
    onShapeChange: (String) -> Unit,
    onValueChange: List<(String) -> Unit>,
    onFilled: (filled: String?) -> Unit
) {
//    val cityList = listOf("Jkt", "Bekasi", "Bogor")
    var selectedPond : String by remember {mutableStateOf("")}
    var expanded by remember { mutableStateOf(isToggled)}

    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    var sizeNum by remember { mutableStateOf(0)}

    val lengthOpt = listOf("Diameter", "Panjang")
    val subquest = listOf("Kedalaman", "Diameter", "Lebar")

    val keyboardController = LocalSoftwareKeyboardController.current

//    var subquestState by remember { mutableStateOf(subquest)}
    var lengthTitle by remember { mutableStateOf("")}

    LaunchedEffect(key1 = selectedPond, key2 =  text){
        if(selectedPond.equals(options[0]) && !text[0].isNullOrBlank() && !text[1].isNullOrBlank() ){
            onFilled("oke")
        }
        else if (selectedPond.equals(options[1]) && !text[0].isNullOrBlank() && !text[1].isNullOrBlank() && !text[2].isNullOrBlank()){
            onFilled("oke")
        }
        else{
            onFilled(null)
        }
    }


    LaunchedEffect(key1 = keyboardState){
        if (keyboardState){
            keyboardController?.hide()
        }
        else{
            keyboardController?.show()
        }

    }

    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = question)
        Spacer(modifier = Modifier.height(40.dp))

        Box (
            modifier = Modifier
                .size(200.dp, 60.dp)
//                .background(color = Color.White)
//                .shadow(elevation = 10.dp, )
                .border(
                    color = Color.LightGray,
                    width = 2.dp,
                    shape = RoundedCornerShape(12.dp)
                )
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            contentAlignment = Alignment.CenterStart,



                ){
            // Back arrow here
            Row (modifier = Modifier
                .fillMaxWidth()
//                .background(color = Color.Yellow)
                .padding(horizontal = 10.dp)
                .clickable { // Anchor view
                    expanded = !expanded
                },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ){ // Anchor view
                Text(
                    text = if (selectedPond.isNullOrEmpty()) placeholder
                    else selectedPond
                ) // City name label
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null,
//                    modifier = Modifier.clickable { // Anchor view
//                        expanded = !expanded
//                    }
                )

//                DropdownMenu(
//                    modifier =Modifier
//                        .width(with(LocalDensity.current){textfieldSize.width.toDp()}),
//                    expanded = expanded,
//                    onDismissRequest = {expanded = false}) {
//                    options.forEach { pond ->
//                        DropdownMenuItem(onClick = {
//                            expanded = false
//                            selectedPond = pond
//                        }) {
//                            Text(pond)
//                        }
//                    }
//                }
            }
            DropdownMenu(
                modifier =Modifier
                    .width(with(LocalDensity.current){textfieldSize.width.toDp()}),
                expanded = expanded,
                onDismissRequest = {expanded = false}) {
                options.forEach { pond ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        selectedPond = pond
                        onShapeChange(pond)
                        if(pond.equals(options[0])){
                            sizeNum = 2
                            lengthTitle = lengthOpt[0]
                        }
                        else {
                            sizeNum = 3
                            lengthTitle = lengthOpt[1]
                        }
                    }) {
                        Text(pond)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.SpaceEvenly

        ){
            (0 until sizeNum).forEach { index ->
                Column(modifier = Modifier
//                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .width(100.dp)
                ) {
                    Text(
                        text = if (index != 1) subquest[index]
                    else lengthTitle
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(modifier = Modifier
                            .width(40.dp)
                            .drawBehind {
                                val strokeWidth = Stroke.DefaultMiter
                                val y = size.height
                                //                val y = size.height - strokeWidth / 2

                                drawLine(
                                    Color.Gray,
                                    Offset(0f, y),
                                    Offset(size.width, y),
                                    strokeWidth
                                )
                            }


                        ){
                            BasicTextField(
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                value = text[index],
                                onValueChange = onValueChange[index],
                                singleLine = true,
                                textStyle = MaterialTheme.typography.h5.copy(textAlign = TextAlign.Center),
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "m")

                    }

                }



            }


        }

    }



}

//@Preview
//@Composable
//fun PrevPond() {
//    PondComponent(
//        question = "Ahaha",
//        options = listOf("kucing", "anjing"),
//        isToggled = false,
//        placeholder = "Pilih bentuk kolam",
//        text = listOf("1","2","3","4"),
//        onValueChange = listOf({},{},{}),
//        onShapeChange = {},
//        onFilled = {}
//
//    )
//
//
//}