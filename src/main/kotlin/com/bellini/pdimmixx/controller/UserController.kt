package com.bellini.pdimmixx.controller

import com.bellini.pdimmixx.model.User
import com.bellini.pdimmixx.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun list(): ResponseEntity<List<User>> {
        val allUsers = userService.all()
        return ResponseEntity.ok(allUsers)
    }

    @PostMapping
    fun add(@RequestBody user: User): ResponseEntity<User> {
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