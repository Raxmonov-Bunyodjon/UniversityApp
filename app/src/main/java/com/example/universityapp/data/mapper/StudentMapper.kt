package com.example.universityapp.data.mapper

import com.example.universityapp.data.local.StudentEntity
import com.example.universityapp.data.local.StudentWithFaculty
import com.example.universityapp.domain.model.Student
import com.example.universityapp.ui.main.student.SimpleStudent

/**
 * Mapper funksiyalari — Student bilan bog‘liq Entity, Domain va UI model o‘rtasida konvertatsiya qiladi.
 * Shu orqali biz qatlamlar orasidagi bog‘lanishni kamaytiramiz.
 */

/**
 * StudentEntity -> domain.Student
 * DB Entity dan domain modelga aylantiradi.
 * facultyName null, chunki StudentEntity da yo‘q.
 */
fun StudentEntity.toDomain(): Student = Student(
    id = id,
    firstName = firstName,
    lastName = lastName,
    facultyId = facultyId,
    facultyName = null,   // Entity da yo‘q, JOIN query orqali olinadi
    direction = direction,
    avatar = avatar
)

/**
 * domain.Student -> StudentEntity
 * Domain modelni DB uchun Entity formatiga aylantiradi.
 */
fun Student.toEntity(): StudentEntity = StudentEntity(
    id = id,
    firstName = firstName,
    lastName = lastName,
    facultyId = facultyId,
    direction = direction,
    avatar = avatar
)

/**
 * StudentWithFaculty (JOIN natijasi) -> domain.Student
 * Domain modelga aylantiradi va facultyName ni ham saqlaydi.
 */
fun StudentWithFaculty.toDomain(): Student = Student(
    id = id,
    firstName = firstName,
    lastName = lastName,
    facultyId = facultyId,
    facultyName = facultyName,
    direction = direction,
    avatar = avatar
)

/**
 * StudentWithFaculty -> SimpleStudent (UI uchun yengil DTO)
 * UI da faqat kerakli maydonlarni ko‘rsatish uchun ishlatiladi.
 */
fun StudentWithFaculty.toSimpleStudent(): SimpleStudent = SimpleStudent(
    id = id,
    firstName = firstName,
    lastName = lastName,
    facultyName = facultyName,
    direction = direction
)
