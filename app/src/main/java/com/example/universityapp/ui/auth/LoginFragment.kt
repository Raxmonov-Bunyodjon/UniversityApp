package com.example.universityapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.universityapp.R
import com.example.universityapp.databinding.FragmentLoginBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

/**
 * LoginFragment — foydalanuvchi login oynasi.
 * UI elementlari bilan ishlaydi, ViewModel orqali login jarayonini boshqaradi.
 */
@AndroidEntryPoint
class LoginFragment : Fragment() {

    // 🔹 ViewBinding bilan UI elementlariga kirish
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    // 🔹 AuthViewModel-ni olish Hilt orqali
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Fragment binding ini yaratish
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ========================
        // 🔹 Login button listener
        // ========================
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // Form validation
            if (username.isEmpty() || password.length < 8) {
                showBottomSheet("Username yoki parol noto‘g‘ri!")
                return@setOnClickListener
            }

            // Progress bar ko‘rsatish
            binding.progressBar.visibility = View.VISIBLE

            // ViewModel orqali login chaqirish
            viewModel.login(username, password)
        }

        // ========================
        // 🔹 Signup link
        // ========================
        binding.tvGoToSignup.setOnClickListener {
            // Signup fragment-ga navigatsiya
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        // ========================
        // 🔹 Auth state kuzatish (observe)
        // ========================
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.authState.collect { state ->
                // Progress barni yashirish
                binding.progressBar.visibility = View.GONE

                when (state) {
                    // ✅ Muvaffaqiyat holati
                    is AuthState.Success -> {
                        findNavController().navigate(
                            R.id.homeFragment,   // HomeFragment-ga o'tish
                            null,
                            NavOptions.Builder()
                                .setPopUpTo(
                                    R.id.loginFragment,
                                    inclusive = true
                                ) // LoginFragment backstackdan o‘chiriladi
                                .build()
                        )
                    }

                    // ❌ Xatolik holati
                    is AuthState.Error -> showBottomSheet(state.message)

                    // ⏸ Idle holati (hech narsa qilinmaydi)
                    AuthState.Idle -> Unit
                }
            }
        }
    }

    /**
     * 🔹 BottomSheet orqali xabar ko‘rsatish
     */
    private fun showBottomSheet(message: String) {
        val bottomSheet = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.layout_bottom_sheet, null)

        // Xabarni o‘rnatish
        view.findViewById<TextView>(R.id.tvMessage).text = message

        // OK button
        view.findViewById<Button>(R.id.btnOk).setOnClickListener { bottomSheet.dismiss() }

        // BottomSheet-ni ko‘rsatish
        bottomSheet.setContentView(view)
        bottomSheet.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Memory leak oldini olish
    }
}
