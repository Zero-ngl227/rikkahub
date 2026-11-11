package me.rerere.rikkahub.ui.theme.presets

import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import me.rerere.rikkahub.ui.theme.PresetTheme

val RibbonThemePreset by lazy {
    PresetTheme(
        id = "ribbon",
        name = { Text("缎带") },
        standardLight = ribbonLightScheme,
        standardDark = ribbonDarkScheme,
    )
}

//region Ribbon Theme Colors — 缎带粉 / 做旧金 / 黑蕾丝 / 象牙纸
private val primaryLight = Color(0xFFA9546D)
private val onPrimaryLight = Color(0xFFFFFFFF)
private val primaryContainerLight = Color(0xFFFFD9E0)
private val onPrimaryContainerLight = Color(0xFF571B2B)
private val secondaryLight = Color(0xFF7A5A62)
private val onSecondaryLight = Color(0xFFFFFFFF)
private val secondaryContainerLight = Color(0xFFF6DDE1)
private val onSecondaryContainerLight = Color(0xFF5D3F47)
private val tertiaryLight = Color(0xFF7A5B22)
private val onTertiaryLight = Color(0xFFFFFFFF)
private val tertiaryContainerLight = Color(0xFFF5E1B8)
private val onTertiaryContainerLight = Color(0xFF4C3708)
private val errorLight = Color(0xFFBA1A1A)
private val onErrorLight = Color(0xFFFFFFFF)
private val errorContainerLight = Color(0xFFFFDAD6)
private val onErrorContainerLight = Color(0xFF93000A)
private val backgroundLight = Color(0xFFFBF4EE)
private val onBackgroundLight = Color(0xFF2A2022)
private val surfaceLight = Color(0xFFFBF4EE)
private val onSurfaceLight = Color(0xFF2A2022)
private val surfaceVariantLight = Color(0xFFF0DFE1)
private val onSurfaceVariantLight = Color(0xFF524649)
private val outlineLight = Color(0xFF8A7276)
private val outlineVariantLight = Color(0xFFDBC5C8)
private val scrimLight = Color(0xFF000000)
private val inverseSurfaceLight = Color(0xFF3A2F31)
private val inverseOnSurfaceLight = Color(0xFFFBEFF0)
private val inversePrimaryLight = Color(0xFFF5B3C2)
private val surfaceDimLight = Color(0xFFE3D4D0)
private val surfaceBrightLight = Color(0xFFFFFBF7)
private val surfaceContainerLowestLight = Color(0xFFFFFFFF)
private val surfaceContainerLowLight = Color(0xFFF9EFEA)
private val surfaceContainerLight = Color(0xFFF5E9E5)
private val surfaceContainerHighLight = Color(0xFFEFE2DF)
private val surfaceContainerHighestLight = Color(0xFFE9DCD9)

private val primaryDark = Color(0xFFF7B6C4)
private val onPrimaryDark = Color(0xFF4A1526)
private val primaryContainerDark = Color(0xFF6B2C3E)
private val onPrimaryContainerDark = Color(0xFFFFD9E1)
private val secondaryDark = Color(0xFFE5C6CB)
private val onSecondaryDark = Color(0xFF412A2F)
private val secondaryContainerDark = Color(0xFF5A4048)
private val onSecondaryContainerDark = Color(0xFFF8DAE0)
private val tertiaryDark = Color(0xFFE9C98E)
private val onTertiaryDark = Color(0xFF3E2C08)
private val tertiaryContainerDark = Color(0xFF57431A)
private val onTertiaryContainerDark = Color(0xFFF7E3B9)
private val errorDark = Color(0xFFFFB4AB)
private val onErrorDark = Color(0xFF690005)
private val errorContainerDark = Color(0xFF93000A)
private val onErrorContainerDark = Color(0xFFFFDAD6)
private val backgroundDark = Color(0xFF1B151C)
private val onBackgroundDark = Color(0xFFF2E4E7)
private val surfaceDark = Color(0xFF1B151C)
private val onSurfaceDark = Color(0xFFF2E4E7)
private val surfaceVariantDark = Color(0xFF58464A)
private val onSurfaceVariantDark = Color(0xFFDCC3C7)
private val outlineDark = Color(0xFFA38B90)
private val outlineVariantDark = Color(0xFF58464A)
private val scrimDark = Color(0xFF000000)
private val inverseSurfaceDark = Color(0xFFF2E4E7)
private val inverseOnSurfaceDark = Color(0xFF3A2F31)
private val inversePrimaryDark = Color(0xFFA9546D)
private val surfaceDimDark = Color(0xFF1B151C)
private val surfaceBrightDark = Color(0xFF3E3238)
private val surfaceContainerLowestDark = Color(0xFF130E15)
private val surfaceContainerLowDark = Color(0xFF221A22)
private val surfaceContainerDark = Color(0xFF261D25)
private val surfaceContainerHighDark = Color(0xFF312731)
private val surfaceContainerHighestDark = Color(0xFF3C313B)

private val ribbonLightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val ribbonDarkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)
