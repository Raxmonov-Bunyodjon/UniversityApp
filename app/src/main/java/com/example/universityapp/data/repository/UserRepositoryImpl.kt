package com.example.universityapp.data.repository

import com.example.universityapp.data.local.UserDao
import com.example.universityapp.data.local.UserEntity
import com.example.universityapp.data.local.UserPreferences
import com.example.universityapp.domain.model.User
import com.example.universityapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * UserRepositoryImpl — UserRepository interfeysining implementatsiyasi.
 * DAO va DataStore orqali foydalanuvchi ma’lumotlarini boshqaradi.
 */
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val preferences: UserPreferences,
) : UserRepository {

    // ============================
    // DataStore orqali username oqimi
    // ============================
    override val userUsernameFlow: Flow<String?>
        get() = preferences.userUsernameFlow

    // ============================
    // Foydalanuvchilar ro‘yxatini olish
    // ============================
    override fun getUsers(): Flow<List<User>> {
        return userDao.getAllUsers().map { list ->
            list.map { entity ->
                User(
                    id = entity.id,
                    firstName = entity.firstName,
                    lastName = entity.lastName,
                    username = entity.username,
                    password = entity.password
                )
            }
        }
    }

    // ============================
    // Login: username va password bo‘yicha foydalanuvchini olish
    // ============================
    override fun getUserByUsernameAndPassword(username: String, password: String): Flow<User?> {
        return userDao.getUserByUsernameAndPassword(username, password).map { entity ->
            entity?.let {
                User(
                    id = it.id,
                    firstName = it.firstName,
                    lastName = it.lastName,
                    username = it.username,
                    password = it.password
                )
            }
        }
    }

    // ============================
    // Signup: username bo‘yicha foydalanuvchini olish
    // ============================
    override suspend fun getUserByUsername(username: String): User? {
        val entity = userDao.getUserByUsername(username).firstOrNull()
        return entity?.let {
            User(
                id = it.id,
                firstName = it.firstName,
                lastName = it.lastName,
                username = it.username,
                password = it.password,
                faculty = it.faculty,
                direction = it.direction,
                avatar = it.avatar
            )
        }
    }

    // ============================
    // Yangi foydalanuvchi qo‘shish
    // ============================
    override suspend fun insertUser(user: User) {
        userDao.insertUser(
            UserEntity(
                id = user.id,
                firstName = user.firstName,
                lastName = user.lastName,
                username = user.username,
                password = user.password
            )
        )
    }

    // ============================
    // Foydalanuvchini o‘chirish
    // ============================
    override suspend fun deleteUser(user: User) {
        userDao.deleteUser(user.id)
    }

    // ============================
    // Login: username saqlash (DataStore)
    // ============================
    override suspend fun signInUser(username: String) {
        preferences.saveUsername(username)
    }

    // ============================
    // Logout: barcha session ma’lumotlarini tozalash
    // ============================
    override suspend fun logout() {
        preferences.clearUser()
    }

    // ============================
    // Foydalanuvchi avatarini yangilash
    // ============================
    override suspend fun updateUserAvatar(username: String, avatar: String) {
        userDao.updateAvatar(username, avatar)
    }
}
