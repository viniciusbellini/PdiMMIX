package com.bellini.pdimmixx.service

import com.bellini.pdimmixx.model.Note
import com.bellini.pdimmixx.repository.NoteRepository
import org.springframework.stereotype.Service

@Service
class NoteService(
        private val noteRepository: NoteRepository) {

    fun all(): List<Note> {
        return noteRepository.findAll().toList()
    }

    fun deleteById(id: Long) {
        return noteRepository.deleteById(id)
    }

    fun existsById(id: Long): Boolean {
        return noteRepository.existsById(id)
    }

    fun save(note: Note): Note {
        return noteRepository.save(note)
    }

    fun alter(id: Long, note: Note): Note {
        var safeNote = note.copy(id = id)
        return noteRepository.save(safeNote)
    }
}