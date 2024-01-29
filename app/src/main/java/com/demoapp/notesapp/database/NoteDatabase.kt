package com.demoapp.notesapp.database

import android.content.Context
import androidx.core.graphics.createBitmap
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demoapp.notesapp.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun getNoteDao(): NoteDao
    companion object{
        @Volatile
        private var instanse: NoteDatabase? = null
        private val LOCK = Any()

        operator  fun invoke(context : Context)= instanse?:
        synchronized(LOCK){
            instanse ?:
            createDatabase(context).also{
                instanse=it
            }
        }
        private fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "note_db"

            ).build()
    }
}