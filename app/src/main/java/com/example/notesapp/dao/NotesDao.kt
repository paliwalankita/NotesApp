package com.example.notesapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notesapp.model.Notes

@Dao
interface NotesDao {
    @Query("Select * from notes_table")
    fun getNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(notes: Notes)

    @Query("Delete from notes_table where id=:id")
    fun deleteNote(id: Int)

    @Update
    fun updateNote(notes: Notes)

}