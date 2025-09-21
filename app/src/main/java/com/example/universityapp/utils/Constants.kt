package com.example.universityapp.utils

/**
 * 🔹 Constants
 *
 * Ilova bo‘ylab ishlatiladigan statik qiymatlar va doimiy o‘zgaruvchilarni
 * saqlash uchun utility klass.
 *
 * Misol uchun: SharedPreferences keylari, static URL lar, request kodlar va hokazo.
 *
 * Bu klass instance yaratmasdan ishlatiladi (companion object orqali).
 */
class Constants {

    companion object {
        // 🔹 SharedPreferences fayl nomi
        const val PREFS_NAME = "university_app_prefs"

        // 🔹 Foydalanuvchi login holatini saqlash uchun key
        const val KEY_USERNAME = "key_username"

        // 🔹 Talabalar uchun default avatar URL
        const val DEFAULT_AVATAR_URL = "https://example.com/default_avatar.png"

        // 🔹 Intent request kodlari
        const val REQUEST_PICK_IMAGE = 1001

        // 🔹 Base API URL (agar REST API ishlatilsa)
        const val BASE_API_URL = "https://api.universityapp.com/"

        /**
         * 🔹 Funksiya: getStudentFullName
         * Talabaning ism va familiyasini birlashtirib, to‘liq nomini qaytaradi.
         *
         * @param firstName Talabaning ismi
         * @param lastName Talabaning familiyasi
         * @return To‘liq nom sifatida string
         */
        fun getStudentFullName(firstName: String, lastName: String): String {
            return "$firstName $lastName"
        }
    }
}
