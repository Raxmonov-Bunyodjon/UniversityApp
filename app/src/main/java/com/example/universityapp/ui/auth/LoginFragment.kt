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


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔹 Login button
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (username.isEmpty() || password.length < 8) {
                showBottomSheet("Username yoki parol noto‘g‘ri!")
                return@setOnClickListener
            }

            binding.progressBar.visibility = View.VISIBLE
            viewModel.login(username, password)
        }

        // 🔹 Signup link
        binding.tvGoToSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        // 🔹 Auth state observe
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.authState.collect { state ->
                binding.progressBar.visibility = View.GONE
                when (state) {
                    is AuthState.Success -> {
                        findNavController().navigate(
                            R.id.homeFragment,
                            null,
                            NavOptions.Builder()
                                .setPopUpTo(
                                    R.id.loginFragment,
                                    inclusive = true
                                ) // loginFragment ni ham o‘chiradi
                                .build()
                        )
                    }

                    is AuthState.Error -> showBottomSheet(state.message)
                    AuthState.Idle -> Unit
                }
            }
        }
    }

    private fun showBottomSheet(message: String) {
        val bottomSheet = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.layout_bottom_sheet, null)
        view.findViewById<TextView>(R.id.tvMessage).text = message
        view.findViewById<Button>(R.id.btnOk).setOnClickListener { bottomSheet.dismiss() }
        bottomSheet.setContentView(view)
        bottomSheet.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
