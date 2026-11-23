package com.campuscompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.campuscompanion.ui.shell.AppShell
import com.campuscompanion.ui.theme.CampusCompanionTheme

/**
 * Campus Companion Main Activity
 *
 * Configures:
 * - Android 15 edge-to-edge system bars
 * - Adaptive window width size class
 * - Theme state (Light, Dark, System follow)
 * - Single source of truth Theme provider
 */
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val systemInDark = isSystemInDarkTheme()
            var userThemePreference by remember { mutableStateOf<Boolean?>(null) }
            val isDarkTheme = userThemePreference ?: systemInDark

            val windowSizeClass = calculateWindowSizeClass(this)
            val isExpanded = windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact

            CampusCompanionTheme(darkTheme = isDarkTheme) {
                AppShell(
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = {
                        userThemePreference = !isDarkTheme
                    },
                    isExpandedScreen = isExpanded
                )
            }
        }
    }
}

