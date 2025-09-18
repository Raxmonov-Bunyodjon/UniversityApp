package com.example.universityapp.domain.repository

import com.example.universityapp.domain.model.Faculty
import kotlinx.coroutines.flow.Flow

interface FacultyRepository {
        fun getFaculties(): Flow<List<Faculty>>
    suspend fun getFacultyById(id: Long): Faculty?
    suspend fun insertFaculty(faculty: Faculty)
    suspend fun deleteFaculty(id: Long)
}