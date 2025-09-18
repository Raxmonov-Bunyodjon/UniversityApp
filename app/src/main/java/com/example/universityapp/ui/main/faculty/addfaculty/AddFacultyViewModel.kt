package com.example.universityapp.ui.main.faculty.addfaculty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityapp.domain.model.Faculty
import com.example.universityapp.domain.repository.FacultyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFacultyViewModel @Inject constructor(
    private val repository: FacultyRepository
) : ViewModel() {

    fun addFaculty(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFaculty(Faculty(0, name)) // id 0 -> Room avtomatik
        }
    }
}