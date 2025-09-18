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


@AndroidEntryPoint
class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etFirstName.addTextChangedListener(SimpleTextWatcher {
            binding.etFirstName.error = if (it.isNullOrEmpty()) "Ism kiritilishi kerak" else null
            checkInputs()
        })
        binding.etLastName.addTextChangedListener(SimpleTextWatcher {
            binding.etLastName.error = if (it.isNullOrEmpty()) "Familya kiritilishi kerak" else null
            checkInputs()
        })
        binding.etUsername.addTextChangedListener(SimpleTextWatcher {
            binding.etUsername.error =
                if (it.isNullOrEmpty()) "Username bo‘sh bo‘lmasligi kerak" else null
            checkInputs()
        })
        binding.etPassword.addTextChangedListener(SimpleTextWatcher {
            binding.etPassword.error =
                if ((it?.length ?: 0) < 8) "Parol kamida 8 belgidan iborat bo‘lishi kerak" else null
            checkInputs()
        })

        binding.btnSignup.setOnClickListener {
            val firstName = binding.etFirstName.text.toString().trim()
            val lastName = binding.etLastName.text.toString().trim()
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            binding.progressBar.visibility = View.VISIBLE
            viewModel.signup(firstName, lastName, username, password)
        }

        binding.tvGoToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.authState.collectLatest { state ->
                binding.progressBar.visibility = View.GONE
                when (state) {
                    is AuthState.Success -> {
                        showSnackbar(state.message, true)
                        // ✅ signup tugagach faqat login sahifasiga qaytadi
                        findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
                    }

                    is AuthState.Error -> showSnackbar(state.message, false)
                    AuthState.Idle -> Unit
                }
            }
        }

        checkInputs()
    }

    private fun checkInputs() {
        val firstName = binding.etFirstName.text.toString().trim()
        val lastName = binding.etLastName.text.toString().trim()
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        binding.btnSignup.isEnabled =
            firstName.isNotEmpty() && lastName.isNotEmpty() &&
                    username.isNotEmpty() && password.length >= 8
    }

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
        _binding = null
    }
}