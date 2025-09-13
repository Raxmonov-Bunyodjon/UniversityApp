package com.example.universityapp.domain.repository

import com.example.universityapp.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
    suspend fun getUserByUsername(username: String): User?
    suspend fun insertUser(user: User)
    suspend fun deleteUser(user: User)
}
