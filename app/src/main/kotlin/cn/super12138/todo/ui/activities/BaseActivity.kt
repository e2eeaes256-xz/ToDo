package cn.super12138.todo.ui.activities

import android.os.Build
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import cn.super12138.todo.constants.Constants
import cn.super12138.todo.logic.datastore.DataStoreManager
import cn.super12138.todo.ui.components.Languages
import cn.super12138.todo.ui.components.LocalLanguage
import cn.super12138.todo.ui.theme.DarkMode
import cn.super12138.todo.ui.theme.PaletteStyle
import cn.super12138.todo.ui.theme.ToDoTheme
import cn.super12138.todo.utils.VibrationUtils

abstract class BaseActivity : ComponentActivity() {
    protected fun configureWindow() {
        enableEdgeToEdge()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }
    }

    @Composable
    protected fun App(content: @Composable () -> Unit) {
        // 主题
        val dynamicColor by DataStoreManager.dynamicColorFlow.collectAsState(initial = Constants.PREF_DYNAMIC_COLOR_DEFAULT)
        val paletteStyle by DataStoreManager.paletteStyleFlow.collectAsState(initial = Constants.PREF_PALETTE_STYLE_DEFAULT)
        val contrastLevel by DataStoreManager.contrastLevelFlow.collectAsState(initial = Constants.PREF_CONTRAST_LEVEL_DEFAULT)
        val darkMode by DataStoreManager.darkModeFlow.collectAsState(initial = Constants.PREF_DARK_MODE_DEFAULT)
        val secureMode by DataStoreManager.secureModeFlow.collectAsState(initial = Constants.PREF_SECURE_MODE_DEFAULT)
        val hapticFeedback by DataStoreManager.hapticFeedbackFlow.collectAsState(initial = Constants.PREF_HAPTIC_FEEDBACK_DEFAULT)
        // 语言
        val language by DataStoreManager.languageFlow.collectAsState(initial = Constants.PREF_LANGUAGE_DEFAULT)

        // 深色模式
        val darkTheme = when (DarkMode.fromId(darkMode)) {
            DarkMode.FollowSystem -> isSystemInDarkTheme()
            DarkMode.Light -> false
            DarkMode.Dark -> true
        }
        // 配置状态栏和底部导航栏的颜色（在用户切换深色模式时）
        // https://github.com/dn0ne/lotus/blob/master/app/src/main/java/com/dn0ne/player/MainActivity.kt#L266
        LaunchedEffect(darkMode) {
            WindowCompat.getInsetsController(window, window.decorView).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        }

        // 阻止截屏相关配置
        LaunchedEffect(secureMode) {
            if (secureMode) {
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_SECURE,
                    WindowManager.LayoutParams.FLAG_SECURE
                )
            } else {
                window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
            }
        }

        // 触感反馈
        LaunchedEffect(hapticFeedback) { VibrationUtils.setEnabled(hapticFeedback) }

        // 语言
        LaunchedEffect(language) {
            Log.d("TAG", "SettingsInterfaceLanguage: $language")
        }

        CompositionLocalProvider(LocalLanguage provides Languages.fromId(language)) {
            ToDoTheme(
                darkTheme = darkTheme,
                style = PaletteStyle.fromId(paletteStyle),
                contrastLevel = contrastLevel.toDouble(),
                dynamicColor = dynamicColor
            ) {
                Surface(color = MaterialTheme.colorScheme.background) {
                    content()
                }
            }
        }
    }
}