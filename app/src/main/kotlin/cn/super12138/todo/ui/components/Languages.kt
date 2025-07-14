package cn.super12138.todo.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.res.stringResource
import cn.super12138.todo.R
import java.util.Locale

val LocalLanguage = staticCompositionLocalOf { Languages.FollowSystem }

enum class Languages(val id: Int) {
    FollowSystem(0),
    SimplifiedChinese(1),
    English(2);

    fun toLocale(): Locale = when (this) {
        FollowSystem -> Locale.getDefault()
        SimplifiedChinese -> Locale.forLanguageTag("zh-CN")
        English -> Locale.forLanguageTag("en")
    }

    @Composable
    fun displayName(): String = when (this) {
        FollowSystem -> stringResource(R.string.language_follow_system)
        SimplifiedChinese -> stringResource(R.string.language_simplified_chinese)
        English -> stringResource(R.string.language_english)
    }

    companion object {
        fun toLanguage(locale: String): Languages {
            return when (locale) {
                "system" -> FollowSystem
                "zh-CN" -> SimplifiedChinese
                "en" -> English
                else -> FollowSystem // Default to FollowSystem if unknown
            }
        }

        fun fromId(id: Int): Languages = entries.find { it.id == id } ?: FollowSystem
    }
}