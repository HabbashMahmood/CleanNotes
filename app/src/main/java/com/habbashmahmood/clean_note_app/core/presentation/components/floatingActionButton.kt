package com.habbashmahmood.clean_note_app.core.presentation.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun floatingButton(
    onClick: () -> Unit,
    backgroundColor: Color = MaterialTheme.colors.secondary,
    tint: Color = MaterialTheme.colors.onSecondary,
    imageVector: ImageVector,
    contentDescription: String?
) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = backgroundColor
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = tint
        )
    }
}