package com.example.universityapp.domain.repository

import com.example.universityapp.domain.model.Faculty
import kotlinx.coroutines.flow.Flow

/**
 * FacultyRepository — domen qatlamidagi repository interface.
 *
 * Maqsad:
 * - Fakultetlar bilan ishlash uchun abstraktsiya yaratish.
 * - UI yoki biznes logikasi qatlamini ma’lumotlar bazasi yoki
 *   boshqa data source-lardan ajratib turish.
 *
 * Ushbu interface orqali biz:
 * - ma’lumotlarni olish
 * - qo‘shish
 * - o‘chirish
 * - yangilash
 * kabi operatsiyalarni amalga oshiramiz.
 */
interface FacultyRepository {

    /**
     * Barcha fakultetlarni olish.
     * Flow<List<Faculty>> qaytaradi — ya’ni ma’lumot oqimi.
     * Jadvalda o‘zgarish bo‘lsa, UI avtomatik yangilanadi.
     */
    fun getAllFaculties(): Flow<List<Faculty>>

    /**
     * Id bo‘yicha bitta fakultetni olish.
     * Agar topilmasa, null qaytaradi.
     * suspend — bu funksiya korutina ichida ishlatilishini bildiradi.
     */
    suspend fun getFacultyById(id: Long): Faculty?

    /**
     * Yangi fakultet qo‘shish.
     * suspend — bu funksiya korutina ichida ishlashini bildiradi.
     * @param faculty — qo‘shiladigan Faculty domen modeli
     */
    suspend fun insertFaculty(faculty: Faculty)

    /**
     * Id bo‘yicha fakultetni o‘chirish.
     * @param id — o‘chiriladigan fakultet identifikatori
     */
    suspend fun deleteFaculty(id: Long)

    /**
     * Mavjud fakultet ma’lumotlarini yangilash.
     * @param faculty — yangilanadigan Faculty domen modeli (id bo‘yicha)
     */
    suspend fun updateFaculty(faculty: Faculty)
}