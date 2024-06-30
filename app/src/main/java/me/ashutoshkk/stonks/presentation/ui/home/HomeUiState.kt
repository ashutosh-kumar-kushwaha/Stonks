package me.ashutoshkk.stonks.presentation.ui.home

import me.ashutoshkk.stonks.domain.model.Stock

data class HomeUiState(
    val topGainers: List<Stock> = emptyList(),
    val topLosers: List<Stock> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false,
)
