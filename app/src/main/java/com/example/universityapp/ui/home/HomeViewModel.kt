package com.example.universityapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityapp.data.local.UserPreferences
import com.example.universityapp.domain.repository.UserRepository
import com.example.universityapp.ui.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.logout() // 🔹 token, username, isLoggedIn = false
        }
    }
}