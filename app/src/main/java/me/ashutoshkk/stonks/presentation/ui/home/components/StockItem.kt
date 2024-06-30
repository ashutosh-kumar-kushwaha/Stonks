package me.ashutoshkk.stonks.presentation.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.ashutoshkk.stonks.common.Constants.logoColors
import me.ashutoshkk.stonks.domain.model.Stock
import me.ashutoshkk.stonks.presentation.ui.theme.StonksTheme

@Composable
fun StockItem(stock: Stock, stockType: StockType) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(StonksTheme.paddings.allMedium)
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(logoColors.random(), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "A",
                    style = StonksTheme.typography.headlineLarge,
                    color = StonksTheme.colorScheme.text
                )
            }
            Spacer(modifier = Modifier.height(StonksTheme.paddings.verticalInBetween))
            Text(
                text = stock.ticker,
                style = StonksTheme.typography.titleMedium,
                color = StonksTheme.colorScheme.text
            )
            Spacer(modifier = Modifier.height(StonksTheme.paddings.vertical))
            Text(
                text = "$${stock.price}",
                style = StonksTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(StonksTheme.paddings.verticalSmall))
            when (stockType) {
                StockType.Gainer -> Text(
                    text = stock.changePercentage + " ▲ ",
                    style = StonksTheme.typography.bodyMedium,
                    color = Color.Green
                )

                StockType.Loser -> Text(
                    text = stock.changePercentage + " ▼ ",
                    style = StonksTheme.typography.bodyMedium,
                    color = Color.Red
                )
            }
        }
    }
}

enum class StockType {
    Gainer, Loser
}