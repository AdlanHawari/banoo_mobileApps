package com.dev.banoo10.feature_auth.presentation.phone_form

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.banoo10.core.Resource
import com.dev.banoo10.feature_auth.data.remote.dto.req_otp.ReqOTPRequest
import com.dev.banoo10.feature_auth.domain.use_case.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhoneFormViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
):ViewModel() {

    private val _phoneUser = mutableStateOf(PhoneFormState(
        hint = "contoh: 824567819"
    ))
    val phoneUser: State<PhoneFormState> = _phoneUser

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()



    fun onEvent(event: PhoneFormEvent) {
        when(event){
            is PhoneFormEvent.EnteredPhone -> {
                _phoneUser.value = phoneUser.value.copy(
                    text = event.value
                )
                if (_phoneUser.value.text.length >= 10){
                    _phoneUser.value = phoneUser.value.copy(
                        isButtonEnable = true
                    )
                }
            }
            is PhoneFormEvent.ChangePhoneFocus -> {
                _phoneUser.value = phoneUser.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            phoneUser.value.text.isBlank()
                )
            }
            is PhoneFormEvent.SendPhone -> {
                viewModelScope.launch {
                    Log.e("vm","pressed")
                    val phone_param = phoneUser.value.text
                    authUseCases.phoneFormSend(ReqOTPRequest(phoneUser.value.text))
                        .onEach { result ->
                            when (result){
                                is Resource.Success -> {
                                    Log.e("success phone", phone_param)
                                    _eventFlow.emit(UiEvent.SendPhone(phone = phone_param))
                                }
                                is Resource.Error -> {
                                    _phoneUser.value = PhoneFormState(isLoading = false)
                                    _eventFlow.emit(
                                        UiEvent.ShowSnackbar(
                                            message = result.message.toString()
                                        )
                                    )

                                }
                                is Resource.Loading -> {
                                    _phoneUser.value = PhoneFormState(isLoading = true)

                                }
                            }
                         }.launchIn(viewModelScope)
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
//        object SendPhone:UiEvent()
        data class SendPhone(val phone: String): UiEvent()
    }
}