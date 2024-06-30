package me.ashutoshkk.stonks.presentation.ui.company.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import me.ashutoshkk.stonks.R
import me.ashutoshkk.stonks.domain.model.Company
import me.ashutoshkk.stonks.presentation.ui.theme.StonksTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyPriceInfo(company: Company) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Current Price: $${company.analystTargetPrice}",
            style = MaterialTheme.typography.labelSmall,
            color = StonksTheme.colorScheme.text
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            WeeksLowHigh(id = R.string.week_low, value = "$" + company.`52WeekLow`)
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val sliderState = remember {
                    SliderState(
                        value = company.analystTargetPrice.toFloat(),
                        valueRange = company.`52WeekLow`.toFloat()..company.`52WeekHigh`.toFloat()
                    )
                }
                Slider(
                    state = sliderState,
                    enabled = false,
                    colors = SliderDefaults.colors(
                        disabledInactiveTrackColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
            WeeksLowHigh(id = R.string.week_high, value = "$" + company.`52WeekHigh`)
        }
    }

}