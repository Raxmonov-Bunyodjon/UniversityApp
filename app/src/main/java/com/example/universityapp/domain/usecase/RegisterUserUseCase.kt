package com.example.universityapp.domain.usecase

import com.example.universityapp.domain.model.User
import com.example.universityapp.domain.repository.UserRepository

/**
 * RegisterUserUseCase — foydalanuvchini ro‘yxatdan o‘tkazish uchun domen use case.
 *
 * Maqsad:
 * - UI/ViewModel dan foydalanuvchini ro‘yxatdan o‘tkazish logikasini ajratadi.
 * - Repository orqali foydalanuvchi ma’lumotini bazaga qo‘shadi.
 */
class RegisterUserUseCase(private val repo: UserRepository) {

    /**
     * Operator fun invoke:
     * - Foydalanuvchini ro‘yxatdan o‘tkazadi (insert operatsiyasi).
     *
     * @param user — ro‘yxatdan o‘tadigan foydalanuvchi obyekti
     */
    suspend operator fun invoke(user: User) {
        repo.insertUser(user)  // Repository orqali foydalanuvchi qo‘shish
    }
}