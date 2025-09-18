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

@Singleton
class FacultyRepositoryImpl @Inject constructor(
    private val facultyDao: FacultyDao
) : FacultyRepository {

    override fun getFaculties(): Flow<List<Faculty>> {
        return facultyDao.getAllFaculties().map { it.map { Faculty(it.id, it.name) } }
    }

    override suspend fun getFacultyById(id: Long): Faculty? {
        val entities = facultyDao.getAllFaculties().first()
        return entities.find { it.id == id }?.let {  Faculty(it.id, it.name) }
    }

    override suspend fun insertFaculty(faculty: Faculty) {
        // id 0 bo‘lsa, Room avtomatik increment qiladi
        facultyDao.insertFaculty(FacultyEntity(id = 0, name = faculty.name))
    }

   override suspend fun deleteFaculty(id: Long ) {
    // id bo‘yicha o‘chirish
        facultyDao.deleteFaculty(id)
    }
}