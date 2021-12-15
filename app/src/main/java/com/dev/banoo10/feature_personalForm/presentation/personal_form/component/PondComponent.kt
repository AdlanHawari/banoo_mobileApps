package com.dev.banoo10.feature_personalForm.presentation.personal_form.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.exp

@Composable
fun PondComponent(
) {
    val cityList = listOf("Jkt", "Bekasi", "Bogor")
    var cityName : String by remember {mutableStateOf(cityList[0])}
    var expanded by remember { mutableStateOf(false)}

    LaunchedEffect(key1 = expanded ){
        Log.e("expanded", expanded.toString())
    }

    Column(modifier = Modifier.fillMaxSize()) {

    }
    Box (){
        // Back arrow here
        Row (modifier = Modifier
            .fillMaxWidth()
            .clickable { // Anchor view
                expanded = !expanded
            }
        ){ // Anchor view
            Text(text = cityName,) // City name label
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded = false}) {
                cityList.forEach { city ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        cityName = city
                    }) {
                        Text(city)
                    }
                }
            }
        }
    }


}