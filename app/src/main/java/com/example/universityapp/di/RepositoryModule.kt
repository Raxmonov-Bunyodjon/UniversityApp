package com.example.universityapp.di

import com.example.universityapp.data.repository.FacultyRepositoryImpl
import com.example.universityapp.data.repository.StudentRepositoryImpl
import com.example.universityapp.data.repository.UserRepositoryImpl
import com.example.universityapp.domain.repository.FacultyRepository
import com.example.universityapp.domain.repository.StudentRepository
import com.example.universityapp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * UserRepository interfeysini Hilt orqali UserRepositoryImpl bilan bog‘laydi.
     * @param impl - UserRepositoryImpl obyekti
     * @return UserRepository interfeysi
     * 🔹 Shu orqali repository dependency injection bilan istalgan joyda ishlatiladi
     */
    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    /**
     * FacultyRepository interfeysini Hilt orqali FacultyRepositoryImpl bilan bog‘laydi.
     */
    @Binds
    @Singleton
    abstract fun bindFacultyRepository(impl: FacultyRepositoryImpl): FacultyRepository

    /**
     * StudentRepository interfeysini Hilt orqali StudentRepositoryImpl bilan bog‘laydi.
     */
    @Binds
    @Singleton
    abstract fun bindStudentRepository(impl: StudentRepositoryImpl): StudentRepository
}