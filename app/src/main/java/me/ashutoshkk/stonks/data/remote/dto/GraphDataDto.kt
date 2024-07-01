package me.ashutoshkk.stonks.data.remote.dto

data class GraphDataDto(
    val metaData: GraphMetaDataDto,
    val values: List<GraphPointsDto>
)