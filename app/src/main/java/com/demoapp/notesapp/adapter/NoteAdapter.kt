package com.demoapp.notesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.demoapp.notesapp.model.Note
import com.demoapp.notesapp.databinding.NoteLayoutBinding
import com.demoapp.notesapp.fragments.FirstFragment
import com.demoapp.notesapp.fragments.FirstFragmentDirections

class NoteAdapter :RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    class NoteViewHolder(val itemBinding : NoteLayoutBinding):RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object :DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.noteDesc == newItem.noteDesc &&
                    oldItem.noteTitle == newItem.noteTitle
        }
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
           return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NoteLayoutBinding.inflate(inflater, parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]
        holder.itemBinding.noteTitle.text = currentNote.noteTitle
        holder.itemBinding.noteDesc.text= currentNote.noteDesc

        holder.itemView.setOnClickListener{
            val direction = FirstFragmentDirections.actionFirstFragmentToEditNoteFragment(currentNote)
            it.findNavController().navigate(direction)
        }
    }
}