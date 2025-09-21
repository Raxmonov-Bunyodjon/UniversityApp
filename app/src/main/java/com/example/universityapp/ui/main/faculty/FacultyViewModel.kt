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

/**
 * 🔹 FacultyViewModel
 *   - Fakultetlar bilan ishlash: load, search, add, update, delete
 *   - UI bilan StateFlow orqali bog‘langan
 */
@HiltViewModel
class FacultyViewModel @Inject constructor(
    private val repository: FacultyRepository
) : ViewModel() {

    // 🔹 Barcha fakultetlar saqlanadi
    private val _allFaculties = MutableStateFlow<List<Faculty>>(emptyList())

    // 🔹 UI uchun filtrlangan yoki to‘liq fakultetlar listi
    private val _faculties = MutableStateFlow<List<Faculty>>(emptyList())
    val faculties: StateFlow<List<Faculty>> get() = _faculties

    init {
        loadFaculties() // 🔹 boshlang‘ich yuklash
    }

    /**
     * 🔹 Barcha fakultetlarni repositorydan yuklab olish
     * va _allFaculties hamda _faculties Flowlarni yangilash
     */
    private fun loadFaculties() {
        viewModelScope.launch {
            repository.getAllFaculties().collect { list ->
                _allFaculties.value = list
                _faculties.value = list // boshlang‘ichda barcha fakultetlar
            }
        }
    }

    /**
     * 🔹 Qidiruv funksiyasi
     *   - query bo‘sh bo‘lsa barcha fakultetlar
     *   - aks holda query ga mos keladiganlarini filter qiladi
     */
    fun searchFaculties(query: String) {
        val filtered = if (query.isEmpty()) {
            _allFaculties.value
        } else {
            _allFaculties.value.filter { it.name.contains(query, ignoreCase = true) }
        }
        _faculties.value = filtered
    }

    /**
     * 🔹 Yangi fakultet qo‘shish
     */
    fun addFaculty(name: String) {
        viewModelScope.launch {
            repository.insertFaculty(Faculty(0, name))
        }
    }

    /**
     * 🔹 Mavjud fakultetni yangilash
     */
    fun updateFaculty(faculty: Faculty) {
        viewModelScope.launch {
            repository.updateFaculty(faculty)
        }
    }

    /**
     * 🔹 Fakultetni o‘chirish
     */
    fun deleteFaculty(id: Long) {
        viewModelScope.launch {
            repository.deleteFaculty(id)
        }
    }

    /**
     * 🔹 ID orqali fakultetni olish (masalan edit uchun)
     */
    suspend fun getFacultyById(id: Long): Faculty? {
        return repository.getFacultyById(id)
    }
}
