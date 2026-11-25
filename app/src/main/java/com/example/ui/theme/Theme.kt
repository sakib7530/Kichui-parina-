package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Immutable
data class LearningPalette(
    val success: Color,
    val successContainer: Color,
    val gold: Color,
    val goldContainer: Color,
    val sky: Color,
    val skyContainer: Color,
    val cardAccents: List<Color>
)

private val DarkLearningPalette = LearningPalette(
    success = MintGreen,
    successContainer = MintGreenContainer,
    gold = GoldenYellow,
    goldContainer = GoldenYellowContainer,
    sky = SkyBlue,
    skyContainer = SkyBlueContainer,
    cardAccents = listOf(
        CardCobalt, CardEmerald, CardAmber, CardRose,
        CardViolet, CardCyan, CardIndigo, CardTeal
    )
)

private val LightLearningPalette = LearningPalette(
    success = Color(0xFF047857),
    successContainer = Color(0xFFD1FAE5),
    gold = Color(0xFFB45309),
    goldContainer = Color(0xFFFEF3C7),
    sky = Color(0xFF0369A1),
    skyContainer = Color(0xFFE0F2FE),
    cardAccents = listOf(
        CardCobalt, CardEmerald, CardAmber, CardRose,
        CardViolet, CardCyan, CardIndigo, CardTeal
    )
)

val LocalLearningPalette = staticCompositionLocalOf { DarkLearningPalette }

private val NavyDarkColorScheme = darkColorScheme(
    primary = GoldenYellow,
    onPrimary = NavyDeep,
    primaryContainer = GoldenYellowContainer,
    onPrimaryContainer = GoldenYellow,
    secondary = SkyBlue,
    onSecondary = NavyDeep,
    secondaryContainer = SkyBlueContainer,
    onSecondaryContainer = SkyBlue,
    tertiary = MintGreen,
    onTertiary = NavyDeep,
    tertiaryContainer = MintGreenContainer,
    onTertiaryContainer = MintGreen,
    background = NavyDeep,
    onBackground = Color.White,
    surface = NavyPrimary,
    onSurface = Color.White,
    surfaceVariant = NavySurface,
    onSurfaceVariant = Color(0xFFCBD5E1),
    surfaceContainerHigh = NavySurfaceVariant,
    outline = NavyOutline,
    error = CoralRed,
    onError = Color.White,
    errorContainer = CoralRedContainer,
    onErrorContainer = CoralRed
)

private val CrispLightColorScheme = lightColorScheme(
    primary = LightPrimary,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFDCE7FF),
    onPrimaryContainer = LightPrimary,
    secondary = LightSecondary,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFEF3C7),
    onSecondaryContainer = Color(0xFF78350F),
    tertiary = LightTertiary,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFE0F2FE),
    onTertiaryContainer = Color(0xFF075985),
    background = LightBackground,
    onBackground = LightTextPrimary,
    surface = LightSurface,
    onSurface = LightTextPrimary,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = LightTextSecondary,
    surfaceContainerHigh = Color(0xFFEEF3FB),
    outline = LightOutline,
    error = Color(0xFFDC2626),
    onError = Color.White,
    errorContainer = Color(0xFFFEE2E2),
    onErrorContainer = Color(0xFF991B1B)
)

val AppShapes = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(30.dp)
)

enum class AppThemeMode {
    DARK, LIGHT, SYSTEM
}

@Composable
fun KichuiParinaTheme(
    themeMode: AppThemeMode = AppThemeMode.DARK,
    content: @Composable () -> Unit
) {
    val useDark = when (themeMode) {
        AppThemeMode.DARK -> true
        AppThemeMode.LIGHT -> false
        AppThemeMode.SYSTEM -> isSystemInDarkTheme()
    }
    val colorScheme = if (useDark) NavyDarkColorScheme else CrispLightColorScheme
    val learningPalette = if (useDark) DarkLearningPalette else LightLearningPalette

    CompositionLocalProvider(LocalLearningPalette provides learningPalette) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            shapes = AppShapes,
            content = content
        )
    }
}
