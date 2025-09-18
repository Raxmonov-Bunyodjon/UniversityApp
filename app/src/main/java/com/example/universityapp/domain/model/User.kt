package com.example.universityapp.domain.model

data class User(
    val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val username: String,
    val password: String
)
