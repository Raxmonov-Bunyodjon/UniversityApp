package com.example.universityapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * StudentDao — Room uchun DAO interfeysi.
 * Bu interfeys orqali "students" jadvali va fakultet bilan bo‘lgan aloqalar
 * ustida CRUD hamda qidiruv/join amallarini bajarish mumkin.
 */
@Dao
interface StudentDao {

    // === CRUD ===

    /**
     * Yangi talabani bazaga qo‘shadi.
     * Agar shu id bilan student mavjud bo‘lsa, yangisini yozib ustidan almashtiradi.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: StudentEntity)

    /**
     * Mavjud student ma’lumotlarini yangilaydi.
     * StudentEntity da id bo‘lishi shart — shu id bo‘yicha yangilanadi.
     */
    @Update
    suspend fun updateStudent(student: StudentEntity)

    /**
     * Talabani id bo‘yicha olish.
     * Agar topilmasa, null qaytadi.
     */
    @Query("SELECT * FROM students WHERE id = :id LIMIT 1")
    suspend fun getStudentById(id: Long): StudentEntity?

    /**
     * Studentni id bo‘yicha o‘chirish.
     */
    @Query("DELETE FROM students WHERE id = :studentId")
    suspend fun deleteStudent(studentId: Long)

    /**
     * Barcha studentlarni olish.
     * Flow<List<StudentEntity>> qaytaradi — jadvalda o‘zgarish bo‘lsa, natija avtomatik yangilanadi.
     */
    @Query("SELECT * FROM students")
    fun getAllStudents(): Flow<List<StudentEntity>>

    /**
     * Muayyan fakultetga tegishli studentlarni olish.
     */
    @Query("SELECT * FROM students WHERE facultyId = :facultyId")
    fun getStudentsByFaculty(facultyId: Long): Flow<List<StudentEntity>>

    // === Qidiruv (Search) ===

    /**
     * Studentlarni ism yoki familiya bo‘yicha qidirish.
     * Natijada fakultet nomi bilan birga StudentWithFaculty obyektlari qaytadi.
     */
    @Query("""
    SELECT s.id, s.firstName, s.lastName, s.facultyId,
           f.name AS facultyName, s.direction, s.avatar
    FROM students s
    INNER JOIN faculties f ON s.facultyId = f.id
    WHERE s.firstName LIKE :query || '%'
       OR s.lastName LIKE :query || '%'
""")
    fun searchStudents(query: String): Flow<List<StudentWithFaculty>>

    // === JOIN query: StudentWithFaculty ===

    /**
     * Barcha studentlarni ularning fakulteti bilan birga olish.
     */
    @Query("""
        SELECT s.id, s.firstName, s.lastName, s.facultyId,
               f.name AS facultyName, s.direction, s.avatar
        FROM students s
        INNER JOIN faculties f ON s.facultyId = f.id
    """)
    fun getStudentsWithFaculty(): Flow<List<StudentWithFaculty>>

    /**
     * Studentni id bo‘yicha, fakultet ma’lumoti bilan birga olish.
     * Agar topilmasa, null qaytadi.
     */
    @Query("""
        SELECT s.id, s.firstName, s.lastName, s.facultyId,
               f.name AS facultyName, s.direction, s.avatar
        FROM students s
        INNER JOIN faculties f ON s.facultyId = f.id
        WHERE s.id = :id
    """)
    suspend fun getStudentWithFacultyById(id: Long): StudentWithFaculty?
}
