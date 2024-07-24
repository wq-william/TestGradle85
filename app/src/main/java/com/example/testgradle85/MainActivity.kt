package com.example.testgradle85

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.testgradle85.testHttp.HttpTest
import com.example.testgradle85.ui.login.LoginPage
import com.example.testgradle85.ui.login.viewModel.LoginViewModel
import com.example.testgradle85.ui.theme.TestGradle85Theme
import hz.wq.common.TestCommon
import hz.wq.common.util.log.LogUtils.wqLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        "test log ".wqLog()
        TestCommon.getTestStr().wqLog()
//        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
//            HttpTest.fetchData_Test_sendApi_login陈豪()
//        }
        setContent {
            LoginPage(viewModel)
//            TestGradle85Theme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android gradle8.7 app8.5",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
        }

        lifecycleScope.launch {
            viewModel.shouldFinishActivity.collect {
                "shouldFinishActivity collect".wqLog()
                finish()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestGradle85Theme {
        Greeting("Android")
    }
}