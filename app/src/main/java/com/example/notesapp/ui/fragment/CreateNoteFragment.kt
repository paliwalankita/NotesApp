package com.example.notesapp.ui.fragment

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentCreateNoteBinding
import com.example.notesapp.model.Notes
import com.example.notesapp.viewModel.NotesViewModel
import java.util.Date

class CreateNoteFragment : Fragment() {
    lateinit var binding: FragmentCreateNoteBinding
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)
        
        binding.saveFloatingButton.setOnClickListener { 
            createNote(it)
        }
        
        return binding.root
    }


    private fun createNote(it: View?) {

        val title = binding.titleEditText.text.toString()
        val note = binding.notesEditText.text.toString()
        val date = Date()
        val noteDate: CharSequence = DateFormat.format("MMM, d, yyyy", date.time)
        val data = Notes(
            null,
            title = title,
            note = note,
            date = noteDate.toString())
        viewModel.insertNotes(data)

        Toast.makeText(requireContext(), "Note inserted in list", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragment_to_homeFragment)
    }
}