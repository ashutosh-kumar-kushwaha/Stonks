package me.ashutoshkk.stonks.domain.model

data class Company (
    val `52WeekHigh`: String,
    val `52WeekLow`: String,
    val analystTargetPrice: String,
    val assetType: String,
    val beta: String,
    val description: String,
    val dividendYield: String,
    val exchange: String,
    val industry: String,
    val marketCapitalization: String,
    val name: String,
    val pERatio: String,
    val profitMargin: String,
    val sector: String,
    val symbol: String,
)