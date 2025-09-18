package com.example.universityapp.data.repository

import com.example.universityapp.data.local.StudentDao
import com.example.universityapp.data.local.StudentEntity
import com.example.universityapp.domain.model.Student
import com.example.universityapp.domain.repository.StudentRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentRepositoryImpl @Inject constructor(
    private val studentDao: StudentDao
) : StudentRepository {

    override suspend fun getStudents(): List<Student> {
        // Dao-dan barcha studentlarni olib, domain modelga map qilish
        return studentDao.getAllStudentsList().map {
            Student(
                id = it.id,
                firstName = it.firstName,
                lastName = it.lastName,
                facultyId = it.facultyId,
                direction = it.direction,
                avatar = it.avatar
            )
        }
    }

    override suspend fun getStudentsByFaculty(facultyId: Int): List<Student> {
        return studentDao.getStudentsByFaculty(facultyId).map {
            Student(
                id = it.id,
                firstName = it.firstName,
                lastName = it.lastName,
                facultyId = it.facultyId,
                direction = it.direction,
                avatar = it.avatar
            )
        }
    }

    override suspend fun insertStudent(student: Student) {
        studentDao.insertStudent(
            StudentEntity(
                id = student.id,
                firstName = student.firstName,
                lastName = student.lastName,
                facultyId = student.facultyId,
                direction = student.direction,
                avatar = student.avatar
            )
        )
    }

    override suspend fun deleteStudent(student: Student) {
        studentDao.deleteStudent(student.id)
    }
}
