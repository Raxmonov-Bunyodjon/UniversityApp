package com.example.universityapp.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            if (username == "admin" && password == "12345678") {
                _authState.value = AuthState.Success("Xush kelibsiz $username")
            } else {
                _authState.value = AuthState.Error("Username yoki parol noto‘g‘ri!")
            }
        }
    }

    fun signup(fullName: String, username: String, password: String) {
        viewModelScope.launch {
            if (password.length < 8) {
                _authState.value = AuthState.Error("Parol kamida 8 belgidan iborat bo‘lishi kerak!")
            } else {
                _authState.value = AuthState.Success("Ro‘yxatdan o‘tish muvaffaqiyatli!")
            }
        }
    }
}

sealed class AuthState {
    object Idle : AuthState()
    data class Success(val message: String) : AuthState()
    data class Error(val error: String) : AuthState()
}
