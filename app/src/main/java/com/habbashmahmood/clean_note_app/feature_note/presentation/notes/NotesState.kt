package com.habbashmahmood.clean_note_app.feature_note.presentation.notes

import com.habbashmahmood.clean_note_app.feature_note.domain.model.Note
import com.habbashmahmood.clean_note_app.feature_note.domain.util.NoteOrder
import com.habbashmahmood.clean_note_app.feature_note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,
    val toggleSortIcon: Boolean = false,
    val totalNotesCount: Int = 0,
    val isLoading: Boolean = false,
)
