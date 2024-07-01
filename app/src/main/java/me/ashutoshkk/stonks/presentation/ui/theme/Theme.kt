package me.ashutoshkk.stonks.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ColorScheme(
    val background: Color,
    val onBackground: Color,
    val text: Color,
    val text2: Color,
    val subText: Color,
    val textFieldBackground: Color,
    val heading: Color,
    val border: Color,
    val focusedBorder: Color,
    val icon: Color,
    val cursor: Color,
)

private val colorsSchemeLight = ColorScheme(
    background = Color.White,
    onBackground = Color.Black,
    text = Color.Black,
    text2 = Color.White,
    subText = Color.Gray,
    textFieldBackground = Color(0xFFF2F7FD),
    heading = Color(0xFF171B21),
    border = Color(0xFFE7F0F8),
    focusedBorder = Color(0xFFE1E8EF),
    icon = Color.Black,
    cursor = Color.Black,
)

private val colorsSchemeDark = ColorScheme(
    background = Color.White,
    onBackground = Color.Black,
    text = Color.White,
    text2 = Color.Black,
    subText = Color.Gray,
    textFieldBackground = Color(0xFFF2F7FD),
    heading = Color(0xFFD6D0DE),
    border = Color(0xFFE7F0F8),
    focusedBorder = Color(0xFFE1E8EF),
    icon = Color.White,
    cursor = Color.Black,
)

private val DarkColorPalette = darkColorScheme(
    primary = primaryDark
)

private val LightColorPalette = lightColorScheme(
    primary = primaryLight
)

private lateinit var LocalColorScheme: ProvidableCompositionLocal<ColorScheme>

@Immutable
data class Paddings(
    val allLarge: Dp = 20.dp,
    val allMedium: Dp = 16.dp,
    val aroundSmall: Dp = 4.dp,
    val around: Dp = 8.dp,
    val aroundLarge: Dp = 24.dp,
    val horizontal: Dp = 16.dp,
    val horizontalSmall: Dp = 12.dp,
    val vertical: Dp = 8.dp,
    val verticalSmall: Dp = 4.dp,
    val verticalLarge: Dp = 16.dp,
    val verticalInBetween: Dp = 16.dp,
    val verticalInBetweenSmall: Dp = 12.dp,
    val verticalInBetweenLarge: Dp = 24.dp,
)

private val LocalPaddings = staticCompositionLocalOf { Paddings() }

@Composable
fun StonksAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val localColorScheme = if (darkTheme) {
        colorsSchemeDark
    } else {
        colorsSchemeLight
    }

    LocalColorScheme = staticCompositionLocalOf { localColorScheme }

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider {
        LocalColorScheme provides localColorScheme
        LocalPaddings provides Paddings()
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}

object StonksTheme {
    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val paddings: Paddings
        @Composable
        @ReadOnlyComposable
        get() = LocalPaddings.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography
}