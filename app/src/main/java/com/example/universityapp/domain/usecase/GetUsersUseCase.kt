package com.example.universityapp.domain.usecase

import com.example.universityapp.domain.model.User
import com.example.universityapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase(
    private val repo: UserRepository
) {
    operator fun invoke(): Flow<List<User>> {
        return repo.getUsers()
    }
}