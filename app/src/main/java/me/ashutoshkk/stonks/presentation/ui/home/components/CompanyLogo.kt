package me.ashutoshkk.stonks.presentation.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.ashutoshkk.stonks.common.Constants.logoColors
import me.ashutoshkk.stonks.presentation.ui.theme.StonksTheme

@Composable
fun CompanyLogo(name: String) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(logoColors.random(), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name.first().toString(),
            style = StonksTheme.typography.headlineLarge,
            color = StonksTheme.colorScheme.text
        )
    }
}