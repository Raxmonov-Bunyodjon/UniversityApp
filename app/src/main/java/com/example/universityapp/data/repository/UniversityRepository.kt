package com.example.universityapp.data.repository

import com.example.universityapp.data.local.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * UniversityRepository — barcha lokal ma’lumotlarni (Faculty, Student, User) boshqarish uchun yagona repository.
 * Bu repository orqali DAO larni bevosita chaqirmasdan, domain yoki ViewModel qatlamlari bilan ishlash mumkin.
 */
@Singleton
class UniversityRepository @Inject constructor(
    private val facultyDao: FacultyDao,
    private val studentDao: StudentDao,
    private val userDao: UserDao
) {

    // ============================
    // Fakultetlar (FacultyEntity)
    // ============================

    /**
     * Yangi fakultet qo‘shish
     */
    suspend fun addFaculty(faculty: FacultyEntity) = facultyDao.insertFaculty(faculty)

    /**
     * Barcha fakultetlarni olish (Flow orqali real vaqt update bilan)
     */
    fun getFaculties(): Flow<List<FacultyEntity>> = facultyDao.getAllFaculties()

    /**
     * Fakultetni id bo‘yicha o‘chirish
     */
    suspend fun deleteFaculty(id: Long) = facultyDao.deleteFaculty(id)

    // ============================
    // Talabalar (StudentEntity bilan)
    // ============================

    /**
     * Yangi talaba qo‘shish
     */
    suspend fun addStudent(student: StudentEntity) = studentDao.insertStudent(student)

    /**
     * Barcha talabalarni olish (Entity orqali)
     */
    fun getStudents(): Flow<List<StudentEntity>> = studentDao.getAllStudents()

    /**
     * Talabalarni ism yoki familiya bo‘yicha qidirish (JOIN query bilan)
     */
    fun searchStudents(query: String): Flow<List<StudentWithFaculty>> = studentDao.searchStudents(query)

    /**
     * Talabani id bo‘yicha o‘chirish
     */
    suspend fun deleteStudent(id: Long) = studentDao.deleteStudent(id)

    /**
     * Talabani yangilash
     */
    suspend fun updateStudent(student: StudentEntity) = studentDao.updateStudent(student)

    /**
     * Id bo‘yicha bitta talaba olish (Entity)
     */
    suspend fun getStudentById(id: Long): StudentEntity? = studentDao.getStudentById(id)

    // ============================
    // Talabalar (StudentWithFaculty bilan, JOIN query)
    // ============================

    /**
     * Barcha talabalarni fakultet nomi bilan olish
     */
    fun getStudentsWithFaculty(): Flow<List<StudentWithFaculty>> = studentDao.getStudentsWithFaculty()

    /**
     * Id bo‘yicha bitta talaba (JOIN natija) olish
     */
    suspend fun getStudentWithFacultyById(id: Long): StudentWithFaculty? =
        studentDao.getStudentWithFacultyById(id)

    // ============================
    // Foydalanuvchilar (UserEntity)
    // ============================

    /**
     * Foydalanuvchini ro‘yxatdan o‘tkazish
     */
    suspend fun registerUser(user: UserEntity) = userDao.insertUser(user)

    /**
     * Username va password bo‘yicha foydalanuvchini olish
     */
    fun getUserByUsernameAndPassword(username: String, password: String): Flow<UserEntity?> =
        userDao.getUserByUsernameAndPassword(username, password)

    /**
     * Foydalanuvchi avatarini yangilash
     */
    suspend fun updateUserAvatar(username: String, avatar: String) =
        userDao.updateAvatar(username, avatar)

    /**
     * Foydalanuvchini id bo‘yicha o‘chirish
     */
    suspend fun deleteUser(id: Int) = userDao.deleteUser(id.toLong())
}
