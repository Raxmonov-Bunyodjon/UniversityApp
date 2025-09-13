package com.example.universityapp.ui.main.faculty

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.universityapp.R
import com.example.universityapp.databinding.FragmentFacultyBinding
import com.example.universityapp.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FacultyFragment : Fragment(R.layout.fragment_faculty) {

    private var _binding: FragmentFacultyBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FacultyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFacultyBinding.bind(view)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            viewModel.faculties.collect { list ->
                if (list.isEmpty()) {
                    binding.root.showSnackbar("Fakultetlar yo‘q")
                } else {
                    binding.recyclerView.adapter = FacultyAdapter(list) { faculty ->
                        binding.root.showSnackbar("${faculty.name} tanlandi")
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
