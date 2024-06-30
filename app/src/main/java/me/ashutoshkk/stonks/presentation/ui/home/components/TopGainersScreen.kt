package me.ashutoshkk.stonks.presentation.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import me.ashutoshkk.stonks.domain.model.Stock
import me.ashutoshkk.stonks.presentation.ui.theme.StonksTheme

@Composable
fun TopGainersLosersScreen(list: List<Stock>, stockType: StockType) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(StonksTheme.paddings.horizontal),
        verticalArrangement = Arrangement.spacedBy(StonksTheme.paddings.verticalInBetween),
        contentPadding = PaddingValues(StonksTheme.paddings.allMedium)
    ) {
        items(list) { stock ->
            StockItem(stock, stockType)
        }
    }
}