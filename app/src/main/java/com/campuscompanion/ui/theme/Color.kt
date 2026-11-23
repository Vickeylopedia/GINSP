package com.campuscompanion.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Campus Companion Brand Colors
 * Approved brand identity palette:
 * - Atlantic Ink: #102A43 (deep primary ink)
 * - Companion Teal: #087F78 (core brand anchor)
 * - Signal Mint: #42C7B5 (vibrant accent / dark mode hero)
 * - Soft Mint: #E7F5F2 (gentle tint / container surface)
 * - Cloud: #F4F6F8 (clean neutral light background)
 * - Slate: #667788 (balanced secondary neutral)
 */
object CcPalette {
    val AtlanticInk = Color(0xFF102A43)
    val CompanionTeal = Color(0xFF087F78)
    val SignalMint = Color(0xFF42C7B5)
    val SoftMint = Color(0xFFE7F5F2)
    val Cloud = Color(0xFFF4F6F8)
    val Slate = Color(0xFF667788)

    // Derived neutral tones for dark theme surfaces and borders
    val DarkBackground = Color(0xFF0D1824)
    val DarkSurface = Color(0xFF152535)
    val DarkElevatedSurface = Color(0xFF1B3042)
    val DarkBorder = Color(0xFF2B4050)
    val DarkTextPrimary = Color(0xFFF0F6F5)
    val DarkTextSecondary = Color(0xFFA6B7C2)

    // Light theme surface and border tokens
    val LightBackground = Color(0xFFF4F6F8)
    val LightSurface = Color(0xFFFFFFFF)
    val LightElevatedSurface = Color(0xFFF9FBFC)
    val LightBorder = Color(0xFFE3E9EC)
    val LightTextPrimary = Color(0xFF102A43)
    val LightTextSecondary = Color(0xFF667788)

    // Functional status colors (paired with icons in components, never color alone)
    val Success = Color(0xFF0D8050)
    val SuccessContainer = Color(0xFFE6F5ED)
    val OnSuccessContainer = Color(0xFF07482D)

    val Warning = Color(0xFFB25E00)
    val WarningContainer = Color(0xFFFFF4E5)
    val OnWarningContainer = Color(0xFF5C2F00)

    val Error = Color(0xFFBA1A1A)
    val ErrorContainer = Color(0xFFFFDAD6)
    val OnErrorContainer = Color(0xFF410002)

    val Info = Color(0xFF006399)
    val InfoContainer = Color(0xFFCCE5FF)
    val OnInfoContainer = Color(0xFF003355)

    // Dark functional colors (adjusted for high contrast and comfortable luminance)
    val DarkSuccess = Color(0xFF4FD890)
    val DarkSuccessContainer = Color(0xFF00522B)
    val DarkWarning = Color(0xFFFFB77C)
    val DarkWarningContainer = Color(0xFF6E3900)
    val DarkError = Color(0xFFFFB4AB)
    val DarkErrorContainer = Color(0xFF93000A)
    val DarkInfo = Color(0xFF8BCEFF)
    val DarkInfoContainer = Color(0xFF004B75)
}

/**
 * Extended semantic tokens specific to Campus Companion
 */
@Immutable
data class CcExtendedColors(
    val elevatedSurface: Color,
    val border: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val success: Color,
    val successContainer: Color,
    val onSuccessContainer: Color,
    val warning: Color,
    val warningContainer: Color,
    val onWarningContainer: Color,
    val info: Color,
    val infoContainer: Color,
    val onInfoContainer: Color,
    val brandContainer: Color,
    val onBrandContainer: Color,
)

val LocalCcExtendedColors = staticCompositionLocalOf {
    CcExtendedColors(
        elevatedSurface = CcPalette.LightElevatedSurface,
        border = CcPalette.LightBorder,
        textPrimary = CcPalette.LightTextPrimary,
        textSecondary = CcPalette.LightTextSecondary,
        success = CcPalette.Success,
        successContainer = CcPalette.SuccessContainer,
        onSuccessContainer = CcPalette.OnSuccessContainer,
        warning = CcPalette.Warning,
        warningContainer = CcPalette.WarningContainer,
        onWarningContainer = CcPalette.OnWarningContainer,
        info = CcPalette.Info,
        infoContainer = CcPalette.InfoContainer,
        onInfoContainer = CcPalette.OnInfoContainer,
        brandContainer = CcPalette.SoftMint,
        onBrandContainer = CcPalette.AtlanticInk,
    )
}

/**
 * Material 3 Light Color Scheme mapped to approved brand tokens
 */
val CcLightColorScheme: ColorScheme = lightColorScheme(
    primary = CcPalette.CompanionTeal,
    onPrimary = Color.White,
    primaryContainer = CcPalette.SoftMint,
    onPrimaryContainer = CcPalette.AtlanticInk,
    secondary = CcPalette.AtlanticInk,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE2E9F0),
    onSecondaryContainer = CcPalette.AtlanticInk,
    tertiary = CcPalette.SignalMint,
    onTertiary = CcPalette.AtlanticInk,
    tertiaryContainer = Color(0xFFD0F3EE),
    onTertiaryContainer = Color(0xFF004F47),
    background = CcPalette.LightBackground,
    onBackground = CcPalette.LightTextPrimary,
    surface = CcPalette.LightSurface,
    onSurface = CcPalette.LightTextPrimary,
    surfaceVariant = CcPalette.LightElevatedSurface,
    onSurfaceVariant = CcPalette.LightTextSecondary,
    outline = CcPalette.LightBorder,
    outlineVariant = Color(0xFFD0D9DF),
    error = CcPalette.Error,
    onError = Color.White,
    errorContainer = CcPalette.ErrorContainer,
    onErrorContainer = CcPalette.OnErrorContainer,
)

/**
 * Material 3 Dark Color Scheme mapped to approved brand tokens
 */
val CcDarkColorScheme: ColorScheme = darkColorScheme(
    primary = CcPalette.SignalMint,
    onPrimary = CcPalette.DarkBackground,
    primaryContainer = Color(0xFF0A4F4A),
    onPrimaryContainer = Color(0xFFB4F2EA),
    secondary = CcPalette.DarkTextPrimary,
    onSecondary = CcPalette.DarkBackground,
    secondaryContainer = CcPalette.DarkElevatedSurface,
    onSecondaryContainer = CcPalette.DarkTextPrimary,
    tertiary = CcPalette.CompanionTeal,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFF064743),
    onTertiaryContainer = Color(0xFF9EE0DA),
    background = CcPalette.DarkBackground,
    onBackground = CcPalette.DarkTextPrimary,
    surface = CcPalette.DarkSurface,
    onSurface = CcPalette.DarkTextPrimary,
    surfaceVariant = CcPalette.DarkElevatedSurface,
    onSurfaceVariant = CcPalette.DarkTextSecondary,
    outline = CcPalette.DarkBorder,
    outlineVariant = Color(0xFF384E60),
    error = CcPalette.DarkError,
    onError = Color(0xFF690005),
    errorContainer = CcPalette.DarkErrorContainer,
    onErrorContainer = Color(0xFFFFDAD6),
)

val CcLightExtendedColors = CcExtendedColors(
    elevatedSurface = CcPalette.LightElevatedSurface,
    border = CcPalette.LightBorder,
    textPrimary = CcPalette.LightTextPrimary,
    textSecondary = CcPalette.LightTextSecondary,
    success = CcPalette.Success,
    successContainer = CcPalette.SuccessContainer,
    onSuccessContainer = CcPalette.OnSuccessContainer,
    warning = CcPalette.Warning,
    warningContainer = CcPalette.WarningContainer,
    onWarningContainer = CcPalette.OnWarningContainer,
    info = CcPalette.Info,
    infoContainer = CcPalette.InfoContainer,
    onInfoContainer = CcPalette.OnInfoContainer,
    brandContainer = CcPalette.SoftMint,
    onBrandContainer = CcPalette.AtlanticInk,
)

val CcDarkExtendedColors = CcExtendedColors(
    elevatedSurface = CcPalette.DarkElevatedSurface,
    border = CcPalette.DarkBorder,
    textPrimary = CcPalette.DarkTextPrimary,
    textSecondary = CcPalette.DarkTextSecondary,
    success = CcPalette.DarkSuccess,
    successContainer = CcPalette.DarkSuccessContainer,
    onSuccessContainer = Color(0xFFB5F4D0),
    warning = CcPalette.DarkWarning,
    warningContainer = CcPalette.DarkWarningContainer,
    onWarningContainer = Color(0xFFFFDCC2),
    info = CcPalette.DarkInfo,
    infoContainer = CcPalette.DarkInfoContainer,
    onInfoContainer = Color(0xFFD1ECFF),
    brandContainer = Color(0xFF0F3A37),
    onBrandContainer = CcPalette.SignalMint,
)

