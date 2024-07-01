package me.ashutoshkk.stonks.presentation.ui.company

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import me.ashutoshkk.stonks.common.Resource
import me.ashutoshkk.stonks.domain.useCase.CompanyUseCase
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCase: CompanyUseCase
) : ViewModel() {

    val ticker = savedStateHandle.get<String>("ticker")!!

    private val _uiState = MutableStateFlow(CompanyUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getCompanyInfo()
        getDailyPrices()
    }

    private fun getCompanyInfo() {
        useCase.getCompanyDetails("IBM").onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }

                is Resource.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = response.message) }
                }

                is Resource.Success -> {
                    _uiState.value = CompanyUiState(response.data!!)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getDailyPrices(){
        useCase.getDailyPrices("IBM").onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }

                is Resource.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = response.message) }
                }

                is Resource.Success -> {
//                    _uiState.value = CompanyUiState(response.data!!, dailyPrices = response.data.prices)
                }
            }
        }.launchIn(viewModelScope)
    }



    fun resetErrorMessage(){
        _uiState.update { it.copy(error = null) }
    }

}