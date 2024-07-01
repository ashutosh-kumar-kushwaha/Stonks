package me.ashutoshkk.stonks.presentation.ui.search.componens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import me.ashutoshkk.stonks.R
import me.ashutoshkk.stonks.data.room.SearchHistory
import me.ashutoshkk.stonks.presentation.ui.theme.StonksTheme

@Composable
fun SearchHistoryItem(searchHistory: SearchHistory, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(StonksTheme.paddings.around),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(StonksTheme.paddings.horizontal)
    ) {
        Icon(painter = painterResource(id = R.drawable.history), contentDescription = null)
        Text(
            text = searchHistory.query,
            style = StonksTheme.typography.bodyMedium,
            color = StonksTheme.colorScheme.subText
        )
    }
}