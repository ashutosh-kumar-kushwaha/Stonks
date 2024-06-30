package me.ashutoshkk.stocksapp.presentation.ui.home

import me.ashutoshkk.stocksapp.domain.model.Stock

data class HomeUiState(
    val topGainers: List<Stock> = emptyList(),
    val topLosers: List<Stock> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false,
)
