package com.example.universityapp.domain.repository

import com.example.universityapp.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * UserRepository — domen qatlamidagi foydalanuvchi repository interface.
 *
 * Maqsad:
 * - Foydalanuvchi ma’lumotlari bilan ishlash uchun abstraktsiya yaratish.
 * - UI yoki biznes logikasi qatlamini ma’lumotlar bazasi yoki
 *   SharedPreferences kabi data source-lardan ajratib turish.
 */
interface UserRepository {

    /**
     * Saqlangan username oqimi (Flow).
     * Login / session monitoring uchun ishlatiladi.
     */
    val userUsernameFlow: Flow<String?>

    /**
     * Barcha foydalanuvchilar ro‘yxatini olish.
     * Flow<List<User>> qaytaradi — ma’lumot oqimi sifatida.
     */
    fun getUsers(): Flow<List<User>>

    /**
     * Login uchun: username va password bo‘yicha foydalanuvchini olish.
     * Agar topilmasa, null qaytaradi.
     * @param username — foydalanuvchi login
     * @param password — foydalanuvchi parol
     */
    fun getUserByUsernameAndPassword(
        username: String,
        password: String
    ): Flow<User?>

    /**
     * Foydalanuvchi avatarini yangilash.
     * @param username — foydalanuvchi identifikatori sifatida username ishlatiladi
     * @param avatar — yangi avatar URL yoki fayl nomi
     */
    suspend fun updateUserAvatar(username: String, avatar: String)

    /**
     * Signup uchun: username bo‘yicha foydalanuvchini olish.
     * Agar topilmasa, null qaytaradi.
     */
    suspend fun getUserByUsername(username: String): User?

    /**
     * Yangi foydalanuvchini qo‘shish.
     * @param user — qo‘shiladigan User domen modeli
     */
    suspend fun insertUser(user: User)

    /**
     * Foydalanuvchini o‘chirish.
     * @param user — o‘chiriladigan User domen modeli
     */
    suspend fun deleteUser(user: User)

    /**
     * Foydalanuvchi tizimga kirganini saqlash.
     * @param username — login qilgan username
     */
    suspend fun signInUser(username: String)

    /**
     * Logout: saqlangan session va preference-larni tozalash.
     */
    suspend fun logout()
}
