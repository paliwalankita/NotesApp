package com.example.notesapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.NotesListBinding
import com.example.notesapp.model.Notes
import com.example.notesapp.ui.fragment.HomeFragmentDirections

class NotesAdapter(val context: Context, val notesList: List<Notes>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    inner class NotesViewHolder(val binding: NotesListBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            NotesListBinding.inflate(
                LayoutInflater.from(
                    parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.titleTextView.text = data.title
        holder.binding.noteTextView.text = data.note
        holder.binding.dateTextView.text = data.date

        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }
}