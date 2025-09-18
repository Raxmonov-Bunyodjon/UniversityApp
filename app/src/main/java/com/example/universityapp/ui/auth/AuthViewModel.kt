package com.example.universityapp.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityapp.data.local.UserPreferences
import com.example.universityapp.domain.model.User
import com.example.universityapp.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    val currentUserFlow: Flow<String?> = userRepository.userUsernameFlow

    // ✅ Login
    fun login(username: String, password: String) {
        viewModelScope.launch((Dispatchers.IO)) {
            userRepository.getUserByUsernameAndPassword(username, password).collect { user ->
                if (user == null) {
                    _authState.emit(AuthState.Error("Username yoki parol noto‘g‘ri!"))
                } else {
                    userRepository.signInUser(username)
                    _authState.emit(AuthState.Success("Xush kelibsiz ${user.firstName} ${user.lastName}"))
                }
            }
        }
    }

    // ✅ Signup
    fun signup(firstName: String, lastName: String, username: String, password: String) {
        viewModelScope.launch((Dispatchers.IO)) {
            val existingUser = userRepository.getUserByUsername(username)
            if (existingUser != null) {
                _authState.value = AuthState.Error("Foydalanuvchi allaqachon mavjud!")
                return@launch
            }

            val newUser = User(
                id = 0,
                firstName = firstName,
                lastName = lastName,
                username = username,
                password = password
            )
            userRepository.insertUser(newUser)
            userRepository.signInUser(username)
            _authState.value = AuthState.Success("Ro‘yxatdan o‘tish muvaffaqiyatli!")
        }
    }
}
