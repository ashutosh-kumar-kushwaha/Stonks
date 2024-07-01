package me.ashutoshkk.stonks.data.remote.dto

import me.ashutoshkk.stonks.common.convertTo12HourFormat
import me.ashutoshkk.stonks.domain.model.GraphPoints

data class GraphDataDto(
    val values: List<GraphPointsDto>
)

fun GraphDataDto.toDay(): GraphPoints = GraphPoints(
    labels = values.map { convertTo12HourFormat(it.time) },
    values = values.map { it.price.close }
)