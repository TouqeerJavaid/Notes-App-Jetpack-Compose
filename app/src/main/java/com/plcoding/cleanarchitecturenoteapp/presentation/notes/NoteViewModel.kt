package com.plcoding.cleanarchitecturenoteapp.presentation.notes

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.use_case.NoteUseCases
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.utils.NoteOrder
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.utils.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState> = _state

    private var recentlyDeletedNote: Note? = null
    private var notesJob : Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descendig))
    }

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType){
                    return
                }

                getNotes(event.noteOrder)
            }

            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNoteDelete(event.note)
                    recentlyDeletedNote = event.note
                }
            }

            is NoteEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }

            is NoteEvent.ToggleOrderSection -> {
                Log.i("TAG", "onEvent: ")
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }

    }




    private fun getNotes(noteOrder : NoteOrder){
        notesJob?.cancel()
        notesJob = noteUseCases.getNotes(noteOrder)
            .onEach {notes->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }.launchIn(viewModelScope)
    }
}