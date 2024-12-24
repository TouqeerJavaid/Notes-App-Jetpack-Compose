package com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.use_case

import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.model.InvalidNoteException
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.repository.NoteRepository

class AddNoteUseCase(val noteRepository: NoteRepository){

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note : Note){

        if (note.title.isBlank()){
            throw InvalidNoteException("Note title cannot be empty")
        }else if (note.content.isBlank()){
            throw InvalidNoteException("Note content cannot be empty")
        }

        noteRepository.insertNote(note)
    }
}