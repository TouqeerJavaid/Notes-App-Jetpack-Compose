package com.plcoding.cleanarchitecturenoteapp.di

import android.app.Application
import androidx.room.Room
import com.plcoding.cleanarchitecturenoteapp.feature_notes.data.data_source.NoteDatabase
import com.plcoding.cleanarchitecturenoteapp.feature_notes.data.repository.NoteRepositoryImpl
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.repository.NoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.use_case.AddNoteUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.use_case.DeleteUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.use_case.GetNoteById
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.use_case.GetNotesUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(app : Application) : NoteDatabase{
        return Room.databaseBuilder(
             app,
            NoteDatabase::class.java,
            "note_app"
        ).build()
    }

    @Provides
    @Singleton
    fun providesNoteRepository(db : NoteDatabase) : NoteRepository{
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun providesNoteUseCases(repository : NoteRepository) : NoteUseCases{
        return NoteUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNoteDelete = DeleteUseCase(repository),
            addNote = AddNoteUseCase(repository),
            getNoteById = GetNoteById(repository)
        )
    }
}