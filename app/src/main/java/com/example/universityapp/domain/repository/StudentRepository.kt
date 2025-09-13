package com.example.universityapp.domain.repository

import com.example.universityapp.domain.model.Student

interface StudentRepository {
    suspend fun getStudents(): List<Student>
    suspend fun getStudentsByFaculty(facultyId: Int): List<Student>
    suspend fun insertStudent(student: Student)
    suspend fun deleteStudent(student: Student)
}