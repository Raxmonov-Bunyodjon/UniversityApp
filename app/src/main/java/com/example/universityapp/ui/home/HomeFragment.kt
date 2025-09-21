package com.example.universityapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.universityapp.R
import com.example.universityapp.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    // 🔹 Hilt orqali ViewModel inject qilinadi
    private val viewModel: HomeViewModel by viewModels()

    // 🔹 ViewBinding nullable backing property
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    /**
     * Fragment view yaratish
     * Layoutni inflate qiladi va bindingni initialize qiladi
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Fragment yaratilib bo‘lgach ishlaydi
     * Click listenerlar va navigation controller setup qilinadi
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔹 Logout tugmasi bosilganda dialog ko‘rsatish
        binding.logout.setOnClickListener {
            showLogoutDialog()
        }

        // 🔹 Nested navigation (Home ichidagi nav host fragment)
        val navHostFragment = childFragmentManager
            .findFragmentById(R.id.home_nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController

        // 🔹 BottomNavigationView navController bilan bog‘lanadi
        binding.bottomNavigation.setupWithNavController(navController)
    }

    /**
     * Logout tasdiqlash dialogi
     * Ha tugmasi bosilsa logout qilinadi va login ekraniga qaytadi
     */
    private fun showLogoutDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Chiqish")
            .setMessage("Rostdan ham tizimdan chiqmoqchimisiz?")
            .setPositiveButton("Ha") { _, _ ->
                viewModel.logout() // logout amalga oshiriladi
                findNavController().navigate(
                    R.id.loginFragment,
                    null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.main_nav_graph, inclusive = true) // stack tozalash
                        .build()
                )
            }
            .setNegativeButton("Yo‘q", null) // rad etilganda dialog yopiladi
            .show()
    }

    /**
     * Fragment view yo‘q qilinayotganda bindingni null qilamiz
     * Memory leakning oldini olish uchun
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
