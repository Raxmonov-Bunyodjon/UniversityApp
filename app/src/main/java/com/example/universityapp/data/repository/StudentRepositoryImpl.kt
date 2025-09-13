package com.example.universityapp.data.repository

import com.example.universityapp.domain.model.Student
import com.example.universityapp.domain.repository.StudentRepository
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    // agar Room yoki API bo‘lsa keyin shu yerga ulaysan (StudentDao yoki api service)
) : StudentRepository {

    // Hozircha fake ma’lumot
    private val fakeStudents = mutableListOf(
        Student(1, "Ali", "Valiyev", 1),
        Student(2, "Dilshod", "Karimov",2),
        Student(3, "Malika", "Olimova", 3)
    )

    override suspend fun getStudents(): List<Student> {
        return fakeStudents
    }

    override suspend fun getStudentsByFaculty(facultyId: Int): List<Student> {
        TODO("Not yet implemented")
    }

    override suspend fun insertStudent(student: Student) {
        fakeStudents.add(student)
    }

    override suspend fun deleteStudent(student: Student) {
        fakeStudents.remove(student)
    }
}
