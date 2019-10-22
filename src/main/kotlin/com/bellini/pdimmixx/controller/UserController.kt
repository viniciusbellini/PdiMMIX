package com.bellini.pdimmixx.controller

import com.bellini.pdimmixx.models.User
import com.bellini.pdimmixx.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("users")
class UserController {

    @Autowired
    private lateinit var  userService: UserService

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