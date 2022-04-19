package com.dev.banoo10.feature_account.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dev.banoo10.ui.theme.Banoo10Theme

@Composable
fun AccountScreen(

) {
    val scaffoldState = rememberScaffoldState()
    
    Scaffold(
//        topBar =
    ) {

    }
    Text(text = "This is Account Screen")
    Text(text = "")

}


@Preview(showBackground = true)
@Composable
fun PreviewAccountScreen() {
    Banoo10Theme {
        AccountScreen(

        )

    }

}