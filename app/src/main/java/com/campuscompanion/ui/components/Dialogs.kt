package com.campuscompanion.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.DialogProperties
import com.campuscompanion.ui.theme.CampusCompanionTheme

/**
 * Campus Companion Dialogs
 *
 * Rules:
 * - Emil Kowalski: Modals stay centered (never scale from trigger), enter with scale(0.95f) + opacity fade
 * - Mobile-Design Floor: Verb-based actions ("Delete course", "Cancel"). Never generic "OK" or "Submit".
 * - Dialogs are reserved for critical decisions that MUST interrupt.
 */

@Composable
fun CcAlertDialog(
    title: String,
    message: String,
    confirmText: String,
    onConfirm: () -> Unit,
    dismissText: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    isDestructive: Boolean = false,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = modifier,
        icon = icon?.let {
            {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = if (isDestructive) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                )
            }
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        text = {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        confirmButton = {
            if (isDestructive) {
                CcFilledButton(
                    text = confirmText,
                    onClick = onConfirm,
                )
            } else {
                CcFilledButton(
                    text = confirmText,
                    onClick = onConfirm,
                )
            }
        },
        dismissButton = {
            CcTextButton(
                text = dismissText,
                onClick = onDismiss,
            )
        },
        shape = CampusCompanionTheme.shapes.large,
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = CampusCompanionTheme.elevation.level5,
        properties = DialogProperties(usePlatformDefaultWidth = true),
    )
}

