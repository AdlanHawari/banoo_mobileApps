package com.dev.banoo10.feature_calculatorList.presentation.calculator_details

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.banoo10.core.Resource
import com.dev.banoo10.core.constants.Constants
import com.dev.banoo10.core.constants.DatePattern
import com.dev.banoo10.feature_calculatorList.domain.use_case.CalculatorUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class CalcDetailsViewModel @Inject constructor(
    private val useCases: CalculatorUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _calcDetailsState = mutableStateOf(CalcDetailsState())
    val calcDetailsState: State<CalcDetailsState> = _calcDetailsState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
//        viewModelScope.launch {
//            _eventFlow.emit(UiEvent.ShowSnackbar(message = "aaaaa"))
//        }

        savedStateHandle.get<String>(Constants.PARAM_CALCID)?.let { id ->
//            _calcDetailsState.value = calcDetailsState.value.copy(
//                isLoading = true,
//                id = id
//            )
            getDetails(id)

        }
//        getDetails("uhuy")
//
    }

     fun getDetails(id:String){
        viewModelScope.launch {
            useCases.getDetails(id).onEach { result ->
                when (result){
                    is Resource.Success -> {

                        val calendarVM = Calendar.getInstance()
                        val simpleYD = SimpleDateFormat(DatePattern.YEARDATEONLY)
                        val parsed = simpleYD.format(calendarVM.getTime())
                        Log.e("parsed",parsed)
                        result.data!!.calendarList.forEachIndexed{ index, element ->
                            val cut = element.substring(0,7)
                            if (parsed.equals(cut)){
                                Log.e("page",index.toString())
                                _calcDetailsState.value = calcDetailsState.value.copy(
                                    todayCalendarPage = index,
                                    todayDate = calendarVM.get(Calendar.DAY_OF_MONTH)
                                )
                            }
//                            Log.e("cut",cut)
                        }

                        _calcDetailsState.value = calcDetailsState.value.copy(
                            isLoading = false,
                            name = result.data!!.feedCalc_name,
                            calendarList = result.data.calendarList,
                            startAt = result.data.startAt,
                            endAt = result.data.endAt,
                            feedData = result.data.record,
//                            todayCalendarPage =
//                            id = id
                        )
//                        Log.e("data",result.data.toString())
                    }
                    is Resource.Error -> {
                        Log.e("data", result.message.toString())
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = result.message.toString()
                            )
                        )
                    }
                    is Resource.Loading -> {
                        Log.e("uhuy","ihiy")
                        _calcDetailsState.value = calcDetailsState.value.copy(
                            isLoading = true
//                            id = id
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getDay(dayString: String): Int{
        val calendar = Calendar.getInstance()
        val simpleDF = SimpleDateFormat(DatePattern.DATEONLY)
        val datePickDF = SimpleDateFormat(DatePattern.DATEPICK)
        val startTime = simpleDF.parse(_calcDetailsState.value.startAt).time
        val pickedTime = datePickDF.parse(dayString).time
        val delta = pickedTime - startTime

        val index = TimeUnit.MILLISECONDS.toDays(delta).toInt()
        Log.e("day",index.toString())
        return index
    }

    fun onEvent(event: CalcDetailsEvent){
        when(event){
            is CalcDetailsEvent.DatePick -> {

                _calcDetailsState.value = calcDetailsState.value.copy(
                    index = getDay(event.value)
                )

            }
        }

    }

    sealed class UiEvent{
        data class ShowSnackbar(val message: String):UiEvent()
    }
}