package com.bellini.pdimmixx.dto

import com.bellini.pdimmixx.model.User
import javax.validation.constraints.NotBlank

class UserDTO {
    @NotBlank(message = "Preenchimento obrigatorio")
    val fullName: String = ""
    val email: String = ""
    val password: String = ""

    fun toUser () : User {
        return User(fullName = fullName, email = email, password = password)
    }
}