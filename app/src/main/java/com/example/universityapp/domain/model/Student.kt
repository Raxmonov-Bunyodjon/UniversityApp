package com.example.universityapp.domain.model

/**
 * Student — domen qatlamidagi model.
 * Bu model talabalar ma'lumotlarini saqlash va biznes logikada ishlatish uchun mo'ljallangan.
 *
 * Domen modeli va Entity farqi:
 * - Entity: ma'lumotlar bazasi (Room) uchun
 * - Domain model: UI va biznes logikasi qatlamida ishlatiladi
 */
data class Student(
    val id: Long,           // Talabaning unikal identifikatori (Room DB bilan mos)
    val firstName: String,  // Talabaning ismi
    val lastName: String,   // Talabaning familiyasi
    val facultyId: Long,    // Talabaning fakultet ID si (Room DB bilan mos)
    val direction: String,  // Talabaning yo'nalishi yoki mutaxassisligi
    val avatar: String?,    // Talabaning avatar rasmi URL yoki fayl yo'li (nullable)
    val facultyName: String? // Fakultet nomi (JOIN natijasi, nullable)
)