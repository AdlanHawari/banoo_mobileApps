package com.dev.banoo10.feature_delete.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.banoo10.core.Resource
import com.dev.banoo10.feature_auth.domain.use_case.AuthUseCases
import com.dev.banoo10.feature_auth.presentation.login.LoginState
import com.dev.banoo10.feature_auth.presentation.login.LoginViewModel
import com.dev.banoo10.feature_auth.presentation.otp_form.OtpFormViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteViewModel  @Inject constructor(
    private val authUseCases: AuthUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = mutableStateOf(DeleteState())
    val state: State<DeleteState> = _state

    private val _eventFlow = MutableSharedFlow<DeleteEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private fun getAccTok() {
        authUseCases.getAccTokenLocal().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.e("vm", "success")
                    Log.e("result data", result.data.toString())

                    _state.value = DeleteState(token = result.data.toString())


                }
                is Resource.Error -> {
                    Log.e("vm", "error")
                    Log.e("result data", result.data.toString())
                    _state.value = DeleteState(token = "-1")

                }
                is Resource.Loading -> {
                    Log.e("vm", "loading")

                }
            }
        }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: DeleteEvent){
        when(event){
            is DeleteEvent.LogoutClicked -> {
                viewModelScope.launch {
                    authUseCases.deleteUserLocal()
                }

            }
            is DeleteEvent.ShowClicked -> {
                getAccTok()

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