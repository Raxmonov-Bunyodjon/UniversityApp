package com.example.universityapp.data.repository

import com.example.universityapp.data.local.StudentDao
import com.example.universityapp.data.mapper.toDomain
import com.example.universityapp.data.mapper.toEntity
import com.example.universityapp.domain.model.Student
import com.example.universityapp.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * StudentRepositoryImpl — StudentRepository interfeysining Room (DAO) orqali amalga oshirilgan implementatsiyasi.
 * Domain qatlamidan DAO’ni bevosita chaqirmaslik uchun repository ishlatiladi.
 */
class StudentRepositoryImpl @Inject constructor(
    private val studentDao: StudentDao
) : StudentRepository {

    /**
     * Barcha talabalarni olish.
     * DAO’dan Flow<List<StudentEntity>> olinadi va domain modelga map qilinadi.
     * Flow qaytarilgani sababli UI real vaqt rejimida yangilanadi.
     */
    override fun getAllStudents(): Flow<List<Student>> =
        studentDao.getAllStudents().map { list ->
            list.map { it.toDomain() } // Entity → Domain
        }

    /**
     * Fakultet bo‘yicha talabalarni olish.
     * DAO getStudentsByFaculty(facultyId) dan Flow olinadi va domain modelga map qilinadi.
     */
    override fun getStudentsByFaculty(facultyId: Long): Flow<List<Student>> =
        studentDao.getStudentsByFaculty(facultyId).map { list ->
            list.map { it.toDomain() } // Entity → Domain
        }

    /**
     * Yangi talaba qo‘shish.
     * Domain model → Entity ga map qilinadi va DAO insert metodi chaqiriladi.
     */
    override suspend fun insertStudent(student: Student) {
        studentDao.insertStudent(student.toEntity())
    }

    /**
     * Talabani yangilash.
     * Domain model → Entity ga map qilinadi va DAO update metodi chaqiriladi.
     */
    override suspend fun updateStudent(student: Student) {
        studentDao.updateStudent(student.toEntity())
    }

    /**
     * Talabani o‘chirish.
     * studentId orqali o‘chirish amalga oshiriladi.
     */
    override suspend fun deleteStudent(studentId: Long) {
        studentDao.deleteStudent(studentId)
    }
}
