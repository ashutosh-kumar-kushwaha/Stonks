package me.ashutoshkk.stonks.presentation.ui.search

import me.ashutoshkk.stonks.domain.model.SearchResult

data class SearchUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchResults: List<SearchResult> = emptyList()
)