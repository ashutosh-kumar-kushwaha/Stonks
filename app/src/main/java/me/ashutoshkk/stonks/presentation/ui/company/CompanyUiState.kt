package me.ashutoshkk.stonks.presentation.ui.company

import me.ashutoshkk.stonks.domain.model.Company

data class CompanyUiState(
    val company: Company,
    val error: String? = null,
    val isLoading: Boolean = false,
)