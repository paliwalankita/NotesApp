package com.example.notesapp.repository

import androidx.lifecycle.LiveData
import com.example.notesapp.dao.NotesDao
import com.example.notesapp.model.Notes

class NotesRepository(val notesDao: NotesDao) {

     fun getAllNotes(): LiveData<List<Notes>>{
        return notesDao.getNotes()
    }

    fun insertNotes(notes: Notes){
        notesDao.insertNote(notes)
    }

    fun delete(id : Int){
        notesDao.deleteNote(id)
    }

    fun updateNotes(notes: Notes){
        notesDao.updateNote(notes)
    }
}