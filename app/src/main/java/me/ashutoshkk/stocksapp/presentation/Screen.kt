package me.ashutoshkk.stocksapp.presentation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import me.ashutoshkk.stocksapp.R

sealed class Screen(val route: String) {

    data object Home: Screen("home")

    data object Company: Screen("company")

}