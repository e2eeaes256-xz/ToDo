package cn.super12138.todo.ui.pages.settings.components.language

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.super12138.todo.ui.TodoDefaults
import cn.super12138.todo.ui.components.Languages
import cn.super12138.todo.utils.VibrationUtils

@Composable
fun LanguageItem(
    modifier: Modifier = Modifier,
    language: Languages,
    selected: Boolean,
    onSelected: (Languages) -> Unit = {},
) {
    val view = LocalView.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(MaterialTheme.shapes.large)
            .clickable(
                onClick = {
                    VibrationUtils.performHapticFeedback(view)
                    onSelected(language)
                }
            )
            .padding(
                horizontal = TodoDefaults.settingsItemHorizontalPadding / 2,
                vertical = TodoDefaults.settingsItemVerticalPadding / 2
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = {
                VibrationUtils.performHapticFeedback(view)
                onSelected(language)
            }
        )
        Text(
            text = language.displayName(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 17.sp
            ),
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}