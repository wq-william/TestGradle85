package com.example.testgradle85.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hz.wq.common.log.LogUtils.wqLog
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

// ViewModel 示例
class LoginViewModel : ViewModel() {
    private val _loginResult = MutableSharedFlow<LoginResult>()
    val loginResult: SharedFlow<LoginResult> = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            // 模拟登录请求
//            delay(1000L)
            _loginResult.emit(if (email == "123" && password == "456") LoginResult.LoginSuccess else LoginResult.LoginError)
        }
    }
    fun resetState(){
        viewModelScope.launch {
            _loginResult.emit(LoginResult.NoState)
        }
    }
}

sealed class LoginResult {
    data object LoginSuccess : LoginResult()
    data object LoginError : LoginResult()
    data object LoggingIn : LoginResult() //登录中
    data object NoState : LoginResult()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(viewModel: LoginViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    val loginState = viewModel.loginResult.collectAsState(initial = null).value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Default.Face else Icons.Default.Lock,
                        contentDescription = if (showPassword) "Hide password" else "Show password"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.login(email, password)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log In")
        }
    }
    LaunchedEffect(email) {
        email.wqLog()
    }
    LaunchedEffect(password) {
        password.wqLog()
    }
    LaunchedEffect(loginState) {
        loginState?.let {
            when (it) {
                is LoginResult.LoginSuccess -> {
                    // 登录成功，保存数据并跳转到主页
                    // 假设你有一个保存数据的函数 saveUserData
                    // saveUserData(email)
                    "go Home ".wqLog()
                    viewModel.resetState()
                }

                is LoginResult.LoginError -> {
                    // 处理登录错误，例如显示错误消息
                    // showErrorDialog("Login failed.")
                    "go Home error".wqLog()
                    viewModel.resetState()
                }

                else -> {
                    "NoState".wqLog()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginPage() {
    LoginPage(LoginViewModel())
}