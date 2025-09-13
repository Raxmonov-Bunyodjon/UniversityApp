package com.example.universityapp.ui.main.student

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.universityapp.R
import com.example.universityapp.databinding.FragmentStudentBinding
import com.example.universityapp.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StudentFragment : Fragment(R.layout.fragment_student) {

    private var _binding: FragmentStudentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StudentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentStudentBinding.bind(view)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            viewModel.students.collect { list ->
                if (list.isEmpty()) {
                    binding.root.showSnackbar("Talabalar yo‘q")
                } else {
                    binding.recyclerView.adapter = StudentAdapter(list) { student ->
                        binding.root.showSnackbar("${student.firstName} tanlandi")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
