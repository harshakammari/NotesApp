package com.demoapp.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.demoapp.notesapp.database.NoteDatabase
import com.demoapp.notesapp.repository.NoteRepository
import com.demoapp.notesapp.viewmodel.NoteViewModel
import com.demoapp.notesapp.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

     lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
    }
    private fun setupViewModel(){
        val noteRepository = NoteRepository(NoteDatabase(this))
        val viewModelProviderFactory = NoteViewModelFactory(application,noteRepository)
        noteViewModel = ViewModelProvider(this,viewModelProviderFactory)[NoteViewModel::class.java]
    }
}