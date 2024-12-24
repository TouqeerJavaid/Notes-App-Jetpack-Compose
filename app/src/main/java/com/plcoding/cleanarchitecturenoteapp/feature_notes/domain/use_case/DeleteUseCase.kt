package com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.use_case

import com.plcoding.cleanarchitecturenoteapp.feature_notes.data.repository.NoteRepositoryImpl
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.repository.NoteRepository

class DeleteUseCase(val noteRepository: NoteRepository){
    suspend operator fun invoke(note : Note){
        noteRepository.deleteNote(note)
    }
}