package com.example.universityapp.ui.main.faculty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.universityapp.databinding.ItemFacultyBinding
import com.example.universityapp.domain.model.Faculty

/**
 * 🔹 FacultyAdapter
 *   - RecyclerView uchun ListAdapter
 *   - Fakultetlar ro‘yxatini ko‘rsatadi
 *   - Click, Edit, Delete callbacklarini qo‘llab-quvvatlaydi
 */
class FacultyAdapter(
    private val onItemClick: (Faculty) -> Unit,   // 🔹 Item bosilganda callback
    private val onEditClick: (Faculty) -> Unit,   // 🔹 Edit tugmasi bosilganda callback
    private val onDeleteClick: (Faculty) -> Unit  // 🔹 Delete tugmasi bosilganda callback
) : ListAdapter<Faculty, FacultyAdapter.FacultyViewHolder>(DIFF_CALLBACK) {

    companion object {
        // 🔹 DiffUtil.ItemCallback – RecyclerView yangilanishini optimallashtirish
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Faculty>() {
            // 🔹 IDlar teng bo‘lsa elementlar bir xil deb hisoblanadi
            override fun areItemsTheSame(oldItem: Faculty, newItem: Faculty) = oldItem.id == newItem.id

            // 🔹 Elementlarning ichki qiymatlari bir xil bo‘lsa yangilanish talab qilinmaydi
            override fun areContentsTheSame(oldItem: Faculty, newItem: Faculty) = oldItem == newItem
        }
    }

    /**
     * 🔹 ViewHolder – har bir item UI sini bog‘laydi
     */
    inner class FacultyViewHolder(private val binding: ItemFacultyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * 🔹 Itemni bind qilish
         * @param faculty – Fakultet modeli
         */
        fun bind(faculty: Faculty) {
            // 🔹 TextView ga nomni o‘rnatish
            binding.tvFacultyName.text = faculty.name

            // 🔹 Item click
            binding.root.setOnClickListener { onItemClick(faculty) }

            // 🔹 Edit tugma click
            binding.btnEdit.setOnClickListener { onEditClick(faculty) }

            // 🔹 Delete tugma click
            binding.btnDelete.setOnClickListener { onDeleteClick(faculty) }
        }
    }

    /**
     * 🔹 ViewHolder yaratish va layoutni inflate qilish
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacultyViewHolder {
        val binding = ItemFacultyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FacultyViewHolder(binding)
    }

    /**
     * 🔹 ViewHolder ga data bog‘lash
     */
    override fun onBindViewHolder(holder: FacultyViewHolder, position: Int) {
        holder.bind(getItem(position)) // 🔹 ListAdapter dan item olish
    }
}
