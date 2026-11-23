package com.campuscompanion.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.campuscompanion.ui.theme.CampusCompanionTheme
import com.campuscompanion.ui.theme.CcMotionTokens

/**
 * Campus Companion Cards
 *
 * Rules:
 * - One card treatment across the system
 * - Never nest a card inside another card (Cards are for distinct tappable objects)
 * - Tonal elevation used first, shadows kept soft and minimal
 * - Clickable cards feature Emil scale-down press physics (0.985f)
 */

@Composable
fun CcCard(
    modifier: Modifier = Modifier,
    shape: Shape = CampusCompanionTheme.shapes.medium,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    borderColor: Color = MaterialTheme.colorScheme.outline,
    borderWidth: Dp = 1.dp,
    contentPadding: Dp = CampusCompanionTheme.spacing.cardContentPadding,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = containerColor),
        border = BorderStroke(borderWidth, borderColor),
        elevation = CardDefaults.cardElevation(defaultElevation = CampusCompanionTheme.elevation.level0),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding)
        ) {
            content()
        }
    }
}

@Composable
fun CcElevatedCard(
    modifier: Modifier = Modifier,
    shape: Shape = CampusCompanionTheme.shapes.medium,
    containerColor: Color = CampusCompanionTheme.extendedColors.elevatedSurface,
    borderColor: Color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
    contentPadding: Dp = CampusCompanionTheme.spacing.cardContentPadding,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = containerColor),
        border = BorderStroke(1.dp, borderColor),
        elevation = CardDefaults.cardElevation(defaultElevation = CampusCompanionTheme.elevation.level1),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding)
        ) {
            content()
        }
    }
}

@Composable
fun CcClickableCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = CampusCompanionTheme.shapes.medium,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    borderColor: Color = MaterialTheme.colorScheme.outline,
    contentPadding: Dp = CampusCompanionTheme.spacing.cardContentPadding,
    content: @Composable ColumnScope.() -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) CcMotionTokens.CardPressScale else 1f,
        animationSpec = tween(
            durationMillis = CcMotionTokens.DurationMicro,
            easing = CcMotionTokens.EaseOutCubic
        ),
        label = "CardPressScale"
    )

    Surface(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .scale(scale)
            .semantics { role = Role.Button },
        shape = shape,
        color = containerColor,
        border = BorderStroke(1.dp, borderColor),
        tonalElevation = CampusCompanionTheme.elevation.level1,
        interactionSource = interactionSource,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding)
        ) {
            content()
        }
    }
}

