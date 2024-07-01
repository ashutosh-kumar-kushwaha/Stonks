package me.ashutoshkk.stonks.presentation.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import me.ashutoshkk.stonks.R
import me.ashutoshkk.stonks.presentation.ui.theme.StonksTheme

@Composable
fun ErrorScreen(onRetryClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(StonksTheme.paddings.allLarge),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.something_went_wrong),
            style = StonksTheme.typography.titleLarge,
            color = StonksTheme.colorScheme.text
        )
        Button(onClick = onRetryClick) {
            Text(
                text = stringResource(id = R.string.retry),
                style = StonksTheme.typography.titleLarge,
            )
        }
    }
}