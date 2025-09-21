package com.example.universityapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.universityapp.R
import com.example.universityapp.databinding.FragmentSignupBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

/**
 * SignupFragment — foydalanuvchi ro‘yxatdan o‘tish oynasi.
 * UI elementlari bilan ishlaydi va ViewModel orqali signup jarayonini boshqaradi.
 */
@AndroidEntryPoint
class SignupFragment : Fragment() {

    // 🔹 ViewBinding bilan UI elementlariga kirish
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    // 🔹 AuthViewModel-ni olish Hilt orqali
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Fragment binding ini yaratish
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ========================
        // 🔹 Input validation
        // ========================
        // Ism, Familya, Username va Password tekshiruvlari
        binding.etFirstName.addTextChangedListener(SimpleTextWatcher {
            binding.etFirstName.error = if (it.isNullOrEmpty()) "Ism kiritilishi kerak" else null
            checkInputs()
        })
        binding.etLastName.addTextChangedListener(SimpleTextWatcher {
            binding.etLastName.error = if (it.isNullOrEmpty()) "Familya kiritilishi kerak" else null
            checkInputs()
        })
        binding.etUsername.addTextChangedListener(SimpleTextWatcher {
            binding.etUsername.error = if (it.isNullOrEmpty()) "Username bo‘sh bo‘lmasligi kerak" else null
            checkInputs()
        })
        binding.etPassword.addTextChangedListener(SimpleTextWatcher {
            binding.etPassword.error =
                if ((it?.length ?: 0) < 8) "Parol kamida 8 belgidan iborat bo‘lishi kerak" else null
            checkInputs()
        })

        // ========================
        // 🔹 Signup button listener
        // ========================
        binding.btnSignup.setOnClickListener {
            val firstName = binding.etFirstName.text.toString().trim()
            val lastName = binding.etLastName.text.toString().trim()
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            binding.progressBar.visibility = View.VISIBLE

            // ViewModel orqali signup chaqirish
            viewModel.signup(firstName, lastName, username, password)
        }

        // ========================
        // 🔹 Login link
        // ========================
        binding.tvGoToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }

        // ========================
        // 🔹 Auth state kuzatish (observe)
        // ========================
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.authState.collectLatest { state ->
                binding.progressBar.visibility = View.GONE

                when (state) {
                    // ✅ Muvaffaqiyat holati
                    is AuthState.Success -> {
                        showSnackbar(state.message, true)
                        // Signup tugagach faqat login sahifasiga qaytadi
                        findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
                    }

                    // ❌ Xatolik holati
                    is AuthState.Error -> showSnackbar(state.message, false)

                    // ⏸ Idle holati (hech narsa qilinmaydi)
                    AuthState.Idle -> Unit
                }
            }
        }

        // Dastlabki button enable holatini tekshirish
        checkInputs()
    }

    /**
     * 🔹 Inputlarni tekshiradi va Signup button-ni faollashtiradi yoki o‘chiradi
     */
    private fun checkInputs() {
        val firstName = binding.etFirstName.text.toString().trim()
        val lastName = binding.etLastName.text.toString().trim()
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        binding.btnSignup.isEnabled =
            firstName.isNotEmpty() && lastName.isNotEmpty() &&
                    username.isNotEmpty() && password.length >= 8
    }

    /**
     * 🔹 Snackbar orqali xabar ko‘rsatish
     */
    private fun showSnackbar(message: String, isSuccess: Boolean) {
        val snackbar = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
        if (isSuccess) {
            snackbar.setBackgroundTint(resources.getColor(R.color.teal_700, null))
        } else {
            snackbar.setBackgroundTint(resources.getColor(R.color.red, null))
        }
        snackbar.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Memory leak oldini olish
    }
}
