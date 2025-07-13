package cn.super12138.todo.logic.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cn.super12138.todo.R
import java.util.Locale

enum class Languages {
    FollowSystem,
    SimplifiedChinese,
    English;

    fun toLocale(): Locale = when (this) {
        FollowSystem -> Locale.getDefault()
        SimplifiedChinese -> Locale.forLanguageTag("zh-CN")
        English -> Locale.forLanguageTag("en")
    }

    @Composable
    fun displayName(): String = when (this) {
        FollowSystem -> "跟随系统" // TODO
        SimplifiedChinese -> "简体中文"
        English -> "英语"
    }

    companion object{
        fun toLanguage(locale: String): Languages {
            return when (locale) {
                "system" -> FollowSystem
                "zh-CN" -> SimplifiedChinese
                "en" -> English
                else -> FollowSystem // Default to FollowSystem if unknown
            }
        }
    }
}