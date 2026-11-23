package com.campuscompanion.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Spacing System
 * Strictly based on a 4dp grid: 4, 8, 12, 16, 20, 24, 32, 40, 48, 64.
 * Rule: Related items sit close (8dp). Groups are clearly separated (16-24dp).
 */
@Immutable
data class CcSpacing(
    val xxs: Dp = 2.dp,
    val xs: Dp = 4.dp,
    val s: Dp = 8.dp,
    val sm: Dp = 12.dp,
    val m: Dp = 16.dp,
    val ml: Dp = 20.dp,
    val l: Dp = 24.dp,
    val xl: Dp = 32.dp,
    val xxl: Dp = 40.dp,
    val xxxl: Dp = 48.dp,
    val huge: Dp = 64.dp,

    // Screen-level conventions
    val screenHorizontal: Dp = 16.dp,
    val screenVertical: Dp = 16.dp,
    val cardContentPadding: Dp = 16.dp,
    val listGutter: Dp = 12.dp,
    val bottomSheetPadding: Dp = 24.dp,

    // Accessibility touch target minimum
    val minTouchTarget: Dp = 48.dp,
    val touchTargetGutter: Dp = 8.dp,
)

val LocalCcSpacing = staticCompositionLocalOf { CcSpacing() }

