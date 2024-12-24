package com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.use_case

data class NoteUseCases (
    val getNotes: GetNotesUseCase,
    val deleteNoteDelete: DeleteUseCase,
    val addNote: AddNoteUseCase,
    val getNoteById: GetNoteById
)
