package com.bellini.pdimmixx.repository

import com.bellini.pdimmixx.models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByEmail(email: String?): User?
}
