package com.example.universityapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Bu klass — Room kutubxonasidagi asosiy Database klassi.
 * U barcha Entity (jadval) va DAO (ma’lumot olish interfeysi)larni o‘zida birlashtiradi.
 */
@Database(
    entities = [
        UserEntity::class,      // Foydalanuvchilar jadvali
        StudentEntity::class,   // Talabalar jadvali
        FacultyEntity::class    // Fakultetlar jadvali
    ],
    version = 1, // Database versiyasi (agar Entity yoki ustunlarda o‘zgarish bo‘lsa, bu sonni oshirish kerak)
    exportSchema = false // true bo‘lsa, Room sxema faylini tashqi papkaga eksport qiladi. Odatda ishlab chiqishda kerak emas.
)
abstract class AppDatabase : RoomDatabase() {

    /**
     * User jadvali uchun DAO.
     * Bunda foydalanuvchilar bilan ishlash (insert, delete, update, query) metodlari bo‘ladi.
     */
    abstract fun userDao(): UserDao

    /**
     * Student jadvali uchun DAO.
     * Talabalar bilan ishlash operatsiyalarini amalga oshiradi.
     */
    abstract fun studentDao(): StudentDao

    /**
     * Faculty jadvali uchun DAO.
     * Fakultet ma’lumotlarini olish va boshqarish uchun ishlatiladi.
     */
    abstract fun facultyDao(): FacultyDao
}