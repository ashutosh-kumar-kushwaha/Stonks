package me.ashutoshkk.stonks.data.remote.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import me.ashutoshkk.stonks.data.remote.dto.GraphDataDto
import me.ashutoshkk.stonks.data.remote.dto.GraphMetaDataDto
import me.ashutoshkk.stonks.data.remote.dto.GraphPointsDto
import me.ashutoshkk.stonks.data.remote.dto.PriceDto
import java.lang.reflect.Type

class GraphPointsDeserializer : JsonDeserializer<GraphDataDto> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): GraphDataDto {
        if (json == null || context == null) {
            throw Exception("Error")
        }
        val obj = json.asJsonObject
        val key = obj.keySet().last()
        val graphPointsSet = obj.get(key).asJsonObject.entrySet()
        val graphPoints = graphPointsSet.map {
            val time = it.key
            val open = it.value.asJsonObject.get("1. open").asDouble
            val high = it.value.asJsonObject.get("2. high").asDouble
            val low = it.value.asJsonObject.get("3. low").asDouble
            val close = it.value.asJsonObject.get("4. close").asDouble
            val volume = it.value.asJsonObject.get("5. volume").asLong
            GraphPointsDto(time, PriceDto(open, high, low, close, volume))
        }
        val meta = obj.get("Meta Data").asJsonObject
        val information = meta.get("1. Information").asString
        val symbol = meta.get("2. Symbol").asString
        val lastRefreshed = meta.get("3. Last Refreshed").asString
        val outputSize = meta.get("4. Output Size").asString
        val timeZone = meta.get("5. Time Zone").asString
        val metaData = GraphMetaDataDto(information, symbol, lastRefreshed, outputSize, timeZone)

        return GraphDataDto(metaData, graphPoints)
    }
}