package com.habbashmahmood.clean_note_app.feature_note.presentation.add_edit_note

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.habbashmahmood.clean_note_app.core.presentation.components.floatingButton
import com.habbashmahmood.clean_note_app.feature_note.domain.model.Note
import com.habbashmahmood.clean_note_app.feature_note.presentation.add_edit_note.components.TransparentHintTextField
import com.habbashmahmood.clean_note_app.core.presentation.util.ChangeStatusBarAndNavigationColor
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    viewModel: AddEditNoteViewModel = hiltViewModel(),
    systemUiController: SystemUiController
) {
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value
    val colorState = viewModel.noteColor.value

    val scaffoldState = rememberScaffoldState()

    val textColor = if (isSystemInDarkTheme())
        MaterialTheme.colors.onPrimary
    else
        MaterialTheme.colors.onPrimary

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditNoteViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditNoteViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }

    // Updating Status and Navigation Color w.r.t Selected Color
    ChangeStatusBarAndNavigationColor(systemUiController, Color(colorState))

    Scaffold(
        floatingActionButton = {
            floatingButton(
                imageVector = Icons.Default.Save,
                contentDescription = "Save note",
                onClick = {
                    viewModel.onEvent(AddEditNoteEvent.SaveNote)
                },
            )
        },
        scaffoldState = scaffoldState
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    if (isSystemInDarkTheme())
                        MaterialTheme.colors.primary
                    else
                        Color(colorState)
                )
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Note.noteColors.forEach { color ->

                    val borderColor = if (isSystemInDarkTheme()) {
                        MaterialTheme.colors.onPrimary
                    } else {
                        Color.Black
                    }

                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 4.dp,
                                color = if (colorState == color.toArgb()) {
                                    borderColor
                                } else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                viewModel.onEvent(AddEditNoteEvent.ChangeColor(color.toArgb()))
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
                },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5.copy(color = textColor)
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = contentState.text,
                hint = contentState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
                },
                isHintVisible = contentState.isHintVisible,
                textStyle = MaterialTheme.typography.body1.copy(color = textColor),
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}