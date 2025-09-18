package com.example.universityapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.universityapp.data.local.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: UserEntity)

    @Query("DELETE FROM userentity WHERE id = :id")
    suspend fun deleteUser(id: Int)

    @Query("SELECT * FROM userentity WHERE username = :username AND password = :password LIMIT 1")
    fun getUserByUsernameAndPassword(username: String, password: String): Flow<UserEntity?>

    @Query("SELECT * FROM userentity WHERE username = :username LIMIT 1")
    fun getUserByUsername(username: String): Flow<UserEntity?>

    @Query("SELECT * FROM userentity")
    fun getAllUsers(): Flow<List<UserEntity>>
}