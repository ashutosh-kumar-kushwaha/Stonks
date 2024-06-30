package me.ashutoshkk.stonks.presentation

sealed class Screen(val route: String) {

    data object Home: Screen("home")

    data object Company: Screen("company/{ticker}"){
        fun createRoute(ticker: String) = "company/$ticker"
    }

}