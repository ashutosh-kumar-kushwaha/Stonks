package me.ashutoshkk.stonks.presentation.ui.company.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import me.ashutoshkk.stonks.presentation.ui.company.GraphType
import me.ashutoshkk.stonks.presentation.ui.theme.StonksTheme

@Composable
fun GraphSelector(
    modifier: Modifier,
    selectedGraph: GraphType,
    onGraphSelect: (GraphType) -> Unit
) {
    Card(
        modifier = modifier
            .height(64.dp),
        shape = RoundedCornerShape(64.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
                .padding(StonksTheme.paddings.around)
        ) {
            GraphType.entries.forEach {
                GraphTypeItem(selectedGraph = selectedGraph, graphType = it) {
                    onGraphSelect(it)
                }
            }
        }
    }
}

@Composable
fun GraphTypeItem(selectedGraph: GraphType, graphType: GraphType, onGraphSelect: () -> Unit) {
    Box(
        modifier = if (graphType == selectedGraph) {
            Modifier
                .size(48.dp)
                .background(MaterialTheme.colorScheme.primary, CircleShape)
                .clip(CircleShape)
                .clickable {
                    onGraphSelect()
                }
        } else {
            Modifier
                .size(48.dp)
                .clickable {
                    onGraphSelect()
                }
        },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = when (graphType) {
                GraphType.Day -> "1D"
                GraphType.Week -> "1W"
                GraphType.Month -> "1M"
                GraphType.SixMonth -> "6M"
                GraphType.Year -> "1Y"
            },
            textAlign = TextAlign.Center,
            style = StonksTheme.typography.headlineSmall,
            color = if (selectedGraph == graphType) {
                StonksTheme.colorScheme.text2
            } else {
                StonksTheme.colorScheme.text
            }
        )
    }
}