package com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.use_case

import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.repository.NoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.utils.NoteOrder
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descendig)
    ) : Flow<List<Note>> {
        return repository.getNotes().map {notes->
            when(noteOrder.orderType){
                is OrderType.Ascending->{
                    when(noteOrder){
                        is NoteOrder.Title->notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date->notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Color->notes.sortedBy { it.title.lowercase() }
                    }
                }
                is OrderType.Descendig->{
                    when(noteOrder){
                        is NoteOrder.Title->notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Date->notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Color->notes.sortedByDescending { it.title.lowercase() }
                    }
                }
            }

        }
    }
}