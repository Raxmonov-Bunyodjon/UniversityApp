package com.example.universityapp.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.universityapp.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    init {
        // Hozircha test uchun dummy data
        _user.value = User(
            id = 1,
            firstName = "Ali",
            lastName = "Valiyev",
            username = "ali123",
            password = "12345678"
        )
    }
}
