package com.vsebastianvc.tsnote.utils

import com.vsebastianvc.tsnote.model.Note

class NoteDataSource {
    fun loadNotes(): List<Note>{
        return listOf(
            Note(title = "Android Compose", description = "Working on Android Compose bootcamp today"),
            Note(title = "A good day at TribalScale", description = "We had a great time at today's Watercooler Chat :)"),
            Note(title = "Keep it up...", description = "Sometimes things just happen"),
        )
    }
}