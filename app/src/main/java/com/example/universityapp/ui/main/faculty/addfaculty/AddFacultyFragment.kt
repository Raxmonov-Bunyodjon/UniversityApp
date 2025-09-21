package com.example.universityapp.ui.main.faculty.addfaculty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.universityapp.R
import com.example.universityapp.databinding.FragmentAddFacultyBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * 🔹 AddFacultyFragment
 *   - Fakultet qo‘shish va tahrirlash UI
 *   - Argument orqali Edit yoki Add rejimi aniqlanadi
 */
@AndroidEntryPoint
class AddFacultyFragment : Fragment() {

    private var _binding: FragmentAddFacultyBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddFacultyViewModel by viewModels()

    private var facultyId: Long = -1  // 🔹 Add: -1, Edit: mavjud ID

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 🔹 Layoutni binding orqali inflate qilish
        _binding = FragmentAddFacultyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔹 NavGraph argumentidan facultyId olish
        facultyId = arguments?.getLong("facultyId", -1) ?: -1

        // 🔹 Edit rejimida mavjud fakultetni chiqarish
        if (facultyId != -1L) {
            viewLifecycleOwner.lifecycleScope.launch {
                val faculty = viewModel.getFacultyById(facultyId)
                faculty?.let {
                    binding.etFacultyName.setText(it.name)
                }
            }
        }

        // 🔹 Button matnini Add / Update holatiga qarab o‘zgartirish
        binding.btnSaveFaculty.text = if (facultyId == -1L) "Qo'shish" else "Yangilash"

        // 🔹 Boshlanishda tugmani disable qilamiz shularni qo`shdim 22 sentabrda
        binding.btnSaveFaculty.isEnabled = binding.etFacultyName.text.toString().isNotBlank()

        // 🔹 EditText uchun TextWatcher buni ham qo`shdim 22-sentabrda
        binding.etFacultyName.addTextChangedListener {
            binding.btnSaveFaculty.isEnabled = it.toString().isNotBlank()
        }

        // 🔹 Saqlash tugmasi bosilganda
        binding.btnSaveFaculty.setOnClickListener {
            val name = binding.etFacultyName.text.toString().trim()
            if (name.isNotEmpty()) {
                if (facultyId == -1L) {
                    // 🔹 Add
                    viewModel.addFaculty(name)
                } else {
                    // 🔹 Update
                    viewModel.updateFaculty(facultyId, name)
                }
                Toast.makeText(requireContext(), "Fakultet saqlandi ✅", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack() // 🔹 oldingi fragmentga qaytish
            } else {
                Snackbar.make(binding.root, "Fakultet nomini kiriting", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // 🔹 Memory leak oldini olish
    }
}
