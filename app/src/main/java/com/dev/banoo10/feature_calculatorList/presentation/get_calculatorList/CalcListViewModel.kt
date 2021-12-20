package com.dev.banoo10.feature_calculatorList.presentation.get_calculatorList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.banoo10.feature_auth.presentation.otp_form.OtpFormViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalcListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _calcListState = mutableStateOf(CalcListState())
    val calcListState: State<CalcListState> = _calcListState


    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

//    init {
//        savedStateHandle.get<String>(Constants.PARAM_TOKEN)?.let { tkn ->
//            _calcListState.value = calcListState.value.copy(
//                tkn = tkn
//            )
//        }
//    }

    fun onEvent(event: CalcListEvent){
        when(event){
            is CalcListEvent.AddCalc -> {
                viewModelScope.launch {
                    _eventFlow.emit(UiEvent.AddCalculator)
                }

            }
        }

    }


    sealed class UiEvent {

        data class ShowSnackbar(val message: String):UiEvent()
        //        object MoveFocus:UiEvent()
//        object SendOTP:UiEvent()
        data class CalcDetails(val token: String):UiEvent()
        object AddCalculator: UiEvent()

    }

}