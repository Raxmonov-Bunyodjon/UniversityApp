package com.example.universityapp.domain.usecase

import com.example.universityapp.domain.model.User
import com.example.universityapp.domain.repository.UserRepository

class RegisterUserUseCase(private val repo: UserRepository) {
    suspend operator fun invoke(user: User) {
        repo.insertUser(user)
    }
}