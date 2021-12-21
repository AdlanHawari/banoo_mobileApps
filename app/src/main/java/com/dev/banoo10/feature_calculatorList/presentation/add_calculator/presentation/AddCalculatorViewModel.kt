package com.dev.banoo10.feature_calculatorList.presentation.add_calculator.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.banoo10.core.Resource
import com.dev.banoo10.feature_auth.presentation.otp_form.OtpFormState
import com.dev.banoo10.feature_auth.presentation.otp_form.OtpFormViewModel
import com.dev.banoo10.feature_calculatorList.domain.model.NewCalculationModel
import com.dev.banoo10.feature_calculatorList.domain.use_case.CalculatorUseCases
import com.dev.banoo10.feature_calculatorList.domain.use_case.add_calculator.AddCalculatorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddCalculatorViewModel @Inject constructor(
    private val useCase: CalculatorUseCases
):ViewModel() {

    val calendar = Calendar.getInstance()

    private val _addCalcstate = mutableStateOf(AddCalculatorState(
        year = calendar.get(Calendar.YEAR),
        month = calendar.get(Calendar.MONTH),
        day = calendar.get(Calendar.DAY_OF_MONTH),
    ))
    val addCalcstate: State<AddCalculatorState> = _addCalcstate



    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    val simpleDateFormat=  SimpleDateFormat("dd/MM/yyyy")

    fun onEvent(event: AddCalculatorEvent){
        when(event){
            is AddCalculatorEvent.DatePickerPressed -> {
                viewModelScope.launch {
                    _eventFlow.emit(UiEvent.ShowDatePicker)
                }
            }

            is AddCalculatorEvent.SetDateStart -> {
                _addCalcstate.value = addCalcstate.value.copy(
                    date = event.value
                )
            }

            is AddCalculatorEvent.CreateCalculation -> {
                viewModelScope.launch {
                    useCase.addCalculator(NewCalculationModel(
                        feedcalc_name = "Jancuk",
                        species = "Nila merah",
                        berat_tebar = 71.65f,
                        dosis = 0.03f,
//                        startAt = _addCalcstate.value.date
                        startAt = simpleDateFormat.parse(_addCalcstate.value.date)
                    ))
                    .onEach { result ->
                        when(result){
                            is Resource.Success -> {
                                _addCalcstate.value = addCalcstate.value.copy(
                                    isLoading = false
                                )
                                _eventFlow.emit(
                                    UiEvent.ShowSnackbar(
                                        message = "sukses gan"
                                    )
                                )

                            }
                            is Resource.Error -> {
                                _addCalcstate.value = addCalcstate.value.copy(
                                    isLoading = false
                                )
                                _eventFlow.emit(
                                    UiEvent.ShowSnackbar(
                                        message = "errroooorrr"
                                    )
                                )

                            }
                            is Resource.Loading -> {
                                _addCalcstate.value = addCalcstate.value.copy(
                                    isLoading = true
                                )

                            }
                        }

                    }.launchIn(viewModelScope)

                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object ShowDatePicker:UiEvent()
        //        object MoveFocus:UiEvent()
//        object SendOTP:UiEvent()
        data class SendOTP(val token: String): UiEvent()
        object CreateCalculator: UiEvent()

    }


}