package com.campuscompanion.ui.theme

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Motion System
 * Integrates Emil Kowalski Design Engineering & Mobile-Design principles:
 *
 * 1. Animation Decision Framework:
 *    - Frequency gate: 100+/day (keyboard, tabs) -> instant (0ms)
 *    - Tens/day (selection, lists) -> fast (150-200ms)
 *    - Occasional (sheets, modals, snackbar) -> standard (250-350ms)
 * 2. Easing rules:
 *    - Never use ease-in for entering UI (feels sluggish). Always ease-out for immediate feedback.
 *    - Exits are faster and subtler than enters (user attention has moved on).
 * 3. Emil scale rules:
 *    - Button press scale: 0.97f (never excessive, snappy response).
 *    - Never scale from 0f; entry scale starts from 0.95f with opacity fade.
 * 4. Reduced Motion path:
 *    - When system "Remove animations" is on, durations collapse to instant or 0ms crossfade.
 */
object CcMotionTokens {
    // Durations in milliseconds
    const val DurationInstant = 0
    const val DurationMicro = 100      // Press down feedback, touch highlights
    const val DurationShort = 150      // Tooltips, badges, toggle switches
    const val DurationMedium = 250     // Expanding rows, tab crossfades, menus
    const val DurationLong = 300       // Bottom sheet entry, card expansions
    const val DurationModal = 350      // Dialog enter, screen transitions

    // Asymmetric enter vs exit durations (Exit is faster than enter)
    const val EnterDurationSheet = 300
    const val ExitDurationSheet = 200

    const val EnterDurationToast = 350
    const val ExitDurationToast = 200

    const val EnterDurationModal = 250
    const val ExitDurationModal = 180

    // Scale factors
    const val ButtonPressScale = 0.97f
    const val CardPressScale = 0.985f
    const val EntryStartScale = 0.95f

    // Spring damping & response specifications
    // Utility movement (dialogs, toggles) -> Critically damped (1.0 damping, 0.3s response)
    const val SpringDampingUtility = 1.0f
    const val SpringResponseUtility = 0.3f

    // Gesture release (sheets, drawers) -> Gentle settling (0.85f damping, 0.3s response)
    const val SpringDampingSheet = 0.85f
    const val SpringResponseSheet = 0.3f

    // Easing curves
    // Standard M3 / Emil recommended custom ease-out
    val EaseOutCubic: Easing = CubicBezierEasing(0.33f, 1f, 0.68f, 1f)
    val EaseOutQuart: Easing = CubicBezierEasing(0.25f, 1f, 0.5f, 1f)
    val EmphasizedDecelerate: Easing = CubicBezierEasing(0.05f, 0.7f, 0.1f, 1.0f)
    val EmphasizedAccelerate: Easing = CubicBezierEasing(0.3f, 0.0f, 0.8f, 0.15f)

    // Standard Fallbacks
    val StandardDecelerate: Easing = LinearOutSlowInEasing
    val StandardAccelerateDecelerate: Easing = FastOutSlowInEasing
}

@Immutable
data class CcMotion(
    val durationInstant: Int = CcMotionTokens.DurationInstant,
    val durationMicro: Int = CcMotionTokens.DurationMicro,
    val durationShort: Int = CcMotionTokens.DurationShort,
    val durationMedium: Int = CcMotionTokens.DurationMedium,
    val durationLong: Int = CcMotionTokens.DurationLong,
    val durationModal: Int = CcMotionTokens.DurationModal,
    val buttonPressScale: Float = CcMotionTokens.ButtonPressScale,
    val entryStartScale: Float = CcMotionTokens.EntryStartScale,
    val easeOut: Easing = CcMotionTokens.EaseOutCubic,
    val emphasizedDecelerate: Easing = CcMotionTokens.EmphasizedDecelerate,
    val isReducedMotion: Boolean = false,
)

val LocalCcMotion = staticCompositionLocalOf { CcMotion() }

