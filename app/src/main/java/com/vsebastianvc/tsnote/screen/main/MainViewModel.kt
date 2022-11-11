package com.vsebastianvc.tsnote.screen.main

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.vsebastianvc.tsnote.model.Note
import com.vsebastianvc.tsnote.utils.NoteDataSource

class MainViewModel : ViewModel() {

    private var noteList = mutableStateListOf<Note>()

    init {
        noteList.addAll(NoteDataSource().loadNotes())
    }

    fun addNote(note: Note) = noteList.add(note)
    fun removeNote(note: Note) = noteList.remove(note)
    fun getAllNotes(): List<Note> = noteList
}