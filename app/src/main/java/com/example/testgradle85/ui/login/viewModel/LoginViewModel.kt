package com.example.testgradle85.ui.login.viewModel

import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.ToastUtils
import com.example.testgradle85.ui.login.enums.LoginResult
import com.example.testgradle85.viewModel.CommonViewModel
import hz.wq.common.dialog.BaseDialog
import hz.wq.common.dialog.MessageDialog
import hz.wq.common.util.log.LogUtils.wqLog
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class LoginViewModel : CommonViewModel() {
    private val _loginResult = MutableSharedFlow<LoginResult>()
    val loginResult: SharedFlow<LoginResult> = _loginResult

    fun login(email: String, password: String) {
        showMsg()
//        viewModelScope.launch {
//            // 模拟登录请求
////            delay(1000L)
//            _loginResult.emit(if (email == "123" && password == "456") LoginResult.LoginSuccess else LoginResult.LoginError)
//        }
    }

    fun showMsg() {
        "showMsg".wqLog()
        // 消息对话框
        MessageDialog.Builder(getActivity())
            // 标题可以不用填写
            .setTitle("我是标题")
            // 内容必须要填写
            .setMessage("我是内容")
            // 确定按钮文本
            .setConfirm("确定")
            // 设置 null 表示不显示取消按钮
            .setCancel("取消")
            // 设置点击按钮后不关闭对话框
            //.setAutoDismiss(false)
            .setListener(object : MessageDialog.OnListener {

                override fun onConfirm(dialog: BaseDialog?) {
                    ToastUtils.showShort("确定了")
                }

                override fun onCancel(dialog: BaseDialog?) {
                    ToastUtils.showShort("取消了")
                }
            })
            .show()
//        viewModelScope.launch {
//            _loginResult.emit(LoginResult.ShowMsg)
//        }
    }
    fun resetState() {
        viewModelScope.launch {
            _loginResult.emit(LoginResult.NoState)
        }
    }
}
