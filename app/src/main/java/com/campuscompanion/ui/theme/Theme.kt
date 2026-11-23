package com.campuscompanion.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

/**
 * Campus Companion Theme
 *
 * Single source of truth for the Campus Companion design system.
 * Supports:
 * - Light theme
 * - Dark theme
 * - System theme following
 * - Complete design tokens: Color, Extended Colors, Typography, Shapes, Spacing, Elevation, Motion
 */
@Composable
fun CampusCompanionTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) CcDarkColorScheme else CcLightColorScheme
    val extendedColors = if (darkTheme) CcDarkExtendedColors else CcLightExtendedColors
    val spacing = CcSpacing()
    val radius = CcRadius()
    val elevation = CcElevation()
    val motion = CcMotion()

    CompositionLocalProvider(
        LocalCcExtendedColors provides extendedColors,
        LocalCcSpacing provides spacing,
        LocalCcRadius provides radius,
        LocalCcElevation provides elevation,
        LocalCcMotion provides motion,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = CcTypography,
            shapes = CcShapes,
            content = content,
        )
    }
}

/**
 * Convenient accessor object for Campus Companion design tokens
 */
object CampusCompanionTheme {
    val extendedColors: CcExtendedColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCcExtendedColors.current

    val spacing: CcSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalCcSpacing.current

    val radius: CcRadius
        @Composable
        @ReadOnlyComposable
        get() = LocalCcRadius.current

    val elevation: CcElevation
        @Composable
        @ReadOnlyComposable
        get() = LocalCcElevation.current

    val motion: CcMotion
        @Composable
        @ReadOnlyComposable
        get() = LocalCcMotion.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.shapes
}

