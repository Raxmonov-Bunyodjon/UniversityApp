package com.example.universityapp.domain.model

/**
 * User — domen qatlamidagi model.
 * Bu model foydalanuvchi ma'lumotlarini saqlash va biznes logikada ishlatish uchun mo'ljallangan.
 *
 * Domen modeli va Entity farqi:
 * - Entity: ma'lumotlar bazasi (Room) uchun
 * - Domain model: UI va biznes logikasi qatlamida ishlatiladi
 */
data class User(
    val id: Long,           // Foydalanuvchining unikal identifikatori (Room DB bilan mos)
    val firstName: String,  // Foydalanuvchining ismi
    val lastName: String,   // Foydalanuvchining familiyasi
    val username: String,   // Login uchun username
    val password: String,   // Login uchun parol
    val faculty: String? = null,   // Foydalanuvchining fakulteti (nullable)
    val direction: String? = null, // Foydalanuvchining yo'nalishi (nullable)
    val avatar: String? = null     // Foydalanuvchining avatar rasmi (nullable)
)