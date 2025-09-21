package com.example.universityapp.data.local

import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * FacultyDao — Room uchun DAO interfeysi.
 * "faculties" jadvali bilan ishlash uchun CRUD amallarini bajaradi.
 */
@Dao
interface FacultyDao {

    /**
     * Yangi fakultetni bazaga qo‘shadi.
     * suspend — bu funksiya korutina ichida ishlashini bildiradi.
     */
    @Insert
    suspend fun insertFaculty(faculty: FacultyEntity)

    /**
     * Barcha fakultetlarni olish.
     * Flow<List<FacultyEntity>> qaytaradi — ya’ni ma’lumotlar oqim sifatida keladi
     * va jadvalda o‘zgarish bo‘lsa, avtomatik yangilanadi.
     */
    @Query("SELECT * FROM faculties ORDER BY id DESC")
    fun getAllFaculties(): Flow<List<FacultyEntity>>

    /**
     * Fakultetni id bo‘yicha o‘chiradi.
     * @param facultyId — o‘chiriladigan fakultetning id qiymati.
     */
    @Query("DELETE FROM faculties WHERE id = :facultyId")
    suspend fun deleteFaculty(facultyId: Long)

    /**
     * Fakultetni yangilash.
     * Fakultet obyektida id bo‘lishi shart — shu id bo‘yicha yangilanadi.
     */
    @Update
    suspend fun updateFaculty(faculty: FacultyEntity)

    /**
     * Id bo‘yicha bitta fakultetni olish.
     * Agar topilmasa, null qaytadi.
     */
    @Query("SELECT * FROM faculties WHERE id = :id LIMIT 1")
    suspend fun getFacultyById(id: Long): FacultyEntity?
}
