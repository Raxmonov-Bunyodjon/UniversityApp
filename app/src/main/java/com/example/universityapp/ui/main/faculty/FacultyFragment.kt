package com.example.universityapp.ui.main.faculty

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.universityapp.R
import com.example.universityapp.databinding.FragmentFacultyBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FacultyFragment : Fragment(R.layout.fragment_faculty) {

    private var _binding: FragmentFacultyBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FacultyViewModel by viewModels()
    private lateinit var adapter: FacultyAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFacultyBinding.bind(view)

        adapter = FacultyAdapter (
            onItemClick = { faculty ->
            Snackbar.make(binding.root, "${faculty.name} tanlandi", Snackbar.LENGTH_SHORT).show()
        },
            onDeleteClick = { faculty ->
            // delete qilmoqchi bo‘lsangiz
            viewModel.deleteFaculty(faculty.id )
                Snackbar.make(binding.root, "${faculty.name} o'chirildi", Snackbar.LENGTH_SHORT).show()
        })


        binding.recyclerViewFaculty.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewFaculty.adapter = adapter

        binding.fabAddFaculty.setOnClickListener {
            val parentNav = requireActivity().findNavController(R.id.nav_host_fragment)
            parentNav.navigate(R.id.action_homeFragment_to_addFacultyFragment)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.faculties.collectLatest { list ->
                adapter.submitList(list)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
