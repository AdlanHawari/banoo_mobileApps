package com.dev.banoo10.feature_personalForm.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dev.banoo10.core.presentation.Screen
import com.dev.banoo10.ui.theme.Banoo10Theme

@Composable
fun WelcomeScreen(
    navController: NavController
) {
    
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(60.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Selamat datang",
        style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Untuk melanjutkan, silakan isi data diri terlebih dahulu",
            style = MaterialTheme.typography.body1.copy(
                textAlign = TextAlign.Center
            ))
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = {
                      navController.navigate(Screen.PersonalFormScreen.route)

            },
        ) {
            Text(text = "Lanjutkan",
                modifier = Modifier
                .fillMaxWidth(),
                style = MaterialTheme.typography.button)
        }
        Spacer(modifier = Modifier.height(240.dp))
    }
    
}


//@Preview(showBackground = true)
//@Composable
//fun WelcomePreview() {
//    Banoo10Theme() {
//        WelcomeScreen()
//
//    }
//
//}