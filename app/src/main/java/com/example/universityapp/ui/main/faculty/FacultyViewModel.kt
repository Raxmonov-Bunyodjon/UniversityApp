package com.example.universityapp.ui.main.faculty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityapp.domain.model.Faculty
import com.example.universityapp.domain.repository.FacultyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FacultyViewModel @Inject constructor(
    private val repository: FacultyRepository
) : ViewModel() {

    private val _faculties = MutableStateFlow<List<Faculty>>(emptyList())
    val faculties: StateFlow<List<Faculty>> = _faculties

    init {
        loadFaculties()
    }

    private fun loadFaculties() {
        viewModelScope.launch {
            _faculties.value = repository.getFaculties()
        }
    }
}
