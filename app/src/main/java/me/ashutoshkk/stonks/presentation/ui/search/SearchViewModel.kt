package me.ashutoshkk.stonks.presentation.ui.search

import android.util.Log
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
import me.ashutoshkk.stonks.domain.model.FilterType
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

    private val _filterType = MutableStateFlow(FilterType.None)
    val filterType = _filterType.asStateFlow()

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
                            Log.d("Ashu", response.data.toString())
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
        _uiState.update {
            it.copy(searchResults = emptyList())
        }
    }

    private fun getSearchHistory() {
        search.getSearchHistory().onEach { history ->
            _uiState.update { it.copy(searchHistory = history.reversed()) }
        }.launchIn(viewModelScope)
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

    fun updateFilter(type: FilterType) {
        _filterType.value = type
    }

}