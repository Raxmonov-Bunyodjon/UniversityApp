package com.example.universityapp.ui.main.faculty.addfaculty

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.universityapp.R
import com.example.universityapp.databinding.FragmentAddFacultyBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFacultyFragment : Fragment(R.layout.fragment_add_faculty) {

    private var _binding: FragmentAddFacultyBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddFacultyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddFacultyBinding.bind(view)

        binding.btnSaveFaculty.setOnClickListener {
            val name = binding.etFacultyName.text.toString().trim()
            if (name.isNotEmpty()) {
                viewModel.addFaculty(name)
                findNavController().popBackStack()
            } else {
                Snackbar.make(binding.root, "Fakultet nomini kiriting", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}