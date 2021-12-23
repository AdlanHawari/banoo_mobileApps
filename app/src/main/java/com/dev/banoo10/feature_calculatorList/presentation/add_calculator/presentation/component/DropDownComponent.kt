package com.dev.banoo10.feature_calculatorList.presentation.add_calculator.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun DropDownComponent(
    label: String,
    style: TextStyle,
    isToggled: Boolean,
    onClick: () -> Unit,
    placeholder: String,
    options: List<String>,
    onSelectedChange: (String) -> Unit
) {
    var selectedItem : String by remember{ mutableStateOf("")}
    var expanded by remember { mutableStateOf(isToggled) }

    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    Text(
        text = label,
        style = style
    )
    Spacer(modifier = Modifier.height(4.dp))
    Box(modifier = Modifier
        .size(200.dp, 60.dp)
        .border(
            color = Color.LightGray,
            width = 2.dp,
            shape = MaterialTheme.shapes.small
        )
        .onGloballyPositioned { coordinates ->
            //This value is used to assign to the DropDown the same width
            textfieldSize = coordinates.size.toSize()
        },
        contentAlignment = Alignment.CenterStart
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clickable {
                expanded = !expanded
                onClick()
            },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ){
            Text(
                text = if(selectedItem.isNullOrEmpty()) placeholder
            else selectedItem
            )
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null,
//                    modifier = Modifier.clickable { // Anchor view
//                        expanded = !expanded
//                    }
            )

        }
        DropdownMenu(
            modifier =Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()}),
            expanded = expanded,
            onDismissRequest = {
                {expanded = false}
            }
        ) {
            options.forEachIndexed {index, item ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        selectedItem = item
                        onSelectedChange(item)
                    }) {
                    Text(text = item)

                }
                if (index< options.lastIndex)
                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier
                    .padding(horizontal = 16.dp)
                )
            }

        }

    }


}