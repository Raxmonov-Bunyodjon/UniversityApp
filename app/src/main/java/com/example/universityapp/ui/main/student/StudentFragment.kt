package com.example.universityapp.ui.main.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.universityapp.R
import com.example.universityapp.databinding.FragmentStudentBinding
import com.example.universityapp.utils.showSnackbar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * 🔹 StudentFragment
 *
 * Talabalar ro‘yxatini ko‘rsatish, qidirish, qo‘shish, tahrirlash va o‘chirish imkoniyatlari bilan.
 */
@AndroidEntryPoint
class StudentFragment : Fragment() {

    private var _binding: FragmentStudentBinding? = null
    private val binding get() = _binding!!

    // 🔹 ViewModel orqali talabalar ma'lumotlarini olish
    private val viewModel: StudentViewModel by viewModels()

    // 🔹 RecyclerView Adapter
    private lateinit var adapter: StudentAdapter

    /**
     * 🔹 onCreateView
     * Layoutni binding orqali inflate qiladi
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * 🔹 onViewCreated
     * RecyclerView, Adapter, FloatingActionButton va SearchView sozlamalari
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔹 Adapter yaratish va listenerlarini o‘rnatish
        adapter = StudentAdapter(
            onClick = { student ->
                // Item bosilganda snack bar bilan xabar chiqarish
                binding.root.showSnackbar("${student.firstName} ${student.lastName} tanlandi")
            },
            onEditClick = { student ->
                //bu joyini o`zgartirdim
                val parentNav = requireActivity().findNavController(R.id.nav_host_fragment)
                // Edit button bosilganda AddStudentFragmentga studentId bilan o'tish
                val bundle = Bundle().apply {
                    putLong("studentId", student.id)
                }
                //bu joyini o`zgartirdim
                parentNav.navigate(R.id.addStudentFragment, bundle)
            },
            onDeleteClick = { student ->
                // Delete button bosilganda ViewModel orqali o‘chirish va snackbar
                viewModel.deleteStudent(student.id)
                Snackbar.make(binding.root, "${student.firstName} o'chirildi", Snackbar.LENGTH_SHORT)
                    .show()
            }
        )

        // 🔹 RecyclerView sozlamalari
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // 🔹 Yangi talaba qo‘shish FloatingActionButton
        binding.fabAddStudent.setOnClickListener {
            val parentNav = requireActivity().findNavController(R.id.nav_host_fragment)
            val bundle = Bundle().apply {
                putLong("facultyId", -1L) // yangi qo‘shish uchun ID -1
            }
            parentNav.navigate(R.id.addStudentFragment, bundle)
        }

        // 🔹 SearchView qidiruv listener
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchStudents(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.searchStudents(it) }
                return true
            }
        })

        // 🔹 Talabalar ro‘yxatini observer bilan kuzatish va adapterga yuborish
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.students.collect { list ->
                adapter.submitList(list)
            }
        }
    }

    /**
     * 🔹 onDestroyView
     * Bindingni null qilib memory leakni oldini olish
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
