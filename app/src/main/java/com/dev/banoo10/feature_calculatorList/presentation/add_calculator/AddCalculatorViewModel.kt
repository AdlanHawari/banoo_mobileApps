package com.dev.banoo10.feature_calculatorList.presentation.add_calculator

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.banoo10.core.Resource
import com.dev.banoo10.feature_calculatorList.domain.model.NewCalculationModel
import com.dev.banoo10.feature_calculatorList.domain.use_case.CalculatorUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddCalculatorViewModel @Inject constructor(
    private val useCase: CalculatorUseCases
):ViewModel() {

    val calendar = Calendar.getInstance()

    private val _addCalcstate = mutableStateOf(
        AddCalculatorState(
        year = calendar.get(Calendar.YEAR),
        month = calendar.get(Calendar.MONTH),
        day = calendar.get(Calendar.DAY_OF_MONTH),
    )
    )
    val addCalcstate: State<AddCalculatorState> = _addCalcstate

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    val simpleDateFormat=  SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    fun onEvent(event: AddCalculatorEvent){
        when(event){
            is AddCalculatorEvent.EnteredNamaBudidaya -> {
                _addCalcstate.value = addCalcstate.value.copy(
                    cult_name = addCalcstate.value.cult_name.copy(
                        value = event.value
                    )
                )
            }

            is AddCalculatorEvent.EnteredSpesies -> {
                _addCalcstate.value = addCalcstate.value.copy(
                    spesies = addCalcstate.value.spesies.copy(
                        value = event.value
                    )
                )
            }

            is AddCalculatorEvent.EnteredBeratTebar -> {
                _addCalcstate.value = addCalcstate.value.copy(
                    berat_tebar = addCalcstate.value.berat_tebar.copy(
                        value = event.value
                    )
                )
            }

            is AddCalculatorEvent.EnteredDosis -> {
                _addCalcstate.value = addCalcstate.value.copy(
                    dosis = addCalcstate.value.dosis.copy(
                        value = event.value
                    )
                )
            }

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

//            is AddCalculatorEvent.FreeFocus -> {
//                _addCalcstate.value =
//            }

            is AddCalculatorEvent.CreateCalculation -> {

                val a= _addCalcstate.value.berat_tebar.value.toFloat()
                Log.e("a",a.toString())
//                if (){
//
//                }
                viewModelScope.launch {
                    useCase.addCalculator(NewCalculationModel(

                        feedcalc_name = _addCalcstate.value.cult_name.value,
                        species = _addCalcstate.value.spesies.value,
                        berat_tebar = _addCalcstate.value.berat_tebar.value.toFloat(),
                        dosis = if (_addCalcstate.value.dosis.value == "3%") 0.03f
                        else 0.05f,
//                        startAt = _addCalcstate.value.date
                        startAt = simpleDateFormat.parse(_addCalcstate.value.date+" 00:00:00")
                    ))
                    .onEach { result ->
                        when(result){
                            is Resource.Success -> {
                                _addCalcstate.value = addCalcstate.value.copy(
                                    isLoading = false
                                )
//                                _eventFlow.emit(
//                                    UiEvent.ShowSnackbar(
//                                        message = "sukses gan"
//                                    )
//                                )
                                _eventFlow.emit(
                                    UiEvent.ToDetailScreen(
                                        calcId = result.data?.id
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
        object ShowDatePicker: UiEvent()
        //        object MoveFocus:UiEvent()
//        object SendOTP:UiEvent()
        data class ToDetailScreen(val calcId: String?): UiEvent()
        object CreateCalculator: UiEvent()

    }


}