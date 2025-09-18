package com.example.universityapp.data.local

import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface FacultyDao {
    @Insert
    suspend fun insertFaculty(faculty: FacultyEntity)

    @Query("SELECT * FROM faculties ORDER BY id DESC")
    fun getAllFaculties(): Flow<List<FacultyEntity>>


    @Query("DELETE FROM faculties WHERE id = :facultyId")
    suspend fun deleteFaculty(facultyId: Long)


    @Query("SELECT * FROM faculties WHERE id = :id LIMIT 1")
    suspend fun getFacultyById(id: Long): FacultyEntity?

}
