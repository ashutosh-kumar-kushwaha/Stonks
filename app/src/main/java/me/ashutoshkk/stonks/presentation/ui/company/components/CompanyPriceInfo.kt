package me.ashutoshkk.stonks.presentation.ui.company.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import me.ashutoshkk.stonks.R
import me.ashutoshkk.stonks.domain.model.Company
import me.ashutoshkk.stonks.presentation.ui.theme.StonksTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyPriceInfo(company: Company) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.week_low),
                style = StonksTheme.typography.bodyMedium,
                color = StonksTheme.colorScheme.subText
            )
            Text(
                text = "$" + company.`52WeekLow`,
                style = StonksTheme.typography.titleSmall,
                color = StonksTheme.colorScheme.text
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            val sliderState = remember {
                SliderState(
                    value = company.analystTargetPrice.toFloat(),
                    valueRange = company.`52WeekLow`.toFloat() .. company.`52WeekHigh`.toFloat()
                )
            }
            Slider(
                state = sliderState,
                enabled = false,
                colors = SliderDefaults.colors()
            )
        }
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.week_high),
                style = StonksTheme.typography.bodyMedium,
                color = StonksTheme.colorScheme.subText
            )
            Text(
                text = "$" + company.`52WeekHigh`,
                style = StonksTheme.typography.titleSmall,
                color = StonksTheme.colorScheme.text
            )
        }
    }
}