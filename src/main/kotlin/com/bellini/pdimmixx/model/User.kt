package com.bellini.pdimmixx.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class User(
        @Id
        @GeneratedValue
        @JsonProperty("id", access = JsonProperty.Access.READ_ONLY)
        val id: Long = 0L,
        val name: String = "",
        val email: String = "",
        val password: String = "",
        val isActive: Boolean = true
)