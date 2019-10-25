package com.bellini.pdimmixx.controller

import com.bellini.pdimmixx.dto.UserDTO
import com.bellini.pdimmixx.model.User
import com.bellini.pdimmixx.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/users")
class UserController(
        private val userService: UserService) {

    @GetMapping
    fun list(): ResponseEntity<List<User>> {
        val allUsers = userService.all()
        return ResponseEntity.ok(allUsers)
    }

    @GetMapping("{id}")
    fun listById(@PathVariable id: Long): ResponseEntity<Optional<User>> {
        val user = userService.findById(id)
        return ResponseEntity.ok(user)
    }

    @PostMapping
    fun add(@Valid @RequestBody user: User): ResponseEntity<User> {
        val userSaved = userService.save(user)
        return ResponseEntity.ok(userSaved)
    }

    @PutMapping("{id}")
    fun alter(@PathVariable id: Long, @RequestBody user: User): ResponseEntity<User> {
        if (userService.existsById(id)) {
            val alteredUser = userService.alter(id, user)
            return ResponseEntity.ok(alteredUser)
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