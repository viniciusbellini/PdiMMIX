package com.bellini.pdimmixx.controller

import com.bellini.pdimmixx.dto.UserDTO
import com.bellini.pdimmixx.model.User
import com.bellini.pdimmixx.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/signup")
class SignUpController (val userService: UserService, val bCryptPasswordEncoder: BCryptPasswordEncoder){

    @PostMapping
    fun signup(@RequestBody user: User): ResponseEntity<UserDTO> {
        val userSaved = userService.save(user.copy(password = bCryptPasswordEncoder.encode(user.password)))
        return ResponseEntity.ok(UserDTO(userSaved))
    }

}