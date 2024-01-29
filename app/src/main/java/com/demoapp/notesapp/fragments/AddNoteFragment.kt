package com.demoapp.notesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import com.demoapp.notesapp.MainActivity
import com.demoapp.notesapp.R
import com.demoapp.notesapp.databinding.FragmentAddNoteBinding
import com.demoapp.notesapp.model.Note
import com.demoapp.notesapp.viewmodel.NoteViewModel


class AddNoteFragment : Fragment(R.layout.fragment_add_note),MenuProvider {


    private var addNoteBinding: FragmentAddNoteBinding? = null
    private val binding get() = addNoteBinding!!

    private  lateinit var notesViewModel : NoteViewModel
    private lateinit var addNoteView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addNoteBinding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_note,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
           R.id.saveMenu -> {
               saveNote(addNoteView)
               true
           }
            else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        addNoteBinding = null
    }
     override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
         menuHost.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.RESUMED )
         notesViewModel= (activity as MainActivity).noteViewModel
         addNoteView = view
    }
    private fun saveNote(View: View){
        val noteTitle = binding.addNoteTitle.text.toString().trim()
        val noteDesc = binding.addNoteDesc.text.toString().trim()

        if(noteTitle.isNotEmpty()){
            val note = Note(0,noteTitle,noteDesc)
            notesViewModel.addNote(note)

            Toast.makeText(addNoteView.context,"Note Saved",Toast.LENGTH_SHORT).show()

        }
        else{
            Toast.makeText(addNoteView.context,"Please Enter Note Title",Toast.LENGTH_SHORT).show()
        }
    }

}