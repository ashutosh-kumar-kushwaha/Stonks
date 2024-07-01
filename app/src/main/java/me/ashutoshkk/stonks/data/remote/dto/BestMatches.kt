package me.ashutoshkk.stonks.data.remote.dto

import com.google.gson.annotations.SerializedName
import me.ashutoshkk.stonks.common.convertToType
import me.ashutoshkk.stonks.domain.model.SearchResult

data class BestMatches(
    @SerializedName("1. symbol") val symbol: String,
    @SerializedName("2. name") val name: String,
    @SerializedName("3. type") val type: String,
    @SerializedName("4. region") val region: String,
    @SerializedName("5. marketOpen") val marketOpen: String,
    @SerializedName("6. marketClose") val marketClose: String,
    @SerializedName("7. timezone") val timezone: String,
    @SerializedName("8. currency") val currency: String,
    @SerializedName("9. matchScore") val matchScore: String
)

fun BestMatches.toSearchResult() = SearchResult(
    symbol, name, convertToType(type)
)