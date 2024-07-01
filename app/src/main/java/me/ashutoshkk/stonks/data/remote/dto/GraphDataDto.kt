package me.ashutoshkk.stonks.data.remote.dto

import me.ashutoshkk.stonks.common.convertTo12HourFormat
import me.ashutoshkk.stonks.common.convertToDate
import me.ashutoshkk.stonks.common.convertToMonth
import me.ashutoshkk.stonks.domain.model.GraphPoints

data class GraphDataDto(
    val values: List<GraphPointsDto>
)

fun GraphDataDto.toIntraDay(): GraphPoints = GraphPoints(
    labels = values.map { convertTo12HourFormat(it.time) },
    values = values.map { it.price.close }
)

fun GraphDataDto.toDaily(): GraphPoints = GraphPoints(
    labels = values.map { convertToDate(it.time) },
    values = values.map { it.price.close }
)

fun GraphDataDto.toMonthly(): GraphPoints = GraphPoints(
    labels = values.map { convertToMonth(it.time) },
    values = values.map { it.price.close }
)

