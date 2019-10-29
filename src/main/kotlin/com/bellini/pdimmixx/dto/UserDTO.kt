package com.bellini.pdimmixx.dto

import com.bellini.pdimmixx.model.User

data class UserDTO constructor(val id: Long, val name: String, val email: String){

    constructor(user: User) : this(user.id, user.name, user.email)

}