package com.example.notesapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.database.NoteDatabase
import com.example.notesapp.model.Notes
import com.example.notesapp.repository.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {

    val repository : NotesRepository

    init{
        val dao = NoteDatabase.getDatabaseInstance(application).getNoteDao()
        repository = NotesRepository(dao)
    }

    fun insertNotes(notes: Notes) {
        repository.insertNotes(notes)
    }

    fun getNotes(): LiveData<List<Notes>> = repository.getAllNotes()

    val deleteObserver: MutableLiveData<Boolean> by lazy {
        MutableLiveData()
    }
    fun deleteNote(id: Int) {
        repository.delete(id)
        deleteObserver.postValue(true)
    }

    fun updateNote(notes: Notes) {
        repository.updateNotes(notes)
    }

}