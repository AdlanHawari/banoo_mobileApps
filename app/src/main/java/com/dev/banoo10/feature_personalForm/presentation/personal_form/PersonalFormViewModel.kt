package com.dev.banoo10.feature_personalForm.presentation.personal_form

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dev.banoo10.feature_auth.presentation.phone_form.PhoneFormViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject


@HiltViewModel
class PersonalFormViewModel @Inject constructor(

): ViewModel() {

//    private val _personalFormState = mutableStateOf(PersonalFormState())
//    val personalFormState: State<PersonalFormState> = _personalFormState

    private val _personalFormState = mutableStateOf(PersonalFormState())
    val personalFormState: State<PersonalFormState> = _personalFormState

    private val _eventFlow = MutableSharedFlow<PersonalFormViewModel.UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent( event: PersonalFormEvent){
        when (event){
            is PersonalFormEvent.EnteredGender -> {
//                Log.e("state",personalFormState.value.itemState.toString())
                _personalFormState.value = personalFormState.value.copy(
                    gender = event.gender
//                    itemState = personalFormState.value.itemState
//                    result = event.gender
                )
                Log.e("state",personalFormState.value.toString())
//                _personalFormState.value.itemState = personalFormState.value.itemState.copy(
//                )
            }

            is PersonalFormEvent.EnteredName -> {
                _personalFormState.value = personalFormState.value.copy(
                    name = event.name
                )
                Log.e("state",personalFormState.value.toString())
            }

            is PersonalFormEvent.EnteredAge -> {
                _personalFormState.value = personalFormState.value.copy(
                    age = event.age
                )
                Log.e("state",personalFormState.value.toString())
            }

            is PersonalFormEvent.EnteredAddress -> {
                _personalFormState.value = personalFormState.value.copy(
                    address = event.address
                )
                Log.e("state",personalFormState.value.toString())
            }

            is PersonalFormEvent.EnteredPondShape -> {
                _personalFormState.value = personalFormState.value.copy(
                    pond_shape = event.pond_shape
                )
                Log.e("state",personalFormState.value.toString())
            }

            is PersonalFormEvent.EnteredPondDepth -> {
                _personalFormState.value = personalFormState.value.copy(
                    pond_depth = event.pond_depth
                )
                Log.e("state",personalFormState.value.toString())
            }

            is PersonalFormEvent.EnteredPondLength -> {
                _personalFormState.value = personalFormState.value.copy(
                    pond_length = event.pond_length
                )
                Log.e("state",personalFormState.value.toString())
            }

            is PersonalFormEvent.EnteredPondWidth -> {
                _personalFormState.value = personalFormState.value.copy(
                    pond_width = event.pond_width
                )
                Log.e("state",personalFormState.value.toString())
            }



            is PersonalFormEvent.Next -> {
//                _personalFormState.value = personalFormState.value.copy(
//                    personalFormItemState = event.currentPage
//                )

            }
        }
    }


    sealed class UiEvent {
        data class ShowSnackbar(val message: String): PersonalFormViewModel.UiEvent()
        //        object SendPhone:UiEvent()
        data class SendPhone(val phone: String): PersonalFormViewModel.UiEvent()

    }

}

