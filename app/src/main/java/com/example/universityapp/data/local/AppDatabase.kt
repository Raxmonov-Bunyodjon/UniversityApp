package com.example.universityapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FacultyEntity::class, StudentEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun facultyDao(): FacultyDao
    abstract fun studentDao(): StudentDao
}
