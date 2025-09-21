package com.example.universityapp.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Singleton DataStore extension property.
 * "user_prefs" nomli preferences faylini yaratadi va butun app bo‘ylab ishlatiladi.
 */
private val Context.dataStore by preferencesDataStore(name = "user_prefs")

/**
 * UserPreferences — foydalanuvchi ma’lumotlarini (masalan: username)
 * DataStore orqali saqlash va olish uchun klass.
 */
class UserPreferences(private val context: Context) {

    companion object {
        /**
         * KEY_USERNAME — preferences ichidagi kalit.
         * Bu kalit orqali username saqlanadi va olinadi.
         */
        val KEY_USERNAME = stringPreferencesKey("user_username")
    }

    /**
     * Username saqlash.
     * @param username — saqlanadigan foydalanuvchi nomi.
     * context.dataStore.edit { ... } orqali yoziladi.
     */
    suspend fun saveUsername(username: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_USERNAME] = username
        }
    }

    /**
     * Saqlangan username-ni olish.
     * Flow<String?> qaytaradi → real vaqt rejimida kuzatish mumkin.
     */
    val userUsernameFlow: Flow<String?> = context.dataStore.data
        .map { prefs -> prefs[KEY_USERNAME] }

    /**
     * Logout operatsiyasi.
     * Hamma DataStore ma’lumotlarini tozalaydi.
     */
    suspend fun clearUser() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}
