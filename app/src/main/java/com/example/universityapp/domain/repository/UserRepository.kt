package com.example.universityapp.domain.repository

import com.example.universityapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val userUsernameFlow: Flow<String?>
    fun getUsers(): Flow<List<User>>
    fun getUserByUsernameAndPassword(
        username: String,
        password: String
    ): Flow<User?>   // login uchun

    suspend fun getUserByUsername(username: String): User? // signup uchun
    suspend fun insertUser(user: User)
    suspend fun deleteUser(user: User)

    suspend fun signInUser(username: String)
    suspend fun logout()
}