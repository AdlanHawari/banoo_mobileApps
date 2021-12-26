package com.dev.banoo10.feature_calculatorList.presentation.component

import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TopBarCostum(
    title: String = "",
    backButton: Boolean = false,
    actionButtonAsIcon: Boolean = false,
    actionIcon: ImageVector = Icons.Default.Menu,
    actionButtonAsText: Boolean = false,
    actionButtonText: String = "",
    backgroundColor: Color = MaterialTheme.colors.primary,
    contentColor: Color = contentColorFor(backgroundColor),
    navController: NavController? = null,
    dropDownOptions: List<String> = emptyList(),
    onClickDropDownItem: List<() -> Unit> = emptyList(),
    onClickTextButton: () -> Unit? = {}
//    onClickActionButton: () -> Unit? = {}


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
           if (actionButtonAsIcon){
               IconButton(onClick = { expanded = !expanded}) {
                   Icon(imageVector = actionIcon, contentDescription = "Action Button")
                   //dropdown block
                   DropdownMenu(
                       expanded = expanded, 
                       onDismissRequest = { expanded = false }
                   ) {
                       dropDownOptions.forEachIndexed { index, option ->
                           DropdownMenuItem(
                               onClick = {
                                   expanded = false
                                   onClickDropDownItem[index]()
                                   Log.e("option",option)
                                   Log.e("unit",onClickDropDownItem[index].toString())

                               }
                           ) {
                               Text(text = option)
                           }

                           if (index< dropDownOptions.lastIndex)
                               Divider(
                                   color = Color.LightGray,
                                   thickness = 1.dp,
                                   modifier = Modifier
                                       .padding(horizontal = 16.dp)
                               )

                       }

                       
                   }
                   //end of dropdown block
               }
           }
           if (actionButtonAsText){
               Text(
                   modifier = Modifier.clickable {
                         onClickTextButton()
                   },
                   text = actionButtonText
               )
           }
       },
        backgroundColor = backgroundColor,
        contentColor = contentColor

    )


}