package com.example.universityapp.domain.usecase

import com.example.universityapp.domain.model.User
import com.example.universityapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.firstOrNull

class LoginUserUseCase(private val repo: UserRepository) {
    suspend operator fun invoke(username: String, password: String): User? {
        // Flow dan birinchi qiymatni olish
        val user = repo.getUserByUsernameAndPassword(username, password).firstOrNull()
        return user
    }
}