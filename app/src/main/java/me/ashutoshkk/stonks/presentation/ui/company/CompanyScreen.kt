package me.ashutoshkk.stonks.presentation.ui.company

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.ashutoshkk.stonks.presentation.ui.company.components.CompanyInfo
import me.ashutoshkk.stonks.presentation.ui.company.components.CompanyPriceInfo
import me.ashutoshkk.stonks.presentation.ui.company.components.OtherInfo
import me.ashutoshkk.stonks.presentation.ui.home.components.CompanyLogo
import me.ashutoshkk.stonks.presentation.ui.theme.StonksTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CompanyScreen() {
    val viewModel: CompanyViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .padding(StonksTheme.paddings.allMedium),
            verticalArrangement = Arrangement.spacedBy(StonksTheme.paddings.verticalInBetween)
        ) {
            uiState.company?.let { company ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CompanyLogo(name = company.name)
                    Spacer(modifier = Modifier.width(StonksTheme.paddings.horizontal))
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = company.name,
                            style = StonksTheme.typography.titleMedium,
                            color = StonksTheme.colorScheme.text,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                        Text(
                            text = "${company.symbol}, ${company.assetType}",
                            style = StonksTheme.typography.bodyMedium,
                            color = StonksTheme.colorScheme.subText
                        )
                        Text(
                            text = company.exchange,
                            style = StonksTheme.typography.bodyMedium,
                            color = StonksTheme.colorScheme.subText
                        )
                    }
                }
                Text(
                    text = "About ${company.name}",
                    style = StonksTheme.typography.titleMedium,
                    color = StonksTheme.colorScheme.text
                )
                Text(
                    text = company.description,
                    style = StonksTheme.typography.bodyMedium,
                    color = StonksTheme.colorScheme.subText
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(StonksTheme.paddings.horizontal),
                    verticalArrangement = Arrangement.spacedBy(StonksTheme.paddings.vertical)
                ) {
                    CompanyInfo("Industry " + company.industry)
                    CompanyInfo("Sector " + company.sector)
                }
                CompanyPriceInfo(company)
                OtherInfo(company)
            }
        }
    }
}