package me.ashutoshkk.stonks.presentation.ui.company

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
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

    private val _graphUiState = MutableStateFlow(GraphUiState())
    val graphUiState = _graphUiState.asStateFlow()

    init {
        getCompanyInfo()
    }

    fun getCompanyInfo() {
        useCase.getCompanyDetails(ticker).onEach { response ->
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

    private fun getIntraDayPrices() {
        useCase.getIntraDayPrices(ticker).onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }

                is Resource.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = response.message) }
                }

                is Resource.Success -> {
                    _graphUiState.update { it.copy(day = response.data!!) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getDailyPrices() {
        useCase.getDailyPrices(ticker).onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }

                is Resource.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = response.message) }
                }

                is Resource.Success -> {
                    val data = response.data!!
                    val weekModelData = mutableListOf<FloatEntry>()
                    val monthModelData = mutableListOf<FloatEntry>()
                    data.values.take(7).forEachIndexed { index, value ->
                        weekModelData.add(FloatEntry(index.toFloat(), value))
                    }
                    data.values.take(30).forEachIndexed { index, value ->
                        monthModelData.add(FloatEntry(index.toFloat(), value))
                    }
                    val weekModelProducer = ChartEntryModelProducer(weekModelData)
                    val monthModelProducer = ChartEntryModelProducer(monthModelData)
                    _graphUiState.update {
                        it.copy(
                            week = GraphData(data.labels.take(7), weekModelProducer),
                            month = GraphData(data.labels.take(30), monthModelProducer)
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getMonthlyPrices() {
        useCase.getMonthlyPrices(ticker).onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }

                is Resource.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = response.message) }
                }

                is Resource.Success -> {
                    val data = response.data!!
                    val sixMonthModelData = mutableListOf<FloatEntry>()
                    val yearModelData = mutableListOf<FloatEntry>()
                    data.values.take(6).forEachIndexed { index, value ->
                        sixMonthModelData.add(FloatEntry(index.toFloat(), value))
                    }
                    data.values.take(12).forEachIndexed { index, value ->
                        yearModelData.add(FloatEntry(index.toFloat(), value))
                    }
                    val sixMonthModelProducer = ChartEntryModelProducer(sixMonthModelData)
                    val yearModelProducer = ChartEntryModelProducer(yearModelData)
                    _graphUiState.update {
                        it.copy(
                            sixMonths = GraphData(data.labels.take(6), sixMonthModelProducer),
                            year = GraphData(data.labels.take(12), yearModelProducer)
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun resetErrorMessage() {
        _uiState.update { it.copy(error = null) }
    }


    fun fetchGraphData(selectedGraph: GraphType) {
        when (selectedGraph) {
            GraphType.Day -> if (_graphUiState.value.day == null) getIntraDayPrices()
            GraphType.Week -> if (_graphUiState.value.week == null) getDailyPrices()
            GraphType.Month -> if (_graphUiState.value.month == null) getDailyPrices()
            GraphType.SixMonth -> if (_graphUiState.value.sixMonths == null) getMonthlyPrices()
            GraphType.Year -> if (_graphUiState.value.year == null) getMonthlyPrices()
        }
    }

}