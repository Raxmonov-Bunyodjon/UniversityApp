package com.example.universityapp.ui.auth


sealed class AuthState {
    object Idle : AuthState()                     // Boshlang'ich holat
    data class Success(val message: String) : AuthState()  // Muvaffaqiyat
    data class Error(val message: String) : AuthState()    // Xatolik
}