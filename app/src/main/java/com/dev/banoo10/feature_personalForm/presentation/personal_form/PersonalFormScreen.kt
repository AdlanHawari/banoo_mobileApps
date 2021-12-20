package com.dev.banoo10.feature_personalForm.presentation.personal_form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dev.banoo10.core.presentation.Screen
import com.dev.banoo10.feature_personalForm.presentation.personal_form.component.*
import com.dev.banoo10.ui.theme.Cyan800
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Composable
fun PersonalFormScreen(
    viewModel: PersonalFormViewModel = hiltViewModel(),
    navController: NavController
) {

    val state = viewModel.personalFormState.value
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val pagerState = rememberPagerState()
    val maxChar = 3

//    var res: List<String> by remember{ mutableStateOf(listOf("","","","",""))}
    var isFilled: Boolean by remember {
        mutableStateOf(false)
    }
    var isComplete: Boolean by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true){
//        Log.e("isFilled", isFilled.toString())
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is PersonalFormViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is PersonalFormViewModel.UiEvent.ToCalcList -> {
                    navController.navigate(Screen.CalcListScreen.route)
//                    navController.navigate(Screen.DeleteScreen.route)
                }
            }

        }
    }

    Scaffold(
//        topBar = {
//            Text("Hello its page ${pagerState.currentPage}")
//        },
        scaffoldState = scaffoldState,
        topBar = {
            PersonalFormIndicator(pagerState.currentPage, personalFormItem.size)
        },




    ){
//        scope.launch {
//            pagerState.animateScrollToPage(
////                pagerState.currentPage
//            4
//            )
////            pagerState.stopScroll()
//        }
//        Text(text = "This is content side")
        Box(modifier = Modifier.fillMaxSize()) {
            HorizontalPager(
                count = personalFormItem.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .disabledHorizontalPointerInputScroll()
                    .padding(20.dp)

            ) { page ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
//                Text(
//                    text = "Page: $page",
//                    modifier = Modifier.fillMaxWidth()
//                )
//                Spacer(modifier = Modifier.height(40.dp))

//                GenderPage()


                    if (page == 0) {
                        GenderComponent(
                            question = state.itemState[page].subtitle,
                            option = state.itemState[page].options,
                            strokeWidth = 5.dp,
                            selectedColor = Cyan800,
                            circleSize = 140.dp
                        ){
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
                            focusState = if (pagerState.currentPage == 1) true
                            else false,
//                            onValueChange = {if (pagerState.currentPage == 1)viewModel.onEvent(PersonalFormEvent.EnteredName(it)) }
                            onValueChange = {viewModel.onEvent(PersonalFormEvent.EnteredName(it))}

                        ){
                                getRes ->
                                if (!getRes.isNullOrEmpty()) {
                                    isFilled = true
                                }
                                else isFilled = false
                        }
                    }
                    else if (page ==2){
                        AgeComponent(
                            question = state.itemState[page].subtitle,
                            text = state.age,
                            focusState = if (pagerState.currentPage == 2) true
                            else false,
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
                            focusState = if (pagerState.currentPage == 3) true
                            else false,
                            onValueChange = {
                                viewModel.onEvent(PersonalFormEvent.EnteredAddress(it))
                            }
                        ){
                                getRes ->
                            if (!getRes.isNullOrEmpty()) isFilled = true
                            else isFilled=false

                        }
                    }
                    else if (page == 4){
                        PondComponent(
                            question = state.itemState[page].subtitle,
                            options = state.itemState[page].options,
                            isToggled = false,
                            placeholder = "Pilih bentuk kolam",
                            keyboardState = if (pagerState.currentPage == 4) true
                            else false,
                            text = listOf(state.pond_depth, state.pond_length, state.pond_width),
                            onValueChange = listOf(
                                {viewModel.onEvent(PersonalFormEvent.EnteredPondDepth(it))},
                                {viewModel.onEvent(PersonalFormEvent.EnteredPondLength(it))},
                                {viewModel.onEvent(PersonalFormEvent.EnteredPondWidth(it))}
                            ),
                            onShapeChange = {viewModel.onEvent(PersonalFormEvent.EnteredPondShape(it))}
                        ){
                                getRes ->
                            if (!getRes.isNullOrEmpty()) isFilled = true
                            else isFilled=false

                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {
//                        viewModel.onEvent(PersonalFormEvent.Next)

                            if (page < state.itemState.size - 1){
                                scope.launch {
                                    pagerState.animateScrollToPage(page + 1)
                                    
                                }
                            }
                            else{
                                viewModel.onEvent(PersonalFormEvent.Finish)
                            }
//                        scope.launch {
//                            pagerState.animateScrollToPage(
//                                if(page < state.itemState.size -1){
//                                    page + 1
//                                }
//                                else{
//                                    page
//                                }
//                            )
//                        }
                            isFilled = false
                        },
                        enabled = if(page < state.itemState.size - 1) isFilled
                        else true
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

            if (state.isLoading){
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray.copy(alpha = .6f)),
                    contentAlignment = Alignment.Center
                ) {
                    Box(modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                    ){
                        CircularProgressIndicator(modifier = Modifier
                            .align(Alignment.Center)
                            .padding(20.dp))
                    }


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

//@ExperimentalPagerApi
//@Preview(showBackground = true)
//@Composable
//fun PFSPreview() {
//    Banoo10Theme() {
//        PersonalFormScreen()
//
//    }
//
//}
