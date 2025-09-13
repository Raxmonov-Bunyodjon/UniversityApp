package com.example.universityapp.domain.repository

import com.example.universityapp.domain.model.Faculty

interface FacultyRepository {
    suspend fun getFaculties(): List<Faculty>
    suspend fun getFacultyById(id: Int): Faculty?
    suspend fun insertFaculty(faculty: Faculty)
    suspend fun deleteFaculty(faculty: Faculty)
}