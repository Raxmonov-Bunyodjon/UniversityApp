package com.example.universityapp.ui.main.faculty.addfaculty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityapp.domain.model.Faculty
import com.example.universityapp.domain.repository.FacultyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 🔹 AddFacultyViewModel
 *   - Fakultet qo‘shish va tahrirlash biznes logikasi
 *   - Repository orqali DB bilan ishlaydi
 */
@HiltViewModel
class AddFacultyViewModel @Inject constructor(
    private val repository: FacultyRepository
) : ViewModel() {

    /**
     * 🔹 Yangi fakultet qo‘shish
     * @param name Fakultet nomi
     * id = 0, Room avtomatik yangi ID beradi
     */
    fun addFaculty(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFaculty(Faculty(0, name))
        }
    }

    /**
     * 🔹 Mavjud fakultetni yangilash
     * @param id Fakultetning ID si
     * @param name Yangi nom
     */
    fun updateFaculty(id: Long, name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFaculty(Faculty(id, name))
        }
    }

    /**
     * 🔹 ID bo‘yicha fakultetni olish
     * Edit rejimi uchun kerak bo‘ladi
     * @param id Fakultet ID si
     * @return Faculty yoki null
     */
    suspend fun getFacultyById(id: Long): Faculty? {
        return repository.getFacultyById(id)
    }
}
