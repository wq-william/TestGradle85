package com.example.testgradle85.viewModel

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.ActivityUtils
import hz.wq.common.util.log.LogUtils.wqLog
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

open class CommonViewModel : ViewModel() {
    private val _shouldFinishActivity = MutableSharedFlow<Unit>()
    val shouldFinishActivity: SharedFlow<Unit> = _shouldFinishActivity
    fun getActivity(): ComponentActivity = ActivityUtils.getTopActivity() as ComponentActivity
    fun finishActivity() {
        "wq finishActivity".wqLog()
        viewModelScope.launch {
            "wq finishActivity emit".wqLog()
            _shouldFinishActivity.emit(Unit)
        }
    }
}