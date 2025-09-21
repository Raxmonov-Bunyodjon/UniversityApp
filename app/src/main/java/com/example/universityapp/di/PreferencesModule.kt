package com.example.universityapp.di

import android.content.Context
import com.example.universityapp.data.local.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton   // <<<<<<<<<<<<<<<<<<<<<

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    /**
     * UserPreferences singleton yaratadi.
     * @param context - Application context Hilt tomonidan beriladi
     * @return UserPreferences obyekti
     *
     * 🔹 Shu modul orqali repository yoki ViewModel Hilt orqali
     * UserPreferences obyekti bilan ishlay oladi
     */
    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences {
        return UserPreferences(context)
    }
}

