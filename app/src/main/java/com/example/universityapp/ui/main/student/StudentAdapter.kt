package com.example.universityapp.ui.main.student

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.universityapp.data.local.StudentWithFaculty
import com.example.universityapp.databinding.ItemStudentBinding

/**
 * 🔹 StudentAdapter
 *
 * RecyclerView Adapter talabalar ro‘yxatini fakultet va yo‘nalishi bilan ko‘rsatish uchun.
 * Uchta callback mavjud: item click, edit click, delete click.
 */
class StudentAdapter(
    private val onClick: (StudentWithFaculty) -> Unit,
    private val onEditClick: (StudentWithFaculty) -> Unit,
    private val onDeleteClick: (StudentWithFaculty) -> Unit
) : ListAdapter<StudentWithFaculty, StudentAdapter.StudentViewHolder>(DiffCallback) {

    /**
     * 🔹 ViewHolder class
     * Har bir itemni binding orqali UIga bog‘laydi.
     */
    inner class StudentViewHolder(val binding: ItemStudentBinding) :
        RecyclerView.ViewHolder(binding.root)

    /**
     * 🔹 onCreateViewHolder
     * Item layoutini inflate qiladi va ViewHolder yaratadi
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentViewHolder(binding)
    }

    /**
     * 🔹 onBindViewHolder
     * Item uchun ma’lumotlarni UIga o‘rnatadi
     */
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = getItem(position)

        // 🔹 Talaba ismi va familiyasi
        holder.binding.tvStudentName.text = "${student.firstName} ${student.lastName}"
        // 🔹 Fakultet nomi
        holder.binding.tvStudentFaculty.text = student.facultyName
        // 🔹 Yo‘nalish
        holder.binding.tvStudentDirection.text = student.direction

        // 🔹 Item click listener
        holder.itemView.setOnClickListener { onClick(student) }
        // 🔹 Edit button listener
        holder.binding.btnEditStudent.setOnClickListener { onEditClick(student) }
        // 🔹 Delete button listener
        holder.binding.btnDeleteStudent.setOnClickListener { onDeleteClick(student) }
    }

    /**
     * 🔹 DiffCallback
     * RecyclerView optimallashtirish uchun DiffUtil.ItemCallback
     * - areItemsTheSame → faqat ID solishtiradi
     * - areContentsTheSame → to‘liq obyektni solishtiradi
     */
    companion object DiffCallback : DiffUtil.ItemCallback<StudentWithFaculty>() {
        override fun areItemsTheSame(oldItem: StudentWithFaculty, newItem: StudentWithFaculty): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: StudentWithFaculty, newItem: StudentWithFaculty): Boolean =
            oldItem == newItem
    }
}
