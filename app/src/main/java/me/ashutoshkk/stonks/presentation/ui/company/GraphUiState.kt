package me.ashutoshkk.stonks.presentation.ui.company

import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer

data class GraphUiState(
    val day: GraphData? = null,
    val week: GraphData? = null,
    val month: GraphData? = null,
    val sixMonths: GraphData? = null,
    val year: GraphData? = null
)

data class GraphData(
    val labels: List<String>,
    val modelProducer: ChartEntryModelProducer
)

enum class GraphType{
    Day, Week, Month, SixMonth, Year
}