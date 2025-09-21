package com.example.universityapp.domain.usecase

import com.example.universityapp.domain.model.User
import com.example.universityapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

/**
 * GetUsersUseCase — domen qatlamidagi use case.
 *
 * Maqsad:
 * - Foydalanuvchilar ro‘yxatini olish operatsiyasini UI/ViewModel dan ajratib
 *   biznes logikasi sifatida encapsulate qiladi.
 * - Repository bilan to‘g‘ridan-to‘g‘ri ishlash o‘rniga,
 *   use case orqali ma’lumotlarni olishni ta’minlaydi.
 */
class GetUsersUseCase(
    private val repo: UserRepository // Repository orqali ma’lumot manbai
) {

    /**
     * Operator fun invoke:
     * - Bu funksiya `GetUsersUseCase()` tarzida chaqirilganda ishlaydi.
     * - Repository orqali barcha foydalanuvchilarni Flow<List<User>> sifatida qaytaradi.
     *
     * @return Flow<List<User>> — foydalanuvchilar oqimi
     */
    operator fun invoke(): Flow<List<User>> {
        return repo.getUsers()
    }
}
