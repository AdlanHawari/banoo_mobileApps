package com.dev.banoo10.feature_personalForm.presentation.personal_form

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

    private val _personalFormState = mutableStateOf(PhoneFormState())
    val personalFormState: State<PhoneFormState> = _personalFormState

    private val _eventFlow = MutableSharedFlow<PersonalFormViewModel.UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent( event: PersonalFormEvent){
        when (event){
            is PersonalFormEvent.Next -> {
                _personalFormState.value = personalFormState.value.copy(
                    currentpage = event.currentPage
                )

            }
        }
    }


    sealed class UiEvent {
        data class ShowSnackbar(val message: String): PersonalFormViewModel.UiEvent()
        //        object SendPhone:UiEvent()
        data class SendPhone(val phone: String): PersonalFormViewModel.UiEvent()

    }

}