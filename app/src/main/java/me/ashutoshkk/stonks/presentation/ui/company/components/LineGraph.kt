package me.ashutoshkk.stonks.presentation.ui.company.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.layout.fullWidth
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.component.shape.shader.BrushShader
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.chart.layout.HorizontalLayout
import com.patrykandpatrick.vico.core.chart.line.LineChart.LineSpec
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import me.ashutoshkk.stonks.common.rememberLabelFormatter
import me.ashutoshkk.stonks.common.rememberMarker
import kotlin.math.roundToInt

@Composable
fun LineChart(
    labels: List<String>,
    modelProducer: ChartEntryModelProducer,
) {
    Chart(
        chart = lineChart(
            lines = listOf(
                LineSpec(
                    lineColor = MaterialTheme.colorScheme.primary.toArgb(),
                    lineBackgroundShader = BrushShader(
                        brush = Brush.verticalGradient(
                            listOf(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                                Color.Transparent
                            )
                        ),
                    )
                )
            ),
            axisValuesOverrider = com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider.fixed(
                maxY = modelProducer.getModel()?.maxY?.toDouble()?.roundToInt()?.plus(2)?.toFloat(),
                minY = modelProducer.getModel()?.minY?.toDouble()?.roundToInt()?.minus(2)?.toFloat(),
            ),
            spacing = 120.dp
        ),
        chartModelProducer = modelProducer,
        bottomAxis = rememberBottomAxis(
            valueFormatter = rememberLabelFormatter(labels),
            guideline = null
        ),
        startAxis = rememberStartAxis(
            valueFormatter = { value, _ ->
                value.toInt().toString()
            },
            itemPlacer = AxisItemPlacer.Vertical.default(
                maxItemCount = 6
            ),
            guideline = null
        ),
        horizontalLayout = HorizontalLayout.fullWidth(),
        marker = rememberMarker(labels),
        runInitialAnimation = true,
        modifier = Modifier
            .aspectRatio(1.5f),
    )
}