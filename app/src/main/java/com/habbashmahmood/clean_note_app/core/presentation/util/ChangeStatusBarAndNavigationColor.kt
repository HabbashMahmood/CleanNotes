package com.habbashmahmood.clean_note_app.core.presentation.util

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.SystemUiController

@Composable
fun ChangeStatusBarAndNavigationColor(
    systemUiController: SystemUiController,
    colorBg: Color
) {
    if (!isSystemInDarkTheme()) {
        SideEffect {
            systemUiController.setStatusBarColor(
                color = colorBg,
                darkIcons = false
            )
            systemUiController.setNavigationBarColor(
                color = colorBg,
                darkIcons = false
            )
        }
    }
}
