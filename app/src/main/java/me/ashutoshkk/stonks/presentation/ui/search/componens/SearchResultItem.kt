package me.ashutoshkk.stonks.presentation.ui.search.componens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import me.ashutoshkk.stonks.domain.model.SearchResult
import me.ashutoshkk.stonks.presentation.ui.theme.StonksTheme

@Composable
fun SearchResultItem(searchResult: SearchResult, onClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(searchResult.symbol)
            }
            .padding(StonksTheme.paddings.allMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(StonksTheme.paddings.horizontal)
    ) {
        Text(
            text = searchResult.name,
            style = StonksTheme.typography.bodyMedium,
            color = StonksTheme.colorScheme.heading,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = searchResult.type.name,
            style = StonksTheme.typography.bodyMedium,
            color = StonksTheme.colorScheme.text,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
                .padding(StonksTheme.paddings.aroundSmall)
        )
    }
}