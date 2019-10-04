package com.bellini.pdimmixx.repository

import com.bellini.pdimmixx.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long>
