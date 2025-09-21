package com.example.universityapp.domain.model

/**
 * Faculty — domen qatlamidagi model.
 * Bu model fakultet ma'lumotlarini saqlash uchun ishlatiladi.
 *
 * Domen modeli va Entity farqi:
 * - Entity: ma'lumotlar bazasi uchun (Room DB)
 * - Domain model: biznes logika va UI qatlamlarida ishlatiladi
 */
data class Faculty(
    val id: Long,    // Fakultetning unikal identifikatori (Room DB bilan mos)
    val name: String // Fakultet nomi
)
