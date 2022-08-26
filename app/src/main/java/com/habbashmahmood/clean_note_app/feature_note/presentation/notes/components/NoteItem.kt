package com.habbashmahmood.clean_note_app.feature_note.presentation.notes.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.habbashmahmood.clean_note_app.feature_note.domain.model.Note
import com.habbashmahmood.clean_note_app.ui.theme.DarkGray
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 5.dp,
    cutCornerSize: Dp = 30.dp,
    onDeleteClick: () -> Unit
) {

    val noteColor = if (isSystemInDarkTheme()) DarkGray.toArgb() else note.color

    val iconTintColor = if (isSystemInDarkTheme()) Color(note.color)
    else Color(
        ColorUtils.blendARGB(noteColor, 0x000000, 0.2f)
    )

    Column(
        modifier = modifier
    ) {
        Box(
            modifier = modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .height(260.dp),
        ) {

            Canvas(modifier = Modifier.matchParentSize()) {
                val clipPath = Path().apply {
                    lineTo(size.width - cutCornerSize.toPx(), 0f)
                    lineTo(size.width, cutCornerSize.toPx())
                    lineTo(size.width, size.height)
                    lineTo(0f, size.height)
                    close()
                }

                clipPath(clipPath) {
                    drawRoundRect(
                        color = Color(noteColor),
                        size = size,
                        cornerRadius = CornerRadius(cornerRadius.toPx())
                    )
                    drawRoundRect(
                        color = iconTintColor,
                        topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                        size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f),
                        cornerRadius = CornerRadius(cornerRadius.toPx())
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp)
                    .padding(top = 30.dp, bottom = 10.dp)
            ) {
                Row {
                    Text(
                        text = note.content,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onPrimary,
                        maxLines = 10,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete note",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            Arrangement.Center
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onPrimary,
                maxLines = 1,
                fontWeight = FontWeight.Medium,
                overflow = TextOverflow.Ellipsis
            )
        }

        val formatter = SimpleDateFormat("d MMM yyyy")
        val dateString = formatter.format(Date(note.timestamp))

        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(bottom = 15.dp)
                .fillMaxWidth(),
            Arrangement.Center
        ) {
            Text(
                text = dateString,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onPrimary,
                maxLines = 1,
                fontWeight = FontWeight.Light,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}