package com.dev.banoo10.feature_auth.presentation.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.banoo10.core.Resource
import com.dev.banoo10.feature_auth.domain.use_case.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    savedStateHandle: SavedStateHandle
):ViewModel(){

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    init {
        getAccTok()

    }


    private fun getAccTok() {
        authUseCases.getAccTokenLocal().onEach { result ->
            when(result){
                is Resource.Success -> {
                    Log.e("vm","success")
                    Log.e("result data", result.data.toString())

                    _state.value = LoginState(token = result.data.toString())



                }
                is Resource.Error -> {
                    Log.e("vm","error")
                    Log.e("result data",result.data.toString())
                    _state.value = LoginState(token = "-1")

                }
                is Resource.Loading -> {
                    Log.e("vm","loading")
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            message = "loading"

                        )
                    )
                }
            }
        }
        .launchIn(viewModelScope)


//        viewModelScope.launch {
//            _eventFlow.emit(UiEvent.ShowSnackbar(
//                message = "mashok"
//            )
//            )
//
//        }
//        onEvent(LoginEvent.ShowClicked)
//        viewModelScope.launch {
////            if(token.length<=4){
//                _eventFlow.emit(UiEvent.NextScreen)
////            }else{
//
////            }
//
//        }

    }



    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.LoginClicked -> {

//                viewModelScope.launch {
//                    Log.e("pressed", "login")
//                    authUseCases.addUserLocal()
//
//                }
            }
            is LoginEvent.ShowClicked -> {
                getAccTok()
//                viewModelScope.launch {
//                    Log.e("pressed", "show")
//                    val a = authUseCases.getUserLocal()
//                    Log.e("isinya", a.toString())
//                    Log.e("type response", a.javaClass.name)
//                    val ac = authUseCases.getAccTokenLocal()?.accessToken.toString()


//                    Log.e("isinya", ac)
//                    Log.e("type response", ac.javaClass.name)

//                    if(ac.isBlank()){
//                        Log.e("ac", "null gan")
//                        _eventFlow.emit(UiEvent.NextScreen)
//                    }else{
//                        Log.e("ac","ga null")
//                    }

//                }
            }
            is LoginEvent.LogoutClicked -> {
                viewModelScope.launch {
                    Log.e("pressed", "logout")
                        authUseCases.deleteUserLocal()
//                        Log.e("isinya", a.toString())
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object NextScreen: UiEvent()
    }
}