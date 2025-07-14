package cn.super12138.todo.ui.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import cn.super12138.todo.ui.components.Konfetti
import cn.super12138.todo.ui.navigation.TodoNavigation
import cn.super12138.todo.ui.viewmodels.MainViewModel

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        configureWindow()
        super.onCreate(savedInstanceState)

        setContent {
            val mainViewModel: MainViewModel = viewModel()
            val showConfetti = mainViewModel.showConfetti
            App {
                TodoNavigation(
                    viewModel = mainViewModel,
                    modifier = Modifier.fillMaxSize()
                )
                Konfetti(state = showConfetti)
            }
        }
    }
}