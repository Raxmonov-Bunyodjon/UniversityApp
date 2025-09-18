package com.example.universityapp.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// ✅ Extension property (singleton DataStore)
private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        val KEY_USERNAME = stringPreferencesKey("user_username")
    }

    // ✅ Username saqlash
    suspend fun saveUsername(username: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_USERNAME] = username
        }
    }

    // ✅ Saqlangan username olish
    val userUsernameFlow: Flow<String?> = context.dataStore.data
        .map { prefs -> prefs[KEY_USERNAME] }

    // ✅ Logout (hamma ma’lumotni tozalash)
    suspend fun clearUser() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}