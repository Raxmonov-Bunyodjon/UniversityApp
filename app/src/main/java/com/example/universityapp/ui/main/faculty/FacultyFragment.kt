package com.example.universityapp.ui.main.faculty

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
import com.example.universityapp.databinding.FragmentFacultyBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * 🔹 FacultyFragment
 *   - Fakultetlar ro‘yxatini ko‘rsatadi
 *   - Qo‘shish, edit, delete funksiyalarini boshqaradi
 *   - SearchView bilan qidiruvni qo‘llab-quvvatlaydi
 */
@AndroidEntryPoint
class FacultyFragment : Fragment() {

    private var _binding: FragmentFacultyBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FacultyViewModel by viewModels()
    private lateinit var adapter: FacultyAdapter

    /**
     * 🔹 Layoutni inflate qilish va binding o‘rnatish
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFacultyBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * 🔹 UI elementlarni sozlash va listenerlar o‘rnatish
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔹 Adapter yaratish va callbacklarni bog‘lash
        adapter = FacultyAdapter(
            onItemClick = { faculty ->
                Snackbar.make(binding.root, "${faculty.name} tanlandi", Snackbar.LENGTH_SHORT)
                    .show()
            },
            onEditClick = { faculty ->
                // shu yerda o`zgarish qildim
                val parentNav = requireActivity().findNavController(R.id.nav_host_fragment)
                val bundle = Bundle().apply {
                    putLong("facultyId", faculty.id) // edit qilish uchun ID uzatish
                }
                // buni ham
                parentNav.navigate(R.id.addFacultyFragment, bundle)
            },
            onDeleteClick = { faculty ->
                viewModel.deleteFaculty(faculty.id) // delete ViewModel orqali
                Snackbar.make(binding.root, "${faculty.name} o'chirildi", Snackbar.LENGTH_SHORT)
                    .show()
            }
        )

        // 🔹 RecyclerView sozlash
        binding.recyclerViewFaculty.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewFaculty.adapter = adapter

        // 🔹 FAB orqali yangi fakultet qo‘shish
        binding.fabAddFaculty.setOnClickListener {
            val parentNav = requireActivity().findNavController(R.id.nav_host_fragment)
            val bundle = Bundle().apply {
                putLong("facultyId", -1L) // yangi qo‘shish uchun maxsus qiymat
            }
            parentNav.navigate(R.id.addFacultyFragment, bundle)
        }

        // 🔹 SearchView bilan qidiruv listener
        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchFaculties(it) } // submit qilinganda qidirish
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.searchFaculties(it) } // yozish davomida qidirish
                return true
            }
        })

        // 🔹 Facultetlar listini observe qilish va adapterga yuborish
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.faculties.collectLatest { list ->
                adapter.submitList(list)
            }
        }
    }

    /**
     * 🔹 Bindingni tozalash
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
