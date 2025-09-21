package com.example.universityapp.ui.main.student.addstudent

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.universityapp.data.local.StudentEntity
import com.example.universityapp.databinding.FragmentAddStudentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * 🔹 AddStudentFragment
 *
 * Talaba qo‘shish (ADD) va tahrirlash (EDIT) UI fragmenti.
 * Spinner orqali fakultet tanlash, inputlar orqali ism/familiya/direction kiritish.
 * Save tugmasi faqat barcha required fieldlar to‘ldirilsa enable bo‘ladi.
 */
@AndroidEntryPoint
class AddStudentFragment : Fragment() {

    // ---------------------------
    // 🔹 ViewBinding
    // ---------------------------
    private var _binding: FragmentAddStudentBinding? = null
    private val binding get() = _binding!!

    // ---------------------------
    // 🔹 ViewModel (Hilt orqali inject)
    // ---------------------------
    private val viewModel: AddStudentViewModel by viewModels()

    // ---------------------------
    // 🔹 Spinner orqali tanlangan fakultet ID
    // ---------------------------
    private var selectedFacultyId: Long? = null

    // ---------------------------
    // 🔹 Layoutni inflate qilish
    // ---------------------------
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    // ---------------------------
    // 🔹 UI tayyor bo‘lganda ishlaydi
    // ---------------------------
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔹 Fakultetlarni spinnerga ulash
        setupFacultySpinner()

        // 🔹 Argumentdan kelgan studentId → EDIT rejim uchun
        val studentId = arguments?.getLong("studentId", -1L) ?: -1L
        if (studentId != -1L) {
            viewLifecycleOwner.lifecycleScope.launch {
                val student = viewModel.getStudentById(studentId)
                student?.let {
                    binding.etFirstName.setText(it.firstName)
                    binding.etLastName.setText(it.lastName)
                    selectedFacultyId = it.facultyId
                    binding.etFacultyDirection.setText(it.direction)
                }
            }
        }

        // 🔹 Save tugmasini boshlanishda disable qilish
        updateSaveButtonState()

        // 🔹 TextWatcherlar orqali tugma holatini yangilash
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { updateSaveButtonState() }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        binding.etFirstName.addTextChangedListener(textWatcher)
        binding.etLastName.addTextChangedListener(textWatcher)

        // 🔹 Save tugmasi bosilganda ADD yoki EDIT funksiyasini chaqirish
        binding.btnSaveStudent.setOnClickListener {
            if (studentId == -1L) saveStudent()
            else updateStudent(studentId)
        }
    }

    // ---------------------------
    // 🔹 Save tugmasini enable/disable qiladigan funksiya
    // ---------------------------
    private fun updateSaveButtonState() {
        val enabled = binding.etFirstName.text.toString().isNotBlank() &&
                binding.etLastName.text.toString().isNotBlank() &&
                selectedFacultyId != null

        binding.btnSaveStudent.isEnabled = enabled
        binding.btnSaveStudent.alpha = if (enabled) 1f else 0.5f
    }

    // ---------------------------
    // 🔹 Mavjud studentni yangilash (EDIT)
    // ---------------------------
    private fun updateStudent(id: Long) {
        val firstName = binding.etFirstName.text.toString().trim()
        val lastName = binding.etLastName.text.toString().trim()
        val direction = binding.etFacultyDirection.text.toString().trim()

        val student = StudentEntity(
            id = id,
            firstName = firstName,
            lastName = lastName,
            facultyId = selectedFacultyId!!,
            direction = direction,
            avatar = null
        )

        viewModel.updateStudent(student)
        Toast.makeText(requireContext(), "Talaba yangilandi ✅", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }

    // ---------------------------
    // 🔹 Fakultetlarni Spinnerga ulash
    // ---------------------------
    private fun setupFacultySpinner() {
        viewLifecycleOwner.lifecycleScope.launch {
            val faculties = viewModel.faculties.first()

            val facultyNames = mutableListOf("Fakultet tanlanmagan")
            facultyNames.addAll(faculties.map { it.name })

            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                facultyNames
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerFaculty.adapter = adapter

            // 🔹 Spinner listener
            binding.spinnerFaculty.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>, view: View?, position: Int, id: Long
                    ) {
                        selectedFacultyId = if (position == 0) null else faculties[position - 1].id
                        updateSaveButtonState()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        selectedFacultyId = null
                        updateSaveButtonState()
                    }
                }
        }
    }

    // ---------------------------
    // 🔹 Yangi student qo‘shish (ADD)
    // ---------------------------
    private fun saveStudent() {
        val firstName = binding.etFirstName.text.toString().trim()
        val lastName = binding.etLastName.text.toString().trim()
        val direction = binding.etFacultyDirection.text.toString().trim()

        val student = StudentEntity(
            firstName = firstName,
            lastName = lastName,
            facultyId = selectedFacultyId!!,
            direction = direction,
            avatar = null
        )

        viewModel.addStudent(student)
        Toast.makeText(requireContext(), "Talaba saqlandi ✅", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }

    // ---------------------------
    // 🔹 Memory leak oldini olish
    // ---------------------------
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
