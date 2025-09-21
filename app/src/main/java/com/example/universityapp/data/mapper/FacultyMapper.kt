package com.example.universityapp.data.mapper

import com.example.universityapp.data.local.FacultyEntity
import com.example.universityapp.domain.model.Faculty

/**
 * Mapper funksiyalari — Entity va Domain model o‘rtasida konvertatsiya qilish uchun ishlatiladi.
 *
 * Data Layer (Room Entity) ↔ Domain Layer (Business logic)
 *
 * Shu orqali biz app ichida business logikani Room-ga bog‘lamaymiz.
 */

/**
 * FacultyEntity → Faculty (Domain Model)
 * Room Entity obyektini domen modeliga aylantiradi.
 */
fun FacultyEntity.toDomain(): Faculty {
    return Faculty(id = id, name = name)
}

/**
 * Faculty (Domain Model) → FacultyEntity
 * Domen model obyektini Room Entity formatiga aylantiradi.
 */
fun Faculty.toEntity(): FacultyEntity {
    return FacultyEntity(id = id, name = name)
}