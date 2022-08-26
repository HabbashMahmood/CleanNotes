package com.habbashmahmood.clean_note_app.feature_note.domain.use_case

import com.habbashmahmood.clean_note_app.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetTotalNotesCount(
    private val repository: NoteRepository
) {
    operator fun invoke(): Flow<Int> {
        return repository.totalNotesCount()
    }
}