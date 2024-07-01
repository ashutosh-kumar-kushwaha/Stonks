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
        val graphPointsSet = obj[key].asJsonObject.entrySet()
        val graphPoints = graphPointsSet.map {
            val time = it.key
            val open = it.value.asJsonObject["1. open"].asDouble
            val high = it.value.asJsonObject["2. high"].asDouble
            val low = it.value.asJsonObject["3. low"].asDouble
            val close = it.value.asJsonObject["4. close"].asDouble
            val volume = it.value.asJsonObject["5. volume"].asLong
            GraphPointsDto(time, PriceDto(open, high, low, close, volume))
        }
        val meta = obj["Meta Data"].asJsonObject
        val information = meta["1. Information"].asString
        val symbol = meta["2. Symbol"].asString
        val lastRefreshed = meta["3. Last Refreshed"].asString
        val outputSize = meta["4. Output Size"].asString
        val timeZone = meta["5. Time Zone"].asString
        val metaData = GraphMetaDataDto(information, symbol, lastRefreshed, outputSize, timeZone)

        return GraphDataDto(metaData, graphPoints)
    }
}