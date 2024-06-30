package me.ashutoshkk.stonks.presentation.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import me.ashutoshkk.stonks.presentation.ui.home.components.StockType
import me.ashutoshkk.stonks.presentation.ui.home.components.TopGainersLosersScreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { HomeTabs.entries.size })
    val selectedTabIndex by remember {
        derivedStateOf { pagerState.currentPage }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Home Screen")
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.fillMaxWidth(),
            ) {
                HomeTabs.entries.forEach {
                    Tab(
                        text = {
                            Text(
                                text = stringResource(id = it.text)
                            )
                        },
                        selected = selectedTabIndex == it.ordinal,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(it.ordinal)
                            }
                        }
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { page ->
                when (page) {
                    0 -> TopGainersLosersScreen(uiState.value.topGainers, StockType.Gainer)
                    1 -> TopGainersLosersScreen(uiState.value.topLosers, StockType.Loser)
                }
            }
        }
    }

}