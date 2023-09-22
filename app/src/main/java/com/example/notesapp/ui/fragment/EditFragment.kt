package com.example.notesapp.ui.fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentEditBinding
import com.example.notesapp.model.Notes
import com.example.notesapp.viewModel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Date

@Suppress("DEPRECATION")
class EditFragment : Fragment() {
    lateinit var binding: FragmentEditBinding
    val notes by navArgs<EditFragmentArgs>()
    val viewModel: NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)

        binding.updateTitleEditText.setText(notes.noteData.title)
        binding.updateNotesEditText.setText(notes.noteData.note)


        binding.updateFloatingButton.setOnClickListener {
            updateNote(it)
        }


        return binding.root
    }

    private fun updateNote(it: View?) {
        val title = binding.updateTitleEditText.text.toString()
        val note = binding.updateNotesEditText.text.toString()
        val date = Date()
        val noteDate: CharSequence = DateFormat.format("MMM, d, yyyy", date.time)
        val data = Notes(
            notes.noteData.id,
            title = title,
            note = note,
            date = noteDate.toString())
        viewModel.updateNote(data)

        Toast.makeText(requireContext(), "Note updated!!", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            Log.e("@@@@", "onOptionsItemSelected: Delete")
            val bottomSheet: BottomSheetDialog = BottomSheetDialog(requireContext())
            bottomSheet.setContentView(R.layout.delete_note)

            val dialogYesTv = bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val dialogNoTv = bottomSheet.findViewById<TextView>(R.id.dialog_no)

            dialogYesTv?.setOnClickListener { view ->
                viewModel.deleteNote(notes.noteData.id!!)
//                val action = EditFragmentDirections.actionEditFragmentToHomeFragment()
//                Navigation.findNavController(view).navigate(R.id.action_editFragment_to_homeFragment)
                bottomSheet.dismiss()
            }

            dialogNoTv?.setOnClickListener {
                bottomSheet.dismiss()
            }

            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }
}