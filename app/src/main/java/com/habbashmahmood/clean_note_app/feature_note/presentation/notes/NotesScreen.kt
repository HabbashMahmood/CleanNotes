package com.habbashmahmood.clean_note_app.feature_note.presentation.notes

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.habbashmahmood.clean_note_app.core.presentation.components.floatingButton
import com.habbashmahmood.clean_note_app.core.presentation.util.ChangeStatusBarAndNavigationColor
import com.habbashmahmood.clean_note_app.core.setup.navigation.Screen
import com.habbashmahmood.clean_note_app.feature_note.domain.util.NoteOrder
import com.habbashmahmood.clean_note_app.feature_note.domain.util.OrderType
import com.habbashmahmood.clean_note_app.feature_note.presentation.notes.components.NoteItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@Composable
fun NotesScreen(
    navController: NavController,
    systemUiController: SystemUiController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    ChangeStatusBarAndNavigationColor(systemUiController, Color.White)

    Scaffold(
        floatingActionButton = {
            floatingButton(
                imageVector = Icons.Default.Add,
                contentDescription = "Add note",
                onClick = {
                    navController.navigate(Screen.AddEditNoteScreen.route)
                },
            )
        },
        scaffoldState = scaffoldState,
    ) {

        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .defaultMinSize(minHeight = 64.dp)
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight(),
                ) {
                    Row {
                        Text(
                            text = "All Notes",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.onPrimary,
                        )
                    }
                    if (state.notes.isNotEmpty()) {
                        Row {
                            Text(
                                text = "Count: ${state.notes.size}",
                                style = MaterialTheme.typography.subtitle2,
                                color = Color.DarkGray,
                            )
                        }
                    }

                }
                Column {
                    Row {
                        IconButton(
                            onClick = {
                                navController.navigate(Screen.SearchScreen.route)
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = MaterialTheme.colors.onPrimary,
                            )
                        }
                        IconButton(
                            onClick = {
                                navController.navigate(Screen.SettingsScreen.route)
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Setting",
                                tint = MaterialTheme.colors.onPrimary,
                            )
                        }
                    }
                }
            }

            if (state.isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        color = Color.DarkGray
                    )
                }

            } else {
                if (state.notes.isNotEmpty()) {
                    showFiltersAndList(viewModel, state, navController, scaffoldState, scope)
                } else {
                    NoRecordText()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun showFiltersAndList(
    viewModel: NotesViewModel,
    state: NotesState,
    navController: NavController,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope
) {
    var showMenu by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(
            modifier = Modifier.background(Color.Transparent),
            onClick = {
                showMenu = !showMenu
            },
        ) {
            Icon(
                imageVector = Icons.Default.Sort,
                contentDescription = "Sort",
                tint = MaterialTheme.colors.onPrimary,
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = when (state.noteOrder) {
                    is NoteOrder.Title -> "Title"
                    is NoteOrder.Date -> "Date"
                    is NoteOrder.Color -> "Color"
                },
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.onPrimary,
                maxLines = 1,
                fontWeight = FontWeight.Light,
                overflow = TextOverflow.Ellipsis
            )
        }

        if (showMenu) {
            FilterDropDownMenu(
                expanded = showMenu,
                noteOrder = state.noteOrder,
                onDismissRequest = { showMenu = false },
                onSelection = {
                    viewModel.onEvent(NotesEvent.Order(it))
                }
            )
        }

        Divider(
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .height(22.dp)
                .width(1.dp)
        )

        IconButton(
            onClick = {
                viewModel.onEvent(
                    NotesEvent.Order(
                        state.noteOrder.toggle(
                            state.noteOrder
                        )
                    )
                )
            },
        ) {
            Icon(
                imageVector = when (state.noteOrder.orderType) {
                    is OrderType.Descending -> Icons.Default.ArrowUpward
                    is OrderType.Ascending -> Icons.Default.ArrowDownward
                },
                contentDescription = "Sort",
                tint = MaterialTheme.colors.onPrimary,
            )
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
            .padding(horizontal = 5.dp),
    ) {

        items(state.notes) { note ->
            NoteItem(
                note = note,
                modifier = Modifier
                    .clickable {
                        navController.navigate(
                            Screen.AddEditNoteScreen.route +
                                    "?noteId=${note.id}&noteColor=${note.color}"
                        )
                    },
                onDeleteClick = {
                    viewModel.onEvent(NotesEvent.DeleteNote(note))
                    scope.launch {
                        val result = scaffoldState.snackbarHostState.showSnackbar(
                            message = "Note deleted",
                            actionLabel = "Undo"
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            viewModel.onEvent(NotesEvent.RestoreNote)
                        }
                    }
                }
            )
        }

    }
}

@Composable
fun FilterDropDownMenu(
    expanded: Boolean,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onDismissRequest: () -> Unit,
    onSelection: (NoteOrder) -> Unit,
) {
    val title = "Title"
    val date = "Date"
    val color = "Color"

    val options = listOf(title, date, color)

    Box(
        modifier = Modifier.padding(top = 35.dp)
    ) {
        DropdownMenu(
            modifier = Modifier.background(
                if (isSystemInDarkTheme()) {
                    Color.DarkGray
                } else {
                    MaterialTheme.colors.primary
                }
            ),
            expanded = expanded,
            onDismissRequest = onDismissRequest,
        ) {

            options.forEach { selectionOption ->
                ListDropDownItem(
                    text = selectionOption,
                    onSelect = {
                        when (selectionOption) {
                            title -> onSelection(NoteOrder.Title(noteOrder.orderType))
                            date -> onSelection(NoteOrder.Date(noteOrder.orderType))
                            color -> onSelection(NoteOrder.Color(noteOrder.orderType))
                        }
                        onDismissRequest()
                    }
                )
            }
        }
    }
}

@Composable
fun ListDropDownItem(
    text: String,
    onSelect: () -> Unit,
) {
    DropdownMenuItem(
        onClick = onSelect
    ) {
        Text(
            text = text,
            color = if (isSystemInDarkTheme()) {
                MaterialTheme.colors.onPrimary
            } else {
                MaterialTheme.colors.onPrimary
            }
        )
    }
}

@Composable
fun NoRecordText(
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Click + icon to add new notes",
            color = Color.DarkGray,
            fontSize = MaterialTheme.typography.h6.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}