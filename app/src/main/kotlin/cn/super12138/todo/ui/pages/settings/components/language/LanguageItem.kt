package cn.super12138.todo.ui.pages.settings.components.language

import android.content.res.Configuration
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
import androidx.core.os.ConfigurationCompat
import cn.super12138.todo.ui.TodoDefaults
import java.util.Locale

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    name: String,
    onSelected: (String) -> Unit = {},
) {
    val view = LocalView.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(MaterialTheme.shapes.large)
            .padding(
                horizontal = TodoDefaults.settingsItemHorizontalPadding,
                vertical = TodoDefaults.settingsItemVerticalPadding / 2
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = {
                Locale.getDefault()
                val config = Configuration()
                config.setLocale(Locale.US)


            }
        )
        Text(
            text = name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSurface,
            ),
            modifier = Modifier.weight(1f)
        )
    }
}