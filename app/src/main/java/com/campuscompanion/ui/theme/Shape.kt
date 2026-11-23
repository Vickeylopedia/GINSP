package com.campuscompanion.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Shape Tokens
 * Governed by the "Soft" radius philosophy (8-16dp).
 * Adheres strictly to the Concentric Corner Rule:
 * Outer radius = Inner radius + Padding
 * Equal radii on parent and child look pinched; this formula ensures harmonious nesting.
 */
val CcShapes = Shapes(
    // Tags, badges, small chips
    extraSmall = RoundedCornerShape(6.dp),
    // Small inputs, tooltips, nested child items
    small = RoundedCornerShape(8.dp),
    // Default interactive cards, text fields, list item surfaces
    medium = RoundedCornerShape(12.dp),
    // Modal dialogs, bottom sheets, featured callout cards
    large = RoundedCornerShape(16.dp),
    // Bottom sheets top corners, prominent hero elements
    extraLarge = RoundedCornerShape(24.dp),
)

@Immutable
data class CcRadius(
    val none: Dp = 0.dp,
    val xs: Dp = 6.dp,
    val sm: Dp = 8.dp,
    val md: Dp = 12.dp,
    val lg: Dp = 16.dp,
    val xl: Dp = 24.dp,
    val full: Dp = 9999.dp,
) {
    /**
     * Calculates concentric outer radius given child radius and internal padding
     */
    fun concentricOuter(innerRadius: Dp, padding: Dp): Dp {
        return innerRadius + padding
    }
}

val LocalCcRadius = staticCompositionLocalOf { CcRadius() }

