package com.example.returnpharma.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.returnpharma.networkModule.SessionManager
import com.example.returnpharma.remote.LoginResponse
import com.example.returnpharma.remote.RxMaxApi
import com.example.returnpharma.repository.RxMaxRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val repository = RxMaxRepository()

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState


fun login(username: String, password: String) {
    viewModelScope.launch {
        _loginState.value = LoginState.Loading
        val result = repository.login(username, password)
        _loginState.value = when {
            result.isSuccess -> {
                val loginResponse = result.getOrNull()!!
                SessionManager.setToken(loginResponse.accessToken)
                LoginState.Success(loginResponse)
            }
            else -> LoginState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
        }
    }
}
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val data: LoginResponse) : LoginState()
    data class Error(val message: String) : LoginState()
}