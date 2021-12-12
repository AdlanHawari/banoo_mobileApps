package com.dev.banoo10.feature_personalForm.presentation.personal_form

import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dev.banoo10.feature_auth.presentation.phone_form.PhoneFormViewModel
import com.dev.banoo10.feature_personalForm.presentation.WelcomeScreen
import com.dev.banoo10.feature_personalForm.presentation.personal_form.component.PersonalFormIndicator
import com.dev.banoo10.ui.theme.Banoo10Theme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@Composable
fun PersonalFormScreen(
    viewModel: PersonalFormViewModel = hiltViewModel()
) {

//    val personalFormState = viewModel.personalFormState.value
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(

    )

    Scaffold(
//        topBar = {
//            Text("Hello its page ${pagerState.currentPage}")
//        },
        topBar = {
            PersonalFormIndicator(pagerState.currentPage, personalFormItem.size)
        },




    ){
//        scope.launch {
//            pagerState.animateScrollToPage(
//                pagerState.currentPage
//            )
////            pagerState.stopScroll()
//        }
//        Text(text = "This is content side")
        HorizontalPager(
            count = personalFormItem.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .disabledHorizontalPointerInputScroll()

            ) { page ->
            Column() {
                Text(
                    text = "Page: $page",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(40.dp))
                GenderPage(page = page)

                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                if(page < personalFormItem.size -1){
                                    page + 1
                                }
                                else{
                                    page
                                }
                            )
                        }
                    },
                    enabled = true

                ) {
                    Text(text = if(page< personalFormItem.size -1) "Lanjut"
                    else "Selesai"
                    )

                }
            }


        }
    }
    
}

private val HorizontalScrollConsumer = object : NestedScrollConnection {
    override fun onPreScroll(available: Offset, source: NestedScrollSource) = available.copy(y = 0f)
    override suspend fun onPreFling(available: Velocity) = available.copy(y = 0f)
}

private fun Modifier.disabledHorizontalPointerInputScroll(disabled: Boolean = true) =
    if (disabled) this.nestedScroll(HorizontalScrollConsumer) else this

@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun PFSPreview() {
    Banoo10Theme() {
        PersonalFormScreen()

    }

}

@Composable
fun GenderPage(page: Int) {
    Column(
    ) {
        Text(text = "Pilih Jenis Kelamin Anda halaman $page")
        OutlinedTextField(
            value = "laki",
            onValueChange = {}
        )

        
    }
    
}