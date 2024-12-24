package com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.use_case

import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.repository.NoteRepository

class GetNoteById (val noteRepository: NoteRepository){
    suspend operator fun invoke(id : Int) : Note{
        return noteRepository.getNoteById(id)
    }
}