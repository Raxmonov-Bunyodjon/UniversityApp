package com.example.universityapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * UserDao — foydalanuvchi (UserEntity) bilan ishlash uchun DAO interfeysi.
 * Bunda Room orqali ma'lumotlar bazasida CRUD va boshqa querylar bajariladi.
 */
@Dao
interface UserDao {

    /**
     * Foydalanuvchini qo‘shish.
     * Agar user oldin mavjud bo‘lsa (username unique bo‘lsa), u holda xato beradi (ABORT).
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: UserEntity)

    /**
     * ID bo‘yicha foydalanuvchini o‘chirish.
     */
    @Query("DELETE FROM users WHERE id = :id")
    suspend fun deleteUser(id: Long)

    /**
     * Username va password bo‘yicha foydalanuvchini olish.
     * Login qilishda ishlatiladi.
     * Flow<UserEntity?> qaytaradi → real vaqt rejimida kuzatish mumkin.
     */
    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    fun getUserByUsernameAndPassword(username: String, password: String): Flow<UserEntity?>

    /**
     * Username bo‘yicha foydalanuvchini olish.
     * Masalan: foydalanuvchi mavjudligini tekshirishda kerak.
     */
    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    fun getUserByUsername(username: String): Flow<UserEntity?>

    /**
     * Barcha foydalanuvchilarni olish.
     * Flow<List<UserEntity>> qaytaradi → doimiy kuzatib borish mumkin.
     */
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>

    /**
     * Username bo‘yicha avatar yangilash.
     * Masalan: foydalanuvchi profil rasmini o‘zgartirganda ishlatiladi.
     */
    @Query("UPDATE users SET avatar = :avatar WHERE username = :username")
    suspend fun updateAvatar(username: String, avatar: String)
}
