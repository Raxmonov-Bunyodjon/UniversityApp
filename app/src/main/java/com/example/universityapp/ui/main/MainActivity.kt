package com.example.universityapp.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.universityapp.R
import com.example.universityapp.databinding.ActivityMainBinding
import com.example.universityapp.ui.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

/**
 * 🔹 MainActivity
 *
 * Ilovaning asosiy Activity komponenti.
 * - Hamma fragmentlar shu Activity ichida ishlaydi (NavHostFragment orqali).
 * - Dastlab foydalanuvchi login qilganligini tekshiradi va start destination ni shunga qarab o‘rnatadi.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // 🔹 ViewBinding
    private lateinit var binding: ActivityMainBinding

    // 🔹 AuthViewModel orqali foydalanuvchi login holati tekshiriladi
    private val authViewModel: AuthViewModel by viewModels()

    /**
     * 🔹 Activity yaratish va boshlash
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🔹 Layoutni inflate qilish
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🔹 NavController olish
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // 🔹 Navigation Graphni inflate qilish
        val graph = navController.navInflater.inflate(R.navigation.main_nav_graph)

        // 🔹 Dastlabki startDestinationni aniqlash (login qilinganmi yoki yo‘q)
        lifecycleScope.launch {
            val username = authViewModel.currentUserFlow.firstOrNull() // Hozirgi foydalanuvchi username

            // 🔹 Agar foydalanuvchi mavjud bo‘lmasa, loginFragment; aks holda homeFragment
            graph.setStartDestination(
                if (username.isNullOrEmpty()) {
                    R.id.loginFragment
                } else {
                    R.id.homeFragment
                }
            )

            // 🔹 NavControllerga yangilangan graphni o‘rnatish
            navController.graph = graph
        }
    }
}
