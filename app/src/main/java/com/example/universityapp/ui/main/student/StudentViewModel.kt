package com.example.universityapp.ui.main.student

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityapp.data.local.StudentEntity
import com.example.universityapp.data.local.StudentWithFaculty
import com.example.universityapp.data.repository.UniversityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 🔹 StudentViewModel
 *
 * Talabalar bilan ishlash (CRUD + qidiruv) va UI state boshqaruvi.
 */
@HiltViewModel
class StudentViewModel @Inject constructor(
    private val repository: UniversityRepository
) : ViewModel() {

    // 🔹 Talabalar ro'yxati - internal state
    private val _students = MutableStateFlow<List<StudentWithFaculty>>(emptyList())
    val students: StateFlow<List<StudentWithFaculty>> get() = _students

    /**
     * 🔹 init block
     * Fragment ishga tushganda talabalar ro‘yxatini yuklaydi
     */
    init {
        loadStudents()
    }

    /**
     * 🔹 loadStudents
     * Barcha talabalarni repository orqali olib keladi va stateFlowni yangilaydi
     */
    private fun loadStudents() {
        repository.getStudentsWithFaculty()
            .onEach { list ->
                _students.value = list
            }
            .launchIn(viewModelScope)
    }

    /**
     * 🔹 searchStudents
     * Qidiruv funksiyasi: query bo'sh bo'lsa barcha talabalar, aks holda filtrlangan ro'yxat
     */
    fun searchStudents(query: String) {
        if (query.isBlank()) {
            loadStudents()
        } else {
            repository.searchStudents(query)
                .onEach { list ->
                    _students.value = list
                }
                .launchIn(viewModelScope)
        }
    }

    /**
     * 🔹 addStudent
     * Yangi talaba qo‘shish
     */
    fun addStudent(student: StudentEntity) {
        viewModelScope.launch { repository.addStudent(student) }
    }

    /**
     * 🔹 updateStudent
     * Talaba ma'lumotlarini yangilash
     */
    fun updateStudent(student: StudentEntity) {
        viewModelScope.launch { repository.updateStudent(student) }
    }

    /**
     * 🔹 deleteStudent
     * Talabani ID orqali o‘chirish
     */
    fun deleteStudent(id: Long) {
        viewModelScope.launch { repository.deleteStudent(id) }
    }

    /**
     * 🔹 getStudentById
     * Talabani ID orqali olish (edit uchun)
     */
    suspend fun getStudentById(id: Long): StudentWithFaculty? {
        return repository.getStudentWithFacultyById(id)
    }
}
