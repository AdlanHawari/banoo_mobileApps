package com.dev.banoo10.feature_auth.presentation.otp_form
import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.banoo10.core.Resource
import com.dev.banoo10.core.constants.Constants
import com.dev.banoo10.feature_auth.data.remote.dto.req_otp.ReqOTPRequest
import com.dev.banoo10.feature_auth.data.remote.dto.send_otp.SendOTPRequest
import com.dev.banoo10.feature_auth.domain.model.TimerUtility.formatTime
import com.dev.banoo10.feature_auth.domain.use_case.AuthUseCases
import com.dev.banoo10.feature_auth.presentation.phone_form.PhoneFormState
import com.dev.banoo10.feature_auth.presentation.phone_form.PhoneFormViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class OtpFormViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    savedStateHandle: SavedStateHandle
):ViewModel() {
    private val _otpFormState = mutableStateOf(OtpFormState())
    val otpFormState: State<OtpFormState> = _otpFormState

    private var countDownTimer: CountDownTimer ?= null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    init {
        savedStateHandle.get<String>(Constants.PARAM_PHONE)?.let{ phone ->
            _otpFormState.value = otpFormState.value.copy(
                phone = phone
            )
        }
        startTimer()
    }

    private fun pauseTimer(){
        countDownTimer?.cancel()
    }


    private fun startTimer(){
        countDownTimer = object : CountDownTimer(300000L, 1000){
            override fun onTick(millisUntilFinished: Long) {
                Log.e("timer",millisUntilFinished.formatTime())
                _otpFormState.value = otpFormState.value.copy(
                    time = millisUntilFinished.formatTime(),
                    isTimerFinish = false
                )
                Log.e("state",_otpFormState.value.time)
            }

            override fun onFinish() {
                pauseTimer()
                _otpFormState.value = otpFormState.value.copy(
                    time = "Kirim OTP",
                    isTimerFinish = true
                )
            }
        }.start()
    }

    fun onEvent(event: OtpFormEvent){
        when(event){
            is OtpFormEvent.EnteredOTP -> {

                _otpFormState.value = otpFormState.value.copy(
                    otp = event.value
                )
                Log.e("from vm",otpFormState.value.otp)
//                if (_otpFormState.value.text.length >= 1){
//                    _eventFlow.emit(UiEvent.MoveFocus)
//                }

                viewModelScope.launch {

                    authUseCases.otpFormSend(SendOTPRequest(
                        phone = "+62${otpFormState.value.phone}",
                        code = otpFormState.value.otp))
                        .onEach { result ->
                            when(result){
                                is Resource.Success -> {

//                                    Log.e("res vm", result.data?.accessToken.toString())
                                    pauseTimer()
                                    Log.e("res vm", result.data?.name.toString())
                                    if(result.data?.name.isNullOrEmpty()){
                                        _eventFlow.emit(UiEvent.ToPersonalForm)
                                    }else{
                                        _eventFlow.emit(UiEvent.SendOTP(result.data?.accessToken.toString()))
                                    }

                                }
                                is Resource.Error -> {
                                    _otpFormState.value = otpFormState.value.copy(
                                        isLoading = false
                                    )
                                    _eventFlow.emit(
                                        OtpFormViewModel.UiEvent.ShowSnackbar(
                                            message = result.message.toString()
                                        )
                                    )
                                }
                                is Resource.Loading -> {
                                    _otpFormState.value = otpFormState.value.copy(
                                        isLoading = true
                                    )
                                }


                            }
                        }.launchIn(viewModelScope)
                }
            }
            is OtpFormEvent.ResendOTP -> {
                viewModelScope.launch {
                    authUseCases.phoneFormSend(ReqOTPRequest( phone = otpFormState.value.phone))
                    .onEach { result ->
                        when (result){
                            is Resource.Success -> {
                                _otpFormState.value = otpFormState.value.copy(
                                    isLoading = false
                                )
                                _eventFlow.emit(
                                    UiEvent.ShowSnackbar(
                                        message = "Kode OTP telah dikirim"
                                    )
                                )
                                startTimer()

                            }
                            is Resource.Error -> {
                                _otpFormState.value = otpFormState.value.copy(
                                    isLoading = false
                                )
                                _eventFlow.emit(
                                    UiEvent.ShowSnackbar(
                                        message = result.message.toString()
                                    )
                                )
                            }
                            is Resource.Loading -> {
                                _otpFormState.value = otpFormState.value.copy(
                                    isLoading = true
                                )
//                                _eventFlow.emit(
//                                    UiEvent.ShowSnackbar(
//                                        message = "Kode OTP telah dikirim"
//                                    )
//                                )

                            }
                        }
                    }.launchIn(viewModelScope)
                }

            }
        }

    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
//        object MoveFocus:UiEvent()
//        object SendOTP:UiEvent()
        data class SendOTP(val token: String): UiEvent()
        object ToPersonalForm:UiEvent()
    }

}