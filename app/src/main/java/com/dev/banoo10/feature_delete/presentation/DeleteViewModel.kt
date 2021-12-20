package com.dev.banoo10.feature_delete.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.banoo10.UserTable
import com.dev.banoo10.core.Resource
import com.dev.banoo10.feature_auth.domain.use_case.AuthUseCases
import com.dev.banoo10.feature_auth.presentation.login.LoginState
import com.dev.banoo10.feature_auth.presentation.login.LoginViewModel
import com.dev.banoo10.feature_auth.presentation.otp_form.OtpFormViewModel
import com.dev.banoo10.feature_personalForm.presentation.personal_form.PersonalFormViewModel
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

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private fun getAccTok() {
        authUseCases.getAccTokenLocal().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.e("vm", "success")
                    Log.e("result data", result.data.toString())
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            message = "token:"
                        )
                    )

//                    _state.value = DeleteState(token = result.data.toString())


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

    private fun getUserData(): List<UserTable>? {
        val data = authUseCases.getUserLocal()
        Log.e("data",data.toString())
        return data
    }

    fun onEvent(event: DeleteEvent){
        when(event){
            is DeleteEvent.LogoutClicked -> {
                viewModelScope.launch {
                    authUseCases.deleteUserLocal()
                    _eventFlow.emit(UiEvent.LoggedOut)
                }

            }
            is DeleteEvent.ShowClicked -> {
                viewModelScope.launch {
                    getAccTok()
                }


            }
            is DeleteEvent.ProfileClicked -> {
                viewModelScope.launch {
                    val a = getUserData()
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            message = "token: ${a.toString()}"
                        )
                    )
                }

            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        //        object MoveFocus:UiEvent()
//        object SendOTP:UiEvent()
        data class SendOTP(val token: String): UiEvent()
        object LoggedOut:UiEvent()
    }




}