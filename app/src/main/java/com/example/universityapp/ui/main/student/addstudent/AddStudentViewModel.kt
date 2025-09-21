package com.example.universityapp.ui.main.student.addstudent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityapp.data.local.FacultyEntity
import com.example.universityapp.data.local.StudentEntity
import com.example.universityapp.data.repository.UniversityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 🔹 AddStudentViewModel
 *
 * Talaba qo‘shish (ADD) va tahrirlash (EDIT) logikasini boshqaradi.
 * Fakultetlar oqimi, tanlangan fakultet, CRUD amallari.
 */
@HiltViewModel
class AddStudentViewModel @Inject constructor(
    private val repository: UniversityRepository
) : ViewModel() {

    // ---------------------------
    // 🔹 Barcha fakultetlar oqimi (Spinner/Dialog uchun)
    // ---------------------------
    val faculties: Flow<List<FacultyEntity>> = repository.getFaculties()

    // ---------------------------
    // 🔹 Tanlangan fakultetni saqlash
    // ---------------------------
    private val _selectedFaculty = MutableStateFlow<FacultyEntity?>(null)
    val selectedFaculty: StateFlow<FacultyEntity?> = _selectedFaculty.asStateFlow()

    fun selectFaculty(faculty: FacultyEntity) {
        _selectedFaculty.value = faculty
    }

    // ---------------------------
    // 🔹 Talaba qo‘shish (ADD)
    // ---------------------------
    fun addStudent(student: StudentEntity) {
        viewModelScope.launch {
            repository.addStudent(student)
        }
    }

    // ---------------------------
    // 🔹 Talaba yangilash (EDIT)
    // ---------------------------
    fun updateStudent(student: StudentEntity) {
        viewModelScope.launch {
            repository.updateStudent(student)
        }
    }

    // ---------------------------
    // 🔹 Talaba o'chirish (DELETE)
    // ---------------------------
    fun deleteStudent(id: Long) {
        viewModelScope.launch {
            repository.deleteStudent(id)
        }
    }

    // ---------------------------
    // 🔹 Talaba ID orqali olish (EDIT rejim)
    // ---------------------------
    suspend fun getStudentById(id: Long): StudentEntity? {
        return repository.getStudentById(id)
    }
}
