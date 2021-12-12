package com.dev.banoo10.feature_calculatorList.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.dev.banoo10.core.constants.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalcListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _calcListState = mutableStateOf(CalcListState())
    val calcListState: State<CalcListState> = _calcListState

    init {
        savedStateHandle.get<String>(Constants.PARAM_TOKEN)?.let { tkn ->
            _calcListState.value = calcListState.value.copy(
                tkn = tkn
            )
        }
    }

}