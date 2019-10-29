package com.bellini.pdimmixx.service

import com.bellini.pdimmixx.model.User
import com.bellini.pdimmixx.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepository: UserRepository) {

    fun findAll(): List<User> {
        return userRepository.findAll().toList()
    }

    fun deleteById(id: Long) {
        return userRepository.deleteById(id)
    }

    fun existsById(id: Long): Boolean {
        return userRepository.existsById(id)
    }

    fun save(user: User): User {
        return userRepository.save(user)
    }

    fun alter(id: Long, user: User): User {
        var safeUser = user.copy(id = id)
        return userRepository.save(safeUser)
    }
}