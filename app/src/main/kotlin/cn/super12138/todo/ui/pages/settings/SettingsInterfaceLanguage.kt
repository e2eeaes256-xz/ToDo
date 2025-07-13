package cn.super12138.todo.ui.pages.settings

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cn.super12138.todo.R
import cn.super12138.todo.logic.model.Languages
import cn.super12138.todo.ui.activities.MainActivity
import cn.super12138.todo.ui.components.LargeTopAppBarScaffold
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsInterfaceLanguage(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO: 本页及其相关组件重组性能检查优化
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    val configuration = LocalConfiguration.current
    val context = LocalContext.current
    val resources = LocalContext.current.resources

    LargeTopAppBarScaffold(
        title = stringResource(R.string.pref_language),
        onBack = onNavigateUp,
        scrollBehavior = scrollBehavior,
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(items = Languages.entries, key = { it.ordinal }) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .clickable {
                            configuration.setLocale(it.toLocale())
                            resources.updateConfiguration(configuration, resources.displayMetrics)
                            (context as MainActivity).recreate()
                            Log.d("TAG", "SettingsInterfaceLanguage: ${it.toLocale()}")
                        }
                ) {
                    Text(it.displayName())
                }
            }
        }
    }
}