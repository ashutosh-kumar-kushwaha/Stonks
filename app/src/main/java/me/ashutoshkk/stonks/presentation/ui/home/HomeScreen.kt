package me.ashutoshkk.stonks.presentation.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import me.ashutoshkk.stonks.R
import me.ashutoshkk.stonks.presentation.Screen
import me.ashutoshkk.stonks.presentation.ui.home.components.ProgressBar
import me.ashutoshkk.stonks.presentation.ui.home.components.StockType
import me.ashutoshkk.stonks.presentation.ui.home.components.TopGainersLosersScreen
import me.ashutoshkk.stonks.presentation.ui.theme.StonksTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navigateTo: (String) -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { HomeTabs.entries.size })
    val selectedTabIndex by remember {
        derivedStateOf { pagerState.currentPage }
    }
    val onRetryClick = {
        viewModel.getTopGainersLosers()
    }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = StonksTheme.colorScheme.text,
                        style = StonksTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    scrolledContainerColor = MaterialTheme.colorScheme.background
                ),
                actions = {
                    IconButton(
                        onClick = {
                            navigateTo(Screen.Search.route)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.search_icon),
                            contentDescription = null,
                            tint = StonksTheme.colorScheme.icon,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.fillMaxWidth(),
            ) {
                HomeTabs.entries.forEach {
                    Tab(
                        text = {
                            Text(
                                text = stringResource(id = it.text),
                                style = StonksTheme.typography.titleMedium
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
            if (uiState.isLoading) {
                ProgressBar()
            } else {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) { page ->
                    when (page) {
                        0 -> TopGainersLosersScreen(
                            uiState.topGainers,
                            StockType.Gainer,
                            onRetryClick,
                            navigateTo
                        )

                        1 -> TopGainersLosersScreen(
                            uiState.topLosers,
                            StockType.Loser,
                            onRetryClick,
                            navigateTo
                        )
                    }
                }
            }
        }
    }

}