package me.ashutoshkk.stonks.presentation.ui.company.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.ashutoshkk.stonks.presentation.ui.company.GraphType
import me.ashutoshkk.stonks.presentation.ui.theme.StonksTheme

@Composable
fun GraphSelector(selectedGraph: GraphType, onGraphSelect: (GraphType) -> Unit) {
    Card(
        modifier = Modifier
            .height(64.dp)
            .padding(StonksTheme.paddings.allMedium)
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(64.dp))
    ) {
        Row {
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
    Text(
        text = when (graphType) {
            GraphType.Day -> "1D"
            GraphType.Week -> "1W"
            GraphType.Month -> "1M"
            GraphType.SixMonth -> "6M"
            GraphType.Year -> "1Y"
        },
        modifier = if (graphType == selectedGraph) {
            Modifier
//                .padding(StonksTheme.paddings.around)
                .size(32.dp)
                .background(MaterialTheme.colorScheme.primary, CircleShape)
                .clickable {
                    onGraphSelect()
                }
        } else {
            Modifier
                .size(32.dp)
                .clickable {
                    onGraphSelect()
                }
//                .padding(StonksTheme.paddings.around)
        },
        style = StonksTheme.typography.headlineLarge,
        color = StonksTheme.colorScheme.text
    )
}