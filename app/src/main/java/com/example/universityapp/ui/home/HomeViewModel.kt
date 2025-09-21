package com.example.universityapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityapp.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository // 🔹 UserRepository inject qilinadi
) : ViewModel() {

    /**
     * Logout funksiyasi
     * 🔹 Foydalanuvchini tizimdan chiqaradi
     * 🔹 SharedPreferences yoki local storage ichidagi username/token tozalaydi
     */
    fun logout() {
        // 🔹 IO dispatcher bilan coroutine ishga tushadi
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.logout() // 🔹 username, token va login status false qilinadi
        }
    }
}