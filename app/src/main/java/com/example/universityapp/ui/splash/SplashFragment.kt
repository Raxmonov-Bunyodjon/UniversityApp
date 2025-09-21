package com.example.universityapp.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.universityapp.R
import com.example.universityapp.ui.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * 🔹 SplashFragment
 *
 * Ilova ishga tushganda foydalanuvchi login qilingan yoki qilinmaganini tekshiradi
 * va tegishli navgraf orqali yo‘naltiradi:
 * - Agar foydalanuvchi login qilmagan bo‘lsa → LoginFragment
 * - Agar login qilingan bo‘lsa → HomeFragment
 */
@AndroidEntryPoint
class SplashFragment : Fragment() {

    // 🔹 AuthViewModel – foydalanuvchi login holatini tekshirish uchun
    private val viewModel: AuthViewModel by viewModels()

    /**
     * 🔹 Layoutni inflate qilish
     * Fragment uchun XML faylini view bilan bog‘laydi.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // fragment_splash.xml layoutini fragmentga bog‘lash
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    /**
     * 🔹 View tayyor bo‘lganda ishga tushadigan funksiya
     * Foydalanuvchi login holatini tekshiradi va navgraf orqali yo‘naltiradi.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔹 LifecycleScope ichida coroutine ishga tushadi
        // repeatOnLifecycle ishlatmasdan ham oddiy collect qilindi
        viewLifecycleOwner.lifecycleScope.launch {
            // 🔹 Foydalanuvchi login holatini oqim orqali olish
            viewModel.currentUserFlow.collect { username ->
                if (username.isNullOrBlank()) {
                    // 🔹 Agar foydalanuvchi login qilmagan bo‘lsa, LoginFragment ga yo‘naltirish
                    findNavController().navigate(R.id.loginFragment)
                } else {
                    // 🔹 Agar foydalanuvchi login qilingan bo‘lsa, HomeFragment ga yo‘naltirish
                    findNavController().navigate(R.id.homeFragment)
                }
            }
        }
    }
}
