package com.bellini.pdimmixx.repository

import com.bellini.pdimmixx.model.Note
import org.springframework.data.repository.CrudRepository

interface NoteRepository : CrudRepository<Note, Long>