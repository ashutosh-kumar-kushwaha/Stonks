package me.ashutoshkk.stocksapp.presentation.ui.home

import androidx.annotation.StringRes
import me.ashutoshkk.stocksapp.R

enum class HomeTabs(@StringRes val text: Int) {
    TopGainers(R.string.top_gainers),
    TopLosers(R.string.top_losers),
}