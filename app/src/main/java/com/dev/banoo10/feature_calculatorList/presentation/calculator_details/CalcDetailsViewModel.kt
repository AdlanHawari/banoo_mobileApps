package com.dev.banoo10.feature_calculatorList.presentation.calculator_details

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.banoo10.core.Resource
import com.dev.banoo10.core.constants.Constants
import com.dev.banoo10.feature_calculatorList.domain.use_case.CalculatorUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalcDetailsViewModel @Inject constructor(
    private val useCases: CalculatorUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _calcDetailState = mutableStateOf(CalcDetailsState())
    val calcDetailsState: State<CalcDetailsState> = _calcDetailState

    init {
        savedStateHandle.get<String>(Constants.PARAM_CALCID)?.let { id ->
//            _calcDetailState.value = calcDetailsState.value.copy(
//                id = id
//            )
            Log.e("calcid",id)
            getDetails(id)


        }
    }

    fun getDetails(id: String){
        viewModelScope.launch {
            useCases.getDetails(id)
                .onEach { result ->
                    when (result){
                        is Resource.Success -> {
                            Log.e("result details",result.data.toString())
                            _calcDetailState.value = calcDetailsState.value.copy(
                                isLoading = false,
                                name = result.data!!.name

                            )


                        }

                        is Resource.Error -> {
                            _calcDetailState.value = calcDetailsState.value.copy(
                                isLoading = false
                            )


                        }

                        is Resource.Loading -> {
                            _calcDetailState.value = calcDetailsState.value.copy(
                                isLoading = true
                            )


                        }
                    }
                }.launchIn(viewModelScope)
        }
    }



}