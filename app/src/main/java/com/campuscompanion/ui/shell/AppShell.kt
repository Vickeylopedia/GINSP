package com.campuscompanion.ui.shell

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.campuscompanion.ui.components.CcAlertDialog
import com.campuscompanion.ui.components.CcClickableCard
import com.campuscompanion.ui.components.CcCountBadge
import com.campuscompanion.ui.components.CcEmptyState
import com.campuscompanion.ui.components.CcFilledButton
import com.campuscompanion.ui.components.CcListItem
import com.campuscompanion.ui.components.CcLoadingSkeleton
import com.campuscompanion.ui.components.CcNavigationBar
import com.campuscompanion.ui.components.CcNavigationItem
import com.campuscompanion.ui.components.CcNavigationRail
import com.campuscompanion.ui.components.CcOutlinedButton
import com.campuscompanion.ui.components.CcSearchField
import com.campuscompanion.ui.components.CcSectionHeader
import com.campuscompanion.ui.components.CcStatusBadge
import com.campuscompanion.ui.components.CcStatusType
import com.campuscompanion.ui.components.CcTabRow
import com.campuscompanion.ui.components.CcTextButton
import com.campuscompanion.ui.components.CcTextField
import com.campuscompanion.ui.components.CcTopAppBar
import com.campuscompanion.ui.theme.CampusCompanionTheme
import com.campuscompanion.ui.theme.CcPalette
import kotlinx.coroutines.launch

/**
 * Campus Companion Application Shell
 *
 * Responsibilities:
 * - Adapts navigation between phone (<600dp, bottom navigation bar) and tablet/expanded (>=600dp, navigation rail)
 * - Manages top app bar, theme toggle, and snackbar host
 * - Embeds the Design System Foundation Showcase for living visual validation
 */

@Composable
fun AppShell(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    isExpandedScreen: Boolean = false,
) {
    var currentRoute by remember { mutableStateOf("design_system") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val navItems = listOf(
        CcNavigationItem("home", "Home", Icons.Filled.Home, Icons.Outlined.Home),
        CcNavigationItem("courses", "Courses", Icons.Filled.Book, Icons.Outlined.Book),
        CcNavigationItem("campus", "Campus", Icons.Filled.LocationOn, Icons.Outlined.LocationOn),
        CcNavigationItem("profile", "Profile", Icons.Filled.Person, Icons.Outlined.Person),
    )

    Row(modifier = Modifier.fillMaxSize()) {
        // Navigation Rail on medium / expanded screens
        if (isExpandedScreen) {
            CcNavigationRail(
                items = navItems,
                selectedRoute = currentRoute,
                onItemClick = { currentRoute = it },
                header = {
                    IconButton(onClick = onToggleTheme) {
                        Icon(
                            imageVector = if (isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
                            contentDescription = "Toggle Theme"
                        )
                    }
                }
            )
        }

        Scaffold(
            modifier = Modifier.weight(1f),
            topBar = {
                CcTopAppBar(
                    title = "Campus Companion",
                    actions = {
                        IconButton(onClick = onToggleTheme) {
                            Icon(
                                imageVector = if (isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
                                contentDescription = if (isDarkTheme) "Switch to Light Theme" else "Switch to Dark Theme",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                )
            },
            bottomBar = {
                if (!isExpandedScreen) {
                    CcNavigationBar(
                        items = navItems,
                        selectedRoute = currentRoute,
                        onItemClick = { currentRoute = it }
                    )
                }
            },
            snackbarHost = { SnackbarHost(snackbarHostState) },
            containerColor = MaterialTheme.colorScheme.background,
        ) { innerPadding ->
            DesignSystemShowcaseContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                onShowSnackbar = { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message, actionLabel = "Dismiss")
                    }
                }
            )
        }
    }
}

@Composable
private fun DesignSystemShowcaseContent(
    modifier: Modifier = Modifier,
    onShowSnackbar: (String) -> Unit,
) {
    val scrollState = rememberScrollState()
    var textFieldValue by remember { mutableStateOf("") }
    var searchFieldValue by remember { mutableStateOf("") }
    var selectedTab by remember { mutableIntStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    var buttonLoading by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(CampusCompanionTheme.spacing.screenHorizontal)
    ) {
        // Brand Header
        Text(
            text = "Design System Foundation",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Your Campus. Connected.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(CampusCompanionTheme.spacing.l))

        // 1. Color Palette Tokens
        CcSectionHeader(title = "Brand & Semantic Palette")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(CampusCompanionTheme.spacing.s)
        ) {
            ColorSwatch(name = "Ink", color = CcPalette.AtlanticInk, modifier = Modifier.weight(1f))
            ColorSwatch(name = "Teal", color = CcPalette.CompanionTeal, modifier = Modifier.weight(1f))
            ColorSwatch(name = "Mint", color = CcPalette.SignalMint, modifier = Modifier.weight(1f))
            ColorSwatch(name = "Soft", color = CcPalette.SoftMint, textColor = CcPalette.AtlanticInk, modifier = Modifier.weight(1f))
            ColorSwatch(name = "Cloud", color = CcPalette.Cloud, textColor = CcPalette.AtlanticInk, modifier = Modifier.weight(1f))
            ColorSwatch(name = "Slate", color = CcPalette.Slate, modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(CampusCompanionTheme.spacing.l))

        // 2. Typography Scale (Inter)
        CcSectionHeader(title = "Inter Typography Scale")
        Surface(
            shape = CampusCompanionTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface,
            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(CampusCompanionTheme.spacing.cardContentPadding)) {
                Text("Display Large 44sp Bold", style = MaterialTheme.typography.displayLarge)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Headline Medium 24sp SemiBold", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Title Large 18sp SemiBold", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Body Large 16sp Regular — Optimal for continuous academic reading and campus guides.", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Label Large 14sp SemiBold — Primary interactive controls", style = MaterialTheme.typography.labelLarge)
            }
        }

        Spacer(modifier = Modifier.height(CampusCompanionTheme.spacing.l))

        // 3. Interactive Buttons (Emil scale 0.97f + 48dp target)
        CcSectionHeader(title = "Buttons & Interactions")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(CampusCompanionTheme.spacing.m)
        ) {
            CcFilledButton(
                text = if (buttonLoading) "Enrolling..." else "Enroll Course",
                loading = buttonLoading,
                onClick = {
                    buttonLoading = true
                    onShowSnackbar("Course enrolled successfully")
                },
                modifier = Modifier.weight(1f)
            )
            CcOutlinedButton(
                text = "Reset State",
                onClick = { buttonLoading = false },
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(CampusCompanionTheme.spacing.s))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CcTextButton(
                text = "Show Modal Dialog",
                onClick = { showDialog = true }
            )
            CcFilledButton(
                text = "Disabled Button",
                enabled = false,
                onClick = {}
            )
        }

        Spacer(modifier = Modifier.height(CampusCompanionTheme.spacing.l))

        // 4. Form Inputs & Search
        CcSectionHeader(title = "Inputs & Form Controls")
        CcSearchField(
            query = searchFieldValue,
            onQueryChange = { searchFieldValue = it },
            placeholder = "Search FUNAAB courses (e.g. CSC 301)..."
        )
        Spacer(modifier = Modifier.height(CampusCompanionTheme.spacing.m))
        CcTextField(
            value = textFieldValue,
            onValueChange = { textFieldValue = it },
            label = "Matriculation Number",
            placeholder = "e.g. 2021/1234",
            helperText = "Enter your 9-digit FUNAAB student ID"
        )

        Spacer(modifier = Modifier.height(CampusCompanionTheme.spacing.l))

        // 5. Status Badges (Never color alone)
        CcSectionHeader(title = "Badges & Status Indicators")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(CampusCompanionTheme.spacing.s)
        ) {
            CcStatusBadge(text = "Active", statusType = CcStatusType.Success)
            CcStatusBadge(text = "Upcoming", statusType = CcStatusType.Warning)
            CcStatusBadge(text = "Overdue", statusType = CcStatusType.Error)
            CcStatusBadge(text = "FUNAAB", statusType = CcStatusType.Brand)
            CcCountBadge(count = 5)
        }

        Spacer(modifier = Modifier.height(CampusCompanionTheme.spacing.l))

        // 6. Tabs
        CcSectionHeader(title = "Tab Navigation")
        CcTabRow(
            selectedTabIndex = selectedTab,
            tabs = listOf("Overview", "Schedule", "Announcements"),
            onTabSelected = { selectedTab = it }
        )

        Spacer(modifier = Modifier.height(CampusCompanionTheme.spacing.l))

        // 7. Clickable Cards & Lists
        CcSectionHeader(title = "Lists & Card Surfaces")
        CcClickableCard(
            onClick = { onShowSnackbar("Card clicked with Emil scale physics") }
        ) {
            Text(
                text = "Interactive Surface",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(CampusCompanionTheme.spacing.xs))
            Text(
                text = "Tonal elevation with 12dp soft radius and subtle press feedback.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.height(CampusCompanionTheme.spacing.m))

        CcListItem(
            headlineText = "CSC 301 — Operating Systems",
            supportingText = "Monday 10:00 AM • Lecture Theatre 2",
            showChevron = true,
            onClick = { onShowSnackbar("Selected CSC 301") }
        )
        CcListItem(
            headlineText = "MTS 201 — Mathematical Methods",
            supportingText = "Wednesday 02:00 PM • ETF Hall",
            showChevron = true,
            onClick = { onShowSnackbar("Selected MTS 201") }
        )

        Spacer(modifier = Modifier.height(CampusCompanionTheme.spacing.l))

        // 8. Loading Skeletons
        CcSectionHeader(title = "Loading State Skeletons")
        Row(modifier = Modifier.fillMaxWidth()) {
            CcLoadingSkeleton(modifier = Modifier.size(48.dp), shape = CampusCompanionTheme.shapes.small)
            Spacer(modifier = Modifier.width(CampusCompanionTheme.spacing.m))
            Column(modifier = Modifier.weight(1f)) {
                CcLoadingSkeleton(modifier = Modifier.fillMaxWidth(0.7f), height = 18.dp)
                Spacer(modifier = Modifier.height(8.dp))
                CcLoadingSkeleton(modifier = Modifier.fillMaxWidth(0.4f), height = 14.dp)
            }
        }

        Spacer(modifier = Modifier.height(CampusCompanionTheme.spacing.huge))
    }

    if (showDialog) {
        CcAlertDialog(
            title = "Confirm Course Drop",
            message = "Are you sure you want to drop CSC 301? This will remove your timetable reminders and exam notifications.",
            confirmText = "Drop Course",
            onConfirm = {
                showDialog = false
                onShowSnackbar("Course dropped")
            },
            dismissText = "Cancel",
            onDismiss = { showDialog = false },
            isDestructive = true
        )
    }
}

@Composable
private fun ColorSwatch(
    name: String,
    color: Color,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
) {
    Box(
        modifier = modifier
            .height(48.dp)
            .clip(CampusCompanionTheme.shapes.small)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            color = textColor
        )
    }
}

