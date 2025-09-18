// DatabaseModule.kt
package com.example.universityapp.di

import android.content.Context
import androidx.room.Room
import com.example.universityapp.data.local.AppDatabase
import com.example.universityapp.data.local.UserDao
import com.example.universityapp.data.local.StudentDao
import com.example.universityapp.data.local.FacultyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "university_db"
        )
            .fallbackToDestructiveMigration() // eski DB ni o‘chirib yangisini yaratadi
            .build()
    }

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    fun provideStudentDao(db: AppDatabase): StudentDao = db.studentDao()

    @Provides
    fun provideFacultyDao(db: AppDatabase): FacultyDao = db.facultyDao()
}