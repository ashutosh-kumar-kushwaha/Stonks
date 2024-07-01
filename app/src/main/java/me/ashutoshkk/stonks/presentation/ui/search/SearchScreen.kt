package me.ashutoshkk.stonks.presentation.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.android.awaitFrame
import me.ashutoshkk.stonks.presentation.Screen
import me.ashutoshkk.stonks.presentation.ui.home.components.ProgressBar
import me.ashutoshkk.stonks.presentation.ui.search.componens.SearchHistoryItem
import me.ashutoshkk.stonks.presentation.ui.search.componens.SearchResultItem
import me.ashutoshkk.stonks.presentation.ui.search.componens.SearchTextField
import me.ashutoshkk.stonks.presentation.ui.theme.StonksTheme

@Composable
fun SearchScreen(
    navigateTo: (String) -> Unit,
    navigateBack: () -> Boolean
) {
    val viewModel: SearchViewModel = hiltViewModel()
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(vertical = StonksTheme.paddings.verticalLarge)
        ) {
            SearchTextField(
                value = searchText,
                onValueChange = viewModel::onSearchTextChange,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                navigateBack()
                            },
                        tint = StonksTheme.colorScheme.icon
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            viewModel.clearSearchText()
                        },
                        tint = StonksTheme.colorScheme.icon
                    )
                },
                modifier = Modifier.focusRequester(focusRequester),
            ) {
                focusManager.clearFocus()
                viewModel.addToSearchHistory()
            }
            if (uiState.isLoading) {
                ProgressBar()
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = StonksTheme.paddings.horizontal)
                ) {
                    if (uiState.searchResults.isEmpty()) {
                        items(
                            items = uiState.searchHistory,
                            key = { it.id }
                        ) {
                            SearchHistoryItem(it) {
                                viewModel.onSearchTextChange(it.query)
                            }
                        }
                    } else {
                        items(
                            items = uiState.searchResults,
                            key = { it.symbol }
                        ) {
                            SearchResultItem(it) {
                                viewModel.addToSearchHistory()
                                navigateTo(Screen.Company.createRoute(it))
                            }
                        }
                    }
                }
            }

            if (!uiState.error.isNullOrBlank()) {
                LaunchedEffect(Unit) {
                    snackbarHostState.showSnackbar(
                        message = uiState.error!!
                    )
                    viewModel.resetErrorMessage()
                }
            }

            LaunchedEffect(focusRequester) {
                awaitFrame()
                focusRequester.requestFocus()
            }
        }
    }
}