package com.example.universityapp.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityapp.domain.model.User
import com.example.universityapp.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * AuthViewModel — foydalanuvchi autentifikatsiyasi uchun ViewModel.
 * Login va Signup jarayonlarini boshqaradi, va UI uchun holatlarni StateFlow orqali beradi.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository, // Repository orqali ma'lumotlar qatlamiga kirish
) : ViewModel() {

    // 🔹 MutableStateFlow orqali Auth holati saqlanadi va UI kuzatadi
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    // 🔹 Hozirgi login qilgan foydalanuvchi username-ni kuzatish
    val currentUserFlow: Flow<String?> = userRepository.userUsernameFlow

    // ========================
    // ✅ Login funksiyasi
    // ========================
    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            // Foydalanuvchini username va parol bilan olish
            userRepository.getUserByUsernameAndPassword(username, password).collect { user ->
                if (user == null) {
                    // Agar foydalanuvchi topilmasa, xatolik holatini yuboradi
                    _authState.emit(AuthState.Error("Username yoki parol noto‘g‘ri!"))
                } else {
                    // Foydalanuvchi topilgan holatda login saqlanadi va muvaffaqiyat holati yuboriladi
                    userRepository.signInUser(username)
                    _authState.emit(
                        AuthState.Success("Xush kelibsiz ${user.firstName} ${user.lastName}")
                    )
                }
            }
        }
    }

    // ========================
    // ✅ Signup (Ro‘yxatdan o‘tish)
    // ========================
    fun signup(firstName: String, lastName: String, username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            // Avval foydalanuvchi mavjudligini tekshiradi
            val existingUser = userRepository.getUserByUsername(username)
            if (existingUser != null) {
                // Agar foydalanuvchi allaqachon mavjud bo‘lsa, xatolik holati
                _authState.value = AuthState.Error("Foydalanuvchi allaqachon mavjud!")
                return@launch
            }

            // Yangi foydalanuvchi obyektini yaratish
            val newUser = User(
                id = 0, // Room DB auto-increment qiladi
                firstName = firstName,
                lastName = lastName,
                username = username,
                password = password
            )

            // DB ga qo‘shish va login saqlash
            userRepository.insertUser(newUser)
            userRepository.signInUser(username)

            // Muvaffaqiyat holatini yuborish
            _authState.value = AuthState.Success("Ro‘yxatdan o‘tish muvaffaqiyatli!")
        }
    }
}