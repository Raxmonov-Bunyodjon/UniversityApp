package com.example.universityapp.ui.auth

/**
 * AuthState — bu sealed class bo‘lib, autentifikatsiya (login/signup) jarayonining
 * turli holatlarini ifodalaydi.
 *
 * Sealed class yordamida biz bir nechta ma’lum variantlarni belgilaymiz va
 * UI/ViewModel ularga mos javob bera oladi.
 */
sealed class AuthState {

    /**
     * Idle — autentifikatsiya jarayoni boshlanmagan yoki kutish holati.
     * Masalan, foydalanuvchi login ekranda hech qanday amal qilmagan.
     */
    object Idle : AuthState()

    /**
     * Success — autentifikatsiya muvaffaqiyatli tugaganda.
     * @param message — foydalanuvchiga ko‘rsatish uchun xabar (masalan, "Login muvaffaqiyatli")
     */
    data class Success(val message: String) : AuthState()

    /**
     * Error — autentifikatsiya jarayonida xatolik yuz berganda.
     * @param message — xatolik haqida ma’lumot (masalan, "Username yoki password noto‘g‘ri")
     */
    data class Error(val message: String) : AuthState()
}