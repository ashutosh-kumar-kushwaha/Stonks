package me.ashutoshkk.stonks.presentation.ui.company.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import me.ashutoshkk.stonks.R
import me.ashutoshkk.stonks.presentation.ui.theme.StonksTheme

@Composable
fun WeeksLowHigh(
    @StringRes id: Int,
    value: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(StonksTheme.paddings.vertical, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id),
            style = StonksTheme.typography.bodyMedium,
            color = StonksTheme.colorScheme.subText
        )
        Text(
            text = value,
            style = StonksTheme.typography.titleSmall,
            color = StonksTheme.colorScheme.text
        )
    }
}