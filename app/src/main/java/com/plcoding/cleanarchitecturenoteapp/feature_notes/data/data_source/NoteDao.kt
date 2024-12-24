package com.plcoding.cleanarchitecturenoteapp.feature_notes.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("Select * From note")
    fun getNotes() : Flow<List<Note>>

    @Query("Select * from note WHERE id = :id")
    suspend fun getNoteById(id : Int) : Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note : Note)

    @Delete
    suspend fun deleteNote(note: Note)
}