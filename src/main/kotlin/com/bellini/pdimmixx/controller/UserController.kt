package com.bellini.pdimmixx.controller

import com.bellini.pdimmixx.dto.UserDTO
import com.bellini.pdimmixx.model.User
import com.bellini.pdimmixx.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import kotlin.streams.toList

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("users")
class UserController(private val userService: UserService, private val bCryptPasswordEncoder: BCryptPasswordEncoder) {

    @GetMapping
    fun list(): ResponseEntity<List<UserDTO>> {
        val listUsers = userService.findAll()
        val listUsersDTO = listUsers.stream().map { user -> UserDTO(user) }.toList()
        return ResponseEntity.ok(listUsersDTO)
    }

    @PutMapping("{id}")
    fun alter(@PathVariable id: Long, @RequestBody user: User): ResponseEntity<UserDTO> {
        if (userService.existsById(id)) {
            val alteredUser = userService.alter(id, user)
            return ResponseEntity.ok(UserDTO(alteredUser))
        }
        return ResponseEntity.notFound().build()
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        if (userService.existsById(id)) {
            userService.deleteById(id)
            return ResponseEntity.ok().build()
        }
        return ResponseEntity.notFound().build()
    }
}