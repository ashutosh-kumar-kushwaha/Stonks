package me.ashutoshkk.stonks.presentation.ui.company.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import me.ashutoshkk.stonks.presentation.ui.theme.StonksTheme

@Composable
fun CompanyInfo(info: String) {
    Card (
        shape = RoundedCornerShape(32.dp)
    ){
        Text(
            text = info,
            style = StonksTheme.typography.bodyMedium,
            color = StonksTheme.colorScheme.text
        )
    }
}