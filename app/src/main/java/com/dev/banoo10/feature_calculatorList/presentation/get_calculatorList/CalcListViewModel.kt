package com.dev.banoo10.feature_calculatorList.presentation.get_calculatorList

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.banoo10.core.Resource
import com.dev.banoo10.feature_calculatorList.domain.use_case.CalculatorUseCases
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

            is CalcListEvent.DeleteCalc -> {
                _calcListState.value = calcListState.value.copy(
                    topBarTitle = "Hapus Budidaya",
                    isDeleteMenu = true
                )
            }

            is CalcListEvent.CancelDeleteCalc -> {
                _calcListState.value = calcListState.value.copy(
                    topBarTitle = "Daftar Budidaya Anda",
                    isDeleteMenu = false
                )
            }

            is CalcListEvent.DeleteSelectedCalc -> {
                viewModelScope.launch {
                    useCase.deleteCalculator(
                        event.value)
                        .onEach { result ->
                            when (result){
                                is Resource.Success -> {
                                    _calcListState.value = calcListState.value.copy(
                                        isLoading = false,
                                        isDeleteMenu = false
                                    )
                                    _eventFlow.emit(
                                        UiEvent.ShowSnackbar(
                                            message = "Berhasil menghapus"
                                        )
                                    )
                                    getCalcList()
                                }

                                is Resource.Error -> {
                                    _calcListState.value = calcListState.value.copy(
                                        isLoading = false
                                    )
                                    _eventFlow.emit(
                                        UiEvent.ShowSnackbar(
                                            message = result.message.toString()
                                        )
                                    )

                                }

                                is Resource.Loading -> {
                                    _calcListState.value = calcListState.value.copy(
                                        isLoading = true
                                    )

                                }
                            }
                    }.launchIn(viewModelScope)
                }
            }

            is CalcListEvent.ToAccountEvent -> {
                viewModelScope.launch {
                    _eventFlow.emit(UiEvent.ToAccount)
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
                            _calcListState.value = calcListState.value.copy(
                                isLoading = false
                            )
                            Log.e("result",result.data.toString())
                            _calcListState.value = CalcListState(calculators = result.data ?: emptyList())

                        }
                        is Resource.Error -> {
                            _calcListState.value = calcListState.value.copy(
                                isLoading = false
                            )
                            Log.e("error",result.message.toString())
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    message = result.message.toString()
                                )
                            )

                        }
                        is Resource.Loading -> {
                            Log.e("loading","ehem")
                            _calcListState.value = calcListState.value.copy(
                                isLoading = true
                            )
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
        object ToAccount: UiEvent()
        object AddCalculator: UiEvent()

    }

}