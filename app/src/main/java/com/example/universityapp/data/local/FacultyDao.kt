package com.example.universityapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FacultyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFaculty(faculty: FacultyEntity)

    @Query("SELECT * FROM faculties ORDER BY name ASC")
    fun getAllFaculties(): Flow<List<FacultyEntity>>

    @Query("DELETE FROM faculties WHERE id = :id")
    suspend fun deleteFaculty(id: Int)
}
