package com.dev.banoo10.feature_calculatorList.presentation.get_calculatorList

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.banoo10.core.Resource
import com.dev.banoo10.feature_auth.presentation.otp_form.OtpFormViewModel
import com.dev.banoo10.feature_calculatorList.domain.use_case.CalculatorUseCases
import com.dev.banoo10.feature_calculatorList.domain.use_case.get_calculator.GetCalculatorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalcListViewModel @Inject constructor(
    private val useCase: CalculatorUseCases,
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
    init{
        getCalcList()

    }

    fun onEvent(event: CalcListEvent){
        when(event){
            is CalcListEvent.AddCalc -> {
                viewModelScope.launch {
                    _eventFlow.emit(UiEvent.AddCalculator)
                }

            }
        }

    }

    fun getCalcList(){
        viewModelScope.launch {
            useCase.getCalculator()
                .onEach { result ->
                    when(result){
                        is Resource.Success -> {
                            Log.e("result",result.data.toString())
                            _calcListState.value = CalcListState(calculators = result.data ?: emptyList())

                        }
                        is Resource.Error -> {
                            Log.e("error",result.message.toString())

                        }
                        is Resource.Loading -> {
                            Log.e("loading","ehem")

                        }
                    }
                }.launchIn(viewModelScope)

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