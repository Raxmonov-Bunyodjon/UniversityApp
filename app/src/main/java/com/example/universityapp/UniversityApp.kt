package com.example.universityapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * 🔹 UniversityApp
 *
 * Loyihaning Application klassi.
 * Bu klass Android app boshlanishida ishga tushadi va
 * Hilt dependency injection (DI) framework’ini ishga tushirish uchun belgilangan.
 *
 * @HiltAndroidApp annotatsiyasi:
 * - Dagger Hilt uchun kerak
 * - Ilovani DI container sifatida belgilaydi
 * - Barcha @AndroidEntryPoint bilan belgilangan Activity va Fragmentlar
 *   shu DI container orqali kerakli obyektlarni oladi
 */
@HiltAndroidApp
class UniversityApp : Application() {

    /**
     * 🔹 onCreate()
     *
     * Ilova ishga tushganda chaqiriladi.
     * Hilt uchun container shu yerda ishga tushadi.
     * Shu yerda boshqa global init kodlarini ham yozish mumkin
     */
    override fun onCreate() {
        super.onCreate()
        // Masalan: loglar, analytics, crashlytics, yoki boshqa kutubxonalarni init qilish
    }
}