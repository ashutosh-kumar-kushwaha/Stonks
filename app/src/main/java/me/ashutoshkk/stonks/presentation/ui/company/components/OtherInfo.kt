package me.ashutoshkk.stonks.presentation.ui.company.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import me.ashutoshkk.stonks.R
import me.ashutoshkk.stonks.domain.model.Company
import me.ashutoshkk.stonks.presentation.ui.theme.StonksTheme

@Composable
fun OtherInfo(company: Company) {
    val list = listOf(
        R.string.market_cap to "$" + company.marketCapitalization,
        R.string.pe_ratio to company.pERatio,
        R.string.beta to company.beta,
        R.string.dividend_yield to company.dividendYield,
        R.string.profit_margin to company.profitMargin
    )

    LazyColumn {
        items(list) {
            Row {
                Text(
                    text = stringResource(it.first),
                    style = StonksTheme.typography.bodyMedium,
                    color = StonksTheme.colorScheme.subText
                )
                Text(
                    text = it.second,
                    style = StonksTheme.typography.titleSmall,
                    color = StonksTheme.colorScheme.text
                )
            }
        }
    }
}