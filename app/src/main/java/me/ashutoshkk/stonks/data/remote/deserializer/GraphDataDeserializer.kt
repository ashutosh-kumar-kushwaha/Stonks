package me.ashutoshkk.stonks.data.remote.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import me.ashutoshkk.stonks.data.remote.dto.GraphDataDto
import me.ashutoshkk.stonks.data.remote.dto.GraphMetaDataDto
import me.ashutoshkk.stonks.data.remote.dto.GraphPointsDto
import me.ashutoshkk.stonks.data.remote.dto.PriceDto
import java.lang.reflect.Type

class GraphDataDeserializer : JsonDeserializer<GraphDataDto> {
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
            val open = it.value.asJsonObject["1. open"].asFloat
            val high = it.value.asJsonObject["2. high"].asFloat
            val low = it.value.asJsonObject["3. low"].asFloat
            val close = it.value.asJsonObject["4. close"].asFloat
            val volume = it.value.asJsonObject["5. volume"].asLong
            GraphPointsDto(time, PriceDto(open, high, low, close, volume))
        }

        return GraphDataDto(graphPoints)
    }
}