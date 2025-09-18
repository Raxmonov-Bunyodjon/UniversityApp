package com.example.universityapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: StudentEntity)

    @Query("SELECT * FROM students")
    fun getAllStudents(): Flow<List<StudentEntity>>

    @Query("SELECT * FROM students WHERE lastName LIKE '%' || :query || '%' OR firstName LIKE '%' || :query || '%'")
    fun searchStudents(query: String): Flow<List<StudentEntity>>
    @Query("SELECT * FROM students WHERE facultyId = :facultyId")
    suspend fun getStudentsByFacultyList(facultyId: Int): List<StudentEntity>  // 🔥 snapshot uchun

    @Query("DELETE FROM students WHERE id = :id")
    suspend fun deleteStudent(id: Int)

    @Query("SELECT * FROM students ORDER BY lastName ASC")
    suspend fun getAllStudentsList(): List<StudentEntity>   // 👈 yangi

    @Query("SELECT * FROM students WHERE facultyId = :facultyId ORDER BY lastName ASC")
    suspend fun getStudentsByFaculty(facultyId: Int): List<StudentEntity>  // 👈 yangi
}

