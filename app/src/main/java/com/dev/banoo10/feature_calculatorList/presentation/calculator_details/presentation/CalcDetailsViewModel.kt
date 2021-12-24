package com.dev.banoo10.feature_calculatorList.presentation.calculator_details.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.dev.banoo10.core.constants.Constants
import javax.inject.Inject

class CalcDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle

): ViewModel(){

    private val _calcDetailState = mutableStateOf(CalcDetailsState())
    val calcDetailsState: State<CalcDetailsState> = _calcDetailState

    init {
        savedStateHandle.get<String>(Constants.PARAM_CALCID)?.let { id ->
            _calcDetailState.value = calcDetailsState.value.copy(
                id = id
            )
            Log.e("calcid",id)
        }
    }

}