package com.plcoding.cleanarchitecturenoteapp.presentation.notes

import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.utils.NoteOrder

sealed class NoteEvent(){
    data class Order(val noteOrder: NoteOrder) : NoteEvent()
    data class DeleteNote(val note: Note) : NoteEvent()
    object RestoreNote : NoteEvent()
    object ToggleOrderSection : NoteEvent()
}
