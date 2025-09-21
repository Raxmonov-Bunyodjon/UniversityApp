package com.example.universityapp.ui.main.profile

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.universityapp.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * 🔹 ProfileFragment
 *
 * Foydalanuvchi profilini ko‘rsatish va avatarni yangilash uchun fragment.
 * ViewModel orqali user ma'lumotlarini oqim (Flow) yordamida kuzatadi.
 */
@AndroidEntryPoint
class ProfileFragment : Fragment() {

    // 🔹 ViewBinding uchun nullable property
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    // 🔹 ViewModel inject qilinadi
    private val viewModel: ProfileViewModel by viewModels()

    // 🔹 Image picker launcher ActivityResult API orqali
    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                // 🔹 Tanlangan rasmni ImageView-ga o‘rnatish
                Glide.with(this)
                    .load(it)
                    .circleCrop() // doira shaklida ko‘rsatish
                    .into(binding.ivProfile)

                // 🔹 ViewModel orqali avatarni saqlash
                viewModel.saveAvatar(it.toString())
            }
        }

    /**
     * 🔹 onCreateView
     * Fragment layoutini inflate qiladi va bindingni o‘rnatadi.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * 🔹 onViewCreated
     * UI va interaktivlikni sozlash:
     * - User ma'lumotlarini collect qilish
     * - Avatar o‘zgartirish tugmasi
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔹 LifecycleScope bilan StateFlow kolleksiyasi
        viewLifecycleOwner.lifecycleScope.launch {
            // Fragment STARTED bo‘lgan paytda userFlow kuzatiladi
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userFlow.collect { user ->
                    user?.let {
                        // 🔹 UI ga user ma'lumotlarini o‘rnatish
                        binding.tvFirstName.text = it.firstName
                        binding.tvLastName.text = it.lastName
                        binding.tvUsername.text = it.username

                        // 🔹 Agar avatar mavjud bo‘lsa, uni ImageView-ga yuklash
                        it.avatar?.let { avatarUri ->
                            Glide.with(this@ProfileFragment)
                                .load(avatarUri)
                                .circleCrop()
                                .into(binding.ivProfile)
                        }
                    }
                }
            }
        }

        // 🔹 Avatar o‘zgartirish tugmasi listener
        binding.btnChangeAvatar.setOnClickListener {
            pickImageLauncher.launch("image/*") // foydalanuvchi rasm tanlaydi
        }
    }

    /**
     * 🔹 onDestroyView
     * Bindingni tozalash (memory leak oldini olish)
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
