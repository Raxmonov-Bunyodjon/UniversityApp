package com.example.universityapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        UserEntity::class,
        StudentEntity::class,
        FacultyEntity::class  // ✅ shu entity qo‘shilgan bo‘lishi kerak
    ],
    version = 3, // version-ni oshiring
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun studentDao(): StudentDao
    abstract fun facultyDao(): FacultyDao
}