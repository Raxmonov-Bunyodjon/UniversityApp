package com.example.universityapp.data.repository

import com.example.universityapp.data.local.FacultyDao
import com.example.universityapp.data.local.FacultyEntity
import com.example.universityapp.data.local.StudentDao
import com.example.universityapp.data.local.StudentEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UniversityRepository @Inject constructor(
    private val facultyDao: FacultyDao,
    private val studentDao: StudentDao
) {
    // Fakultetlar
    suspend fun addFaculty(faculty: FacultyEntity) = facultyDao.insertFaculty(faculty)
    fun getFaculties(): Flow<List<FacultyEntity>> = facultyDao.getAllFaculties()
    suspend fun deleteFaculty(id: Int) = facultyDao.deleteFaculty(id)

    // Talabalar
    suspend fun addStudent(student: StudentEntity) = studentDao.insertStudent(student)
    fun getStudents(): Flow<List<StudentEntity>> = studentDao.getAllStudents()
    fun searchStudents(query: String): Flow<List<StudentEntity>> = studentDao.searchStudents(query)
    suspend fun deleteStudent(id: Int) = studentDao.deleteStudent(id)
}
