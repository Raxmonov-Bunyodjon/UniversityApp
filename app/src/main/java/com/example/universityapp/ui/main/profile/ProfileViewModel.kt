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
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    // Flow<User?>: hozirgi login qilgan foydalanuvchi ma’lumotlari
    val userFlow: StateFlow<User?> = userRepository.userUsernameFlow
        .map { username ->
            if (username.isNullOrEmpty()) null
            else userRepository.getUserByUsername(username)
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
}
