package com.example.universityapp.data.repository

import com.example.universityapp.data.local.FacultyDao
import com.example.universityapp.data.local.FacultyEntity
import com.example.universityapp.data.local.StudentDao
import com.example.universityapp.data.local.StudentEntity
import com.example.universityapp.data.local.UserDao
import com.example.universityapp.data.local.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UniversityRepository @Inject constructor(
    private val facultyDao: FacultyDao,
    private val studentDao: StudentDao,
    private val userDao: UserDao   // ✅ User bilan ishlash uchun
) {
    // Fakultetlar
    suspend fun addFaculty(faculty: FacultyEntity) = facultyDao.insertFaculty(faculty)
    fun getFaculties(): Flow<List<FacultyEntity>> = facultyDao.getAllFaculties()
    suspend fun deleteFaculty(id: Long) = facultyDao.deleteFaculty(id)

    // Talabalar
    suspend fun addStudent(student: StudentEntity) = studentDao.insertStudent(student)
    fun getStudents(): Flow<List<StudentEntity>> = studentDao.getAllStudents()
    fun searchStudents(query: String): Flow<List<StudentEntity>> = studentDao.searchStudents(query)
    suspend fun deleteStudent(id: Int) = studentDao.deleteStudent(id)

    // Foydalanuvchilar (User)
    suspend fun registerUser(user: UserEntity) = userDao.insertUser(user)


    // ✅ To‘g‘ri variant
    fun getUserByUsernameAndPassword(username: String, password: String): Flow<UserEntity?> =
        userDao.getUserByUsernameAndPassword(username, password)

    suspend fun deleteUser(id: Int) = userDao.deleteUser(id)
}