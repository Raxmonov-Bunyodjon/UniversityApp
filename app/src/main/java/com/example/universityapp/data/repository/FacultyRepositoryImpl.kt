package com.example.universityapp.data.repository

import com.example.universityapp.domain.model.Faculty
import com.example.universityapp.domain.repository.FacultyRepository
import javax.inject.Inject

class FacultyRepositoryImpl @Inject constructor(
    // agar Room database yoki API ishlatsang, shu yerga DAO yoki service kiradi
) : FacultyRepository {

    private val fakeData = listOf(
        Faculty(1, "Informatika"),
        Faculty(2, "Matematika"),
        Faculty(3, "Fizika")
    )

    override suspend fun getFaculties(): List<Faculty> {
        return fakeData
    }

    override suspend fun getFacultyById(id: Int): Faculty? {
        return fakeData.find { it.id == id }
    }

    override suspend fun insertFaculty(faculty: Faculty) {
        // TODO: database ga qo‘shish
    }

    override suspend fun deleteFaculty(faculty: Faculty) {
        // TODO: database dan o‘chirish
    }
}
