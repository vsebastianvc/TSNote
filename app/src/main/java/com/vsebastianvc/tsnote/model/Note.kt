package com.vsebastianvc.tsnote.model

import java.time.Instant
import java.util.*

data class Note(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    val entryDate: Date = Date.from(Instant.now())
)
