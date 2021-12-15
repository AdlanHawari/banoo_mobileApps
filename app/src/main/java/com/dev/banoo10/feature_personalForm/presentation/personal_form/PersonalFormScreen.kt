package com.dev.banoo10.feature_personalForm.presentation.personal_form

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dev.banoo10.R
import com.dev.banoo10.feature_auth.presentation.phone_form.PhoneFormViewModel
import com.dev.banoo10.feature_personalForm.presentation.WelcomeScreen
import com.dev.banoo10.feature_personalForm.presentation.personal_form.component.*
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

    val state = viewModel.personalFormState.value
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val maxChar = 3

//    var res: List<String> by remember{ mutableStateOf(listOf("","","","",""))}
    var isFilled: Boolean by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = isFilled){
        Log.e("isFilled", isFilled.toString())
    }

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
////                pagerState.currentPage
//            3
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
                .padding(20.dp)

            ) { page ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
//                Text(
//                    text = "Page: $page",
//                    modifier = Modifier.fillMaxWidth()
//                )
//                Spacer(modifier = Modifier.height(40.dp))

//                GenderPage()


                if (page == 0) {
                    GenderComponent(question = state.itemState[page].subtitle,strokeWidth = 5.dp ){
                            getRes ->
//                        Log.e("result parent", getRes)
                        viewModel.onEvent(PersonalFormEvent.EnteredGender(getRes))
                        if (!getRes.isNullOrBlank()) isFilled = true
                    }
                }
                else if (page == 1){
                    NameComponent(
                        subtitle = state.itemState[page].subtitle,
                        text = state.name,
                        onValueChange = {
                            viewModel.onEvent(PersonalFormEvent.EnteredName(it))
                        }
                    ){
                        getRes ->
                            if (!getRes.isNullOrEmpty()) isFilled = true
                            else isFilled=false
                    }
                }
                else if (page ==2){
                    AgeComponent(
                        question = state.itemState[page].subtitle,
                        text = state.age,
                        onValueChange = {
                            if (it.length <= maxChar) viewModel.onEvent(PersonalFormEvent.EnteredAge(it))
                        }
                    ){
                        getRes ->
                        if (!getRes.isNullOrEmpty()) isFilled = true
                        else isFilled=false
                    }

                }
                else if (page == 3){
                    AddressComponent(
                        question = state.itemState[page].subtitle,
                        text = state.address,
                        onValueChange = {
                            viewModel.onEvent(PersonalFormEvent.EnteredAddress(it))
                        }
                    ){
                        getRes ->
                        if (!getRes.isNullOrEmpty()) isFilled = true
                        else isFilled=false

                    }
                }
//                else if (page == 4){
//                    PondComponent(
//                        pondNum = 2
//                    ){
//                            getRes ->
////                        if (!getRes.isNullOrEmpty()) isFilled = true
////                        else isFilled=false
////                        isFilled = false
//
//                    }
//                }

                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
//                        viewModel.onEvent(PersonalFormEvent.Next)
                        scope.launch {
                            pagerState.animateScrollToPage(
                                if(page < state.itemState.size -1){
                                    page + 1
                                }
                                else{
                                    page
                                }
                            )
                        }
                        isFilled = false
                    },
                    enabled = isFilled
//                    enabled = if (result[page].isN)false
//                    else true
//                    enabled = if (personalFormItem[page].result.isNullOrEmpty()) false
//                                else true

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
