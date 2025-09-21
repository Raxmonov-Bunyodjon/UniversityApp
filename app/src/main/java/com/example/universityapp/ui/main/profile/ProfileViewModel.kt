package com.example.universityapp.ui.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityapp.domain.model.User
import com.example.universityapp.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 🔹 ProfileViewModel
 *
 * Foydalanuvchi profilini boshqarish uchun ViewModel.
 * UserRepository orqali login qilgan foydalanuvchi va avatarni kuzatadi va yangilaydi.
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    /**
     * 🔹 userFlow
     *
     * Hozirgi login qilgan foydalanuvchini kuzatadi.
     * StateFlow sifatida lifecycle-safe collect qilish uchun.
     *
     * userUsernameFlow (repository) -> username -> to‘liq User ma'lumotini olish
     */
    val userFlow: StateFlow<User?> = userRepository.userUsernameFlow
        .map { username ->
            if (username.isNullOrEmpty()) null
            else userRepository.getUserByUsername(username)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily, // Flow faqat kerak bo‘lganda ishlaydi
            initialValue = null
        )

    /**
     * 🔹 saveAvatar
     *
     * Tanlangan avatar URI-ni foydalanuvchi profiliga saqlaydi.
     * Flow orqali olinadigan hozirgi user bilan ishlaydi.
     */
    fun saveAvatar(avatarUri: String) {
        viewModelScope.launch {
            val currentUser = userFlow.value
            currentUser?.let { user ->
                // Repository orqali avatarni yangilash
                userRepository.updateUserAvatar(user.username, avatarUri)
            }
        }
    }
}
