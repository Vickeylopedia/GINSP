package com.campuscompanion

import androidx.compose.ui.graphics.Color
import com.campuscompanion.ui.theme.CcElevation
import com.campuscompanion.ui.theme.CcMotionTokens
import com.campuscompanion.ui.theme.CcPalette
import com.campuscompanion.ui.theme.CcRadius
import com.campuscompanion.ui.theme.CcSpacing
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.max
import kotlin.math.min

class DesignSystemTest {

    // Helper to calculate WCAG luminance
    private fun calculateLuminance(color: Color): Double {
        fun transform(channel: Float): Double {
            return if (channel <= 0.03928) {
                channel / 12.92
            } else {
                Math.pow(((channel + 0.055) / 1.055).toDouble(), 2.4)
            }
        }
        val r = transform(color.red)
        val g = transform(color.green)
        val b = transform(color.blue)
        return 0.2126 * r + 0.7152 * g + 0.0722 * b
    }

    // Helper to calculate contrast ratio between two colors
    private fun contrastRatio(c1: Color, c2: Color): Double {
        val l1 = calculateLuminance(c1)
        val l2 = calculateLuminance(c2)
        val lighter = max(l1, l2)
        val darker = min(l1, l2)
        return (lighter + 0.05) / (darker + 0.05)
    }

    @Test
    fun testApprovedBrandColors() {
        assertEquals("Atlantic Ink", Color(0xFF102A43), CcPalette.AtlanticInk)
        assertEquals("Companion Teal", Color(0xFF087F78), CcPalette.CompanionTeal)
        assertEquals("Signal Mint", Color(0xFF42C7B5), CcPalette.SignalMint)
        assertEquals("Soft Mint", Color(0xFFE7F5F2), CcPalette.SoftMint)
        assertEquals("Cloud", Color(0xFFF4F6F8), CcPalette.Cloud)
        assertEquals("Slate", Color(0xFF667788), CcPalette.Slate)
    }

    @Test
    fun testLightModeContrastRatios() {
        val textOnSurface = contrastRatio(CcPalette.LightTextPrimary, CcPalette.LightSurface)
        assertTrue("Primary text on light surface ($textOnSurface) must be >= 4.5:1", textOnSurface >= 4.5)

        val textOnBg = contrastRatio(CcPalette.LightTextPrimary, CcPalette.LightBackground)
        assertTrue("Primary text on light bg ($textOnBg) must be >= 4.5:1", textOnBg >= 4.5)

        val secondaryOnSurface = contrastRatio(CcPalette.LightTextSecondary, CcPalette.LightSurface)
        assertTrue("Secondary text on light surface ($secondaryOnSurface) must be >= 4.5:1", secondaryOnSurface >= 4.5)

        val accentOnSurface = contrastRatio(CcPalette.CompanionTeal, CcPalette.LightSurface)
        assertTrue("Companion Teal on light surface ($accentOnSurface) must be >= 4.5:1", accentOnSurface >= 4.5)

        val textOnSoftMint = contrastRatio(CcPalette.AtlanticInk, CcPalette.SoftMint)
        assertTrue("Atlantic Ink on Soft Mint ($textOnSoftMint) must be >= 4.5:1", textOnSoftMint >= 4.5)
    }

    @Test
    fun testDarkModeContrastRatios() {
        val textOnSurface = contrastRatio(CcPalette.DarkTextPrimary, CcPalette.DarkSurface)
        assertTrue("Primary text on dark surface ($textOnSurface) must be >= 4.5:1", textOnSurface >= 4.5)

        val textOnBg = contrastRatio(CcPalette.DarkTextPrimary, CcPalette.DarkBackground)
        assertTrue("Primary text on dark bg ($textOnBg) must be >= 4.5:1", textOnBg >= 4.5)

        val secondaryOnSurface = contrastRatio(CcPalette.DarkTextSecondary, CcPalette.DarkSurface)
        assertTrue("Secondary text on dark surface ($secondaryOnSurface) must be >= 4.5:1", secondaryOnSurface >= 4.5)

        val accentOnSurface = contrastRatio(CcPalette.SignalMint, CcPalette.DarkSurface)
        assertTrue("Signal Mint on dark surface ($accentOnSurface) must be >= 4.5:1", accentOnSurface >= 4.5)
    }

    @Test
    fun testSpacingConformsTo4dpGrid() {
        val spacing = CcSpacing()
        assertEquals(0f, spacing.xs.value % 4f, 0.01f)
        assertEquals(0f, spacing.s.value % 4f, 0.01f)
        assertEquals(0f, spacing.sm.value % 4f, 0.01f)
        assertEquals(0f, spacing.m.value % 4f, 0.01f)
        assertEquals(0f, spacing.ml.value % 4f, 0.01f)
        assertEquals(0f, spacing.l.value % 4f, 0.01f)
        assertEquals(0f, spacing.xl.value % 4f, 0.01f)
        assertEquals(0f, spacing.xxl.value % 4f, 0.01f)
        assertEquals(0f, spacing.xxxl.value % 4f, 0.01f)
        assertEquals(0f, spacing.huge.value % 4f, 0.01f)
        assertEquals(48f, spacing.minTouchTarget.value, 0.01f)
    }

    @Test
    fun testConcentricRadiusRule() {
        val radius = CcRadius()
        val inner = radius.sm // 8dp
        val padding = CcSpacing().m // 16dp
        val outer = radius.concentricOuter(inner, padding)
        assertEquals(24f, outer.value, 0.01f) // outer = 8 + 16 = 24dp
    }

    @Test
    fun testEmilMotionPrinciples() {
        assertEquals("Button press scale must be 0.97 per Emil Kowalski", 0.97f, CcMotionTokens.ButtonPressScale, 0.001f)
        assertEquals("Never scale from 0; entry starts at 0.95", 0.95f, CcMotionTokens.EntryStartScale, 0.001f)
        assertTrue("Exit must be faster than enter for bottom sheet", CcMotionTokens.ExitDurationSheet < CcMotionTokens.EnterDurationSheet)
        assertTrue("Exit must be faster than enter for toast", CcMotionTokens.ExitDurationToast < CcMotionTokens.EnterDurationToast)
        assertTrue("Exit must be faster than enter for modal", CcMotionTokens.ExitDurationModal < CcMotionTokens.EnterDurationModal)
    }

    @Test
    fun testElevationTokens() {
        val elevation = CcElevation()
        assertTrue(elevation.level0.value < elevation.level1.value)
        assertTrue(elevation.level1.value < elevation.level2.value)
        assertTrue(elevation.level2.value < elevation.level3.value)
        assertTrue(elevation.level3.value < elevation.level4.value)
        assertTrue(elevation.level4.value < elevation.level5.value)
    }
}

