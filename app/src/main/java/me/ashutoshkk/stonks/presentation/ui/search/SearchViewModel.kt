package me.ashutoshkk.stonks.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.ashutoshkk.stonks.common.Resource
import me.ashutoshkk.stonks.data.room.SearchHistory
import me.ashutoshkk.stonks.domain.useCase.SearchUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val search: SearchUseCase,
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    init {
        handleSearchQuery()
        getSearchHistory()
    }

    @OptIn(FlowPreview::class)
    private fun handleSearchQuery() {
        _searchText
            .debounce(500L)
            .filter { it.isNotBlank() }
            .onEach {
                _uiState.update { it.copy(isLoading = true) }
                search(it).onEach { response ->
                    when (response) {
                        is Resource.Success -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    searchResults = response.data!!
                                )
                            }
                        }

                        is Resource.Error -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    error = response.message!!
                                )
                            }
                        }

                        is Resource.Loading -> {
                            _uiState.update { it.copy(isLoading = true) }
                        }
                    }
                }.launchIn(viewModelScope)
            }.launchIn(viewModelScope)
    }

    fun resetErrorMessage() {
        _uiState.update { it.copy(error = null) }
    }

    fun clearSearchText() {
        _searchText.value = ""
    }

    fun getSearchHistory() {
        viewModelScope.launch {
            val history = search.getSearchHistory()
            _uiState.update { it.copy(searchHistory = history) }
        }
    }

    fun addToSearchHistory() {
        viewModelScope.launch {
            search.addToSearchHistory(
                SearchHistory(
                    query = searchText.value,
                    timestamp = System.currentTimeMillis()
                )
            )
        }
    }

}