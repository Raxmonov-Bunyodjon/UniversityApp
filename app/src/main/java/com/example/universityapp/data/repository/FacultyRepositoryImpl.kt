package com.example.universityapp.data.repository

import com.example.universityapp.data.local.FacultyDao
import com.example.universityapp.data.local.FacultyEntity
import com.example.universityapp.domain.model.Faculty
import com.example.universityapp.domain.repository.FacultyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * FacultyRepositoryImpl — FacultyRepository interfeysining Room (DAO) orqali amalga oshirilgan implementatsiyasi.
 * Domain qatlamidan DAO’ni bevosita chaqirmaslik uchun bu repository ishlatiladi.
 */
@Singleton
class FacultyRepositoryImpl @Inject constructor(
    private val facultyDao: FacultyDao
) : FacultyRepository {

    /**
     * Barcha fakultetlarni olish.
     * DAO’dan Flow<List<FacultyEntity>> olinadi va domain modelga map qilinadi.
     * Flow qaytarilgani sababli UI real vaqt rejimida yangilanadi.
     */
    override fun getAllFaculties(): Flow<List<Faculty>> {
        return facultyDao.getAllFaculties().map { entities ->
            entities.map { Faculty(it.id, it.name) }
        }
    }

    /**
     * Id bo‘yicha fakultetni olish.
     * DAO’dan Flow.first() orqali bir martalik qiymat olinadi.
     */
    override suspend fun getFacultyById(id: Long): Faculty? {
        val entities = facultyDao.getAllFaculties().first() // Flow ni collect qilmasdan qiymat olish
        return entities.find { it.id == id }?.let { Faculty(it.id, it.name) }
    }

    /**
     * Yangi fakultet qo‘shish.
     * id = 0 bo‘lsa, Room avtomatik ravishda id ni increment qiladi.
     */
    override suspend fun insertFaculty(faculty: Faculty) {
        facultyDao.insertFaculty(FacultyEntity(id = 0, name = faculty.name))
    }

    /**
     * Fakultetni id bo‘yicha o‘chirish.
     */
    override suspend fun deleteFaculty(id: Long) {
        facultyDao.deleteFaculty(id)
    }

    /**
     * Fakultetni yangilash.
     * Domain model → Entity ga map qilinadi va DAO update metodi chaqiriladi.
     */
    override suspend fun updateFaculty(faculty: Faculty) {
        facultyDao.updateFaculty(FacultyEntity(id = faculty.id, name = faculty.name))
    }
}
