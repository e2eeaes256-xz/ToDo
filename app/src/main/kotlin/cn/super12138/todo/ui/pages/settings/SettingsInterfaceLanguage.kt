package cn.super12138.todo.ui.pages.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import cn.super12138.todo.R
import cn.super12138.todo.logic.datastore.DataStoreManager
import cn.super12138.todo.ui.components.Languages
import cn.super12138.todo.ui.components.LargeTopAppBarScaffold
import cn.super12138.todo.ui.components.LocalLanguage
import cn.super12138.todo.ui.pages.settings.components.language.LanguageItem
import kotlinx.coroutines.launch

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
    val currentLanguage = LocalLanguage.current

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
                LanguageItem(
                    language = it,
                    selected = currentLanguage == it,
                    onSelected = { language ->
                        scope.launch { DataStoreManager.setLanguage(language.id) }

                        configuration.setLocale(language.toLocale())
                        context.resources.updateConfiguration(
                            configuration,
                            context.resources.displayMetrics
                        )
                        //(context as MainActivity).recreate()
                    }
                )
            }
        }
    }
}