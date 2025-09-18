package com.example.universityapp.ui.main.faculty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityapp.domain.model.Faculty
import com.example.universityapp.domain.repository.FacultyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FacultyViewModel @Inject constructor(
    private val repository: FacultyRepository
) : ViewModel() {

    private val _faculties = MutableStateFlow<List<Faculty>>(emptyList())
    val faculties: StateFlow<List<Faculty>> get() = _faculties

    init {
        loadFaculties()
    }

    private fun loadFaculties() {
        repository.getFaculties().onEach { list ->

            _faculties.update { list }

        }.launchIn(viewModelScope)
    }

    fun addFaculty(name: String) {
        viewModelScope.launch {
            repository.insertFaculty(Faculty(0, name)) // id 0 -> Room avtomatik

        }
    }

    fun deleteFaculty(faculty: Long) {
        viewModelScope.launch {
            repository.deleteFaculty(faculty)
        }
    }
}