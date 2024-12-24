package com.plcoding.cleanarchitecturenoteapp.presentation.notes

import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.utils.NoteOrder
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.utils.OrderType

data class NoteState (
    val notes : List<Note> = emptyList(),
    val noteOrder : NoteOrder = NoteOrder.Date(OrderType.Descendig),
    val isOrderSectionVisible : Boolean = false
)