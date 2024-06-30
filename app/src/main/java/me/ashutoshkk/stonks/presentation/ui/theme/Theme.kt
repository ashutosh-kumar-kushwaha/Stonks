package me.ashutoshkk.stonks.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
    val borderColor: Color,
    val focusedBorderColor: Color,
    val iconColor: Color,
    val cursorColor: Color,
)

private val colors = ColorScheme(
    background = Color.White,
    onBackground = Color.Black,
    text = Color.Black,
    text2 = Color.White,
    subText = Color.Gray,
    textFieldBackground = Color(0xFFF2F7FD),
    heading = Color(0xFF171B21),
    borderColor = Color(0xFFE7F0F8),
    focusedBorderColor = Color(0xFFE1E8EF),
    iconColor = Color.Black,
    cursorColor = Color.Black
)

private val lightColors = lightColorScheme(
    primary = primary
)

private val darkColors = darkColorScheme(
    primary = primary
)

private val LocalColorScheme = compositionLocalOf { colors }

@Immutable
data class Paddings(
    val allLarge: Dp = 20.dp,
    val allMedium: Dp = 16.dp,
    val aroundSmall: Dp = 8.dp,
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
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> lightColors
        else -> darkColors
    }

    CompositionLocalProvider {
        LocalColorScheme provides colors
        LocalPaddings provides Paddings()
    }

    MaterialTheme(
        colorScheme = colorScheme,
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