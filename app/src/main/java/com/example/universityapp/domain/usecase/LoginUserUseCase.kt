package com.example.universityapp.domain.usecase

import com.example.universityapp.domain.model.User
import com.example.universityapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.firstOrNull

/**
 * LoginUserUseCase — foydalanuvchini login qilish uchun domen use case.
 *
 * Maqsad:
 * - UI/ViewModel dan foydalanuvchini tekshirish va olish logikasini ajratib
 *   biznes logikasi qatlamida encapsulate qiladi.
 * - Repository orqali foydalanuvchi ma’lumotini olishni ta’minlaydi.
 */
class LoginUserUseCase(private val repo: UserRepository) {

    /**
     * Operator fun invoke:
     * - Login operatsiyasini bajaradi.
     *
     * @param username — foydalanuvchi nomi
     * @param password — foydalanuvchi paroli
     * @return User? — agar foydalanuvchi topilsa, User obyekti; topilmasa null
     */
    suspend operator fun invoke(username: String, password: String): User? {
        // Repository orqali username va password bo‘yicha Flow olish
        val user = repo.getUserByUsernameAndPassword(username, password)
            .firstOrNull() // Flow dan birinchi qiymatni olish
        return user
    }
}
