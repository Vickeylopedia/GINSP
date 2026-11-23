package com.campuscompanion.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Elevation Tokens
 * Follows Material 3 tonal elevation first:
 * Higher surfaces take a subtle tonal shift (lighter in dark mode, softly elevated in light),
 * with shadows used sparingly and with soft blur.
 */
@Immutable
data class CcElevation(
    val level0: Dp = 0.dp,   // Screen background
    val level1: Dp = 1.dp,   // Default flat cards, list surfaces
    val level2: Dp = 3.dp,   // Tonal raised cards, elevated search bar
    val level3: Dp = 6.dp,   // Menus, dropdowns, floating action button
    val level4: Dp = 8.dp,   // Bottom sheets, navigation bar
    val level5: Dp = 12.dp,  // Modal dialogs, date pickers
)

val LocalCcElevation = staticCompositionLocalOf { CcElevation() }

