package com.example.universityapp.domain.repository

import com.example.universityapp.domain.model.Student
import kotlinx.coroutines.flow.Flow

/**
 * StudentRepository — domen qatlamidagi repository interface.
 *
 * Maqsad:
 * - Talabalar bilan ishlash uchun abstraktsiya yaratish.
 * - UI yoki biznes logikasi qatlamini ma’lumotlar bazasi yoki
 *   boshqa data source-lardan ajratib turish.
 *
 * Ushbu interface orqali biz:
 * - barcha talabalarni olish
 * - fakultet bo‘yicha filtrlangan talabalarni olish
 * - qo‘shish, o‘chirish va yangilash
 * kabi operatsiyalarni amalga oshiramiz.
 */
interface StudentRepository {

    /**
     * Barcha talabalarni olish.
     * Flow<List<Student>> qaytaradi — ma’lumot oqimi sifatida.
     * Jadvalda o‘zgarish bo‘lsa, UI avtomatik yangilanadi.
     */
    fun getAllStudents(): Flow<List<Student>>

    /**
     * Fakultet bo‘yicha talabalarni olish.
     * @param facultyId — fakultet identifikatori
     * Flow<List<Student>> qaytaradi
     */
    fun getStudentsByFaculty(facultyId: Long): Flow<List<Student>>

    /**
     * Yangi talabani qo‘shish.
     * @param student — qo‘shiladigan Student domen modeli
     */
    suspend fun insertStudent(student: Student)

    /**
     * Talaba ma’lumotlarini yangilash.
     * @param student — yangilanadigan Student domen modeli
     */
    suspend fun updateStudent(student: Student)

    /**
     * Talabani o‘chirish.
     * @param student — o‘chiriladigan talabaning id-si (Long)
     */
    suspend fun deleteStudent(student: Long)
}
