package com.dev.banoo10.feature_calculatorList.presentation.component

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun TopBarCostum(
    title: String = "",
    backButton: Boolean = false,
    actionButton: Boolean = false,
    backgroundColor: Color = MaterialTheme.colors.primary,
    contentColor: Color = contentColorFor(backgroundColor),
    navController: NavController? = null


) {

    var expanded by remember {mutableStateOf(false)}

//    if (backButton){
//
//
//    }
//    else{
//
//
//    }
    TopAppBar(
        title = {
            Text(text = title,
            style = MaterialTheme.typography.body1)
        },
        navigationIcon = if (!backButton)null
        else
        {
            {
            IconButton(onClick = {
                navController?.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back button")
                }
            }
        },
//        actions = if (!actionButton){}
       actions = {
           if (actionButton){
               IconButton(onClick = {}) {
                   Icon(imageVector = Icons.Default.Menu, contentDescription = "Back Button")
               }
            }
       },
        backgroundColor = backgroundColor,
        contentColor = contentColor
//            {
//                IconButton(
//                    onClick = {
//                        expanded = !expanded
//                        Log.e("expanded", expanded.toString())
//
//                    }
//                ) {
//                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu Icon")
//                    DropdownMenu(
//                        expanded = expanded,
//                        onDismissRequest = {
//                            expanded = false
//                        }
//                    ) {
//                        DropdownMenuItem(
//                            onClick = {
//                                expanded = false
//                            }) {
//                            Text(text = "asu")
//                            Divider()
//                            Text(text = "asu")
//                        }
//
//                    }
//                }
//            }

    )


}