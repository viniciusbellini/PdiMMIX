package com.bellini.pdimmixx.services

import com.bellini.pdimmixx.models.User
import com.bellini.pdimmixx.models.UserDetailsImpl
import com.bellini.pdimmixx.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    fun all(): List<User> {
        return userRepository.findAll().toList()
    }

    fun deleteById(id: Long) {
        return userRepository.deleteById(id)
    }

    fun existsById(id: Long): Boolean {
        return userRepository.existsById(id)
    }

    fun save(user: User): User {
        user.password = bCryptPasswordEncoder.encode(user.password)
        return userRepository.save(user)
    }

    fun alter(id: Long, user: User): User {
        var safeUser = user.copy(id = id)
        return userRepository.save(safeUser)
    }

    fun myself(): String? {
        return userRepository.findByEmail(getCurrentUserEmail())?.fullName
    }

    private fun getCurrentUserEmail(): String? {
        val user = SecurityContextHolder.getContext().authentication.principal as UserDetailsImpl
        return user.username
    }
}