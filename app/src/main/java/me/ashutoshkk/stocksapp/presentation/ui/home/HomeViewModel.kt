package me.ashutoshkk.stocksapp.presentation.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import me.ashutoshkk.stocksapp.common.Resource
import me.ashutoshkk.stocksapp.domain.useCase.TopGainersLosersUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val topGainersLosers: TopGainersLosersUseCase): ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getTopGainersLosers()
    }

    private fun getTopGainersLosers(){
        topGainersLosers().onEach { response ->
            when(response){
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }

                is Resource.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = response.message) }
                }

                is Resource.Success -> {
                    _uiState.value = HomeUiState(
                        topGainers = response.data!!.topGainers,
                        topLosers = response.data.topLosers
                    )
                }
            }
        }
    }

    fun resetErrorMessage(){
        _uiState.update { it.copy(error = null) }
    }
}