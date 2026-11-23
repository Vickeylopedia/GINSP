package com.campuscompanion.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.campuscompanion.ui.theme.CampusCompanionTheme

/**
 * Campus Companion Badges & Status Indicators
 *
 * Craft Floor Rule:
 * Status is NEVER conveyed by color alone.
 * Every status badge pairs color with an explicit ICON and TEXT label.
 */

enum class CcStatusType {
    Success,
    Warning,
    Error,
    Info,
    Brand,
}

@Composable
fun CcStatusBadge(
    text: String,
    statusType: CcStatusType,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
) {
    val (bgColor, contentColor, defaultIcon) = when (statusType) {
        CcStatusType.Success -> Triple(
            CampusCompanionTheme.extendedColors.successContainer,
            CampusCompanionTheme.extendedColors.onSuccessContainer,
            Icons.Default.CheckCircle
        )
        CcStatusType.Warning -> Triple(
            CampusCompanionTheme.extendedColors.warningContainer,
            CampusCompanionTheme.extendedColors.onWarningContainer,
            Icons.Default.Warning
        )
        CcStatusType.Error -> Triple(
            MaterialTheme.colorScheme.errorContainer,
            MaterialTheme.colorScheme.onErrorContainer,
            Icons.Default.Warning
        )
        CcStatusType.Info -> Triple(
            CampusCompanionTheme.extendedColors.infoContainer,
            CampusCompanionTheme.extendedColors.onInfoContainer,
            Icons.Default.Info
        )
        CcStatusType.Brand -> Triple(
            CampusCompanionTheme.extendedColors.brandContainer,
            CampusCompanionTheme.extendedColors.onBrandContainer,
            Icons.Default.Info
        )
    }

    val displayIcon = icon ?: defaultIcon

    Row(
        modifier = modifier
            .clip(CampusCompanionTheme.shapes.extraSmall)
            .background(bgColor)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = displayIcon,
            contentDescription = null,
            modifier = Modifier.size(12.dp),
            tint = contentColor
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
            color = contentColor
        )
    }
}

@Composable
fun CcCountBadge(
    count: Int,
    modifier: Modifier = Modifier,
) {
    Badge(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
    ) {
        Text(
            text = if (count > 99) "99+" else count.toString(),
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}

