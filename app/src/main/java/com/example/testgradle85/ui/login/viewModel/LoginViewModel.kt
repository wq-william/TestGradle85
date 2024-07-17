package com.example.testgradle85.ui.login.viewModel

import androidx.lifecycle.viewModelScope
import com.example.testgradle85.ui.login.enums.LoginResult
import com.example.testgradle85.viewModel.CommonViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class LoginViewModel : CommonViewModel() {
    private val _loginResult = MutableSharedFlow<LoginResult>()
    val loginResult: SharedFlow<LoginResult> = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            // 模拟登录请求
//            delay(1000L)
            _loginResult.emit(if (email == "123" && password == "456") LoginResult.LoginSuccess else LoginResult.LoginError)
        }
    }

    fun resetState() {
        viewModelScope.launch {
            _loginResult.emit(LoginResult.NoState)
        }
    }
}
