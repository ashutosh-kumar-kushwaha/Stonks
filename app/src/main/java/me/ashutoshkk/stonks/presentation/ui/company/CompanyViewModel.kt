package me.ashutoshkk.stonks.presentation.ui.company

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val ticker = savedStateHandle.get<String>("ticker")!!

    private val _uiState = MutableStateFlow<CompanyUiState?>(null)
    val uiState = _uiState.asStateFlow()



}