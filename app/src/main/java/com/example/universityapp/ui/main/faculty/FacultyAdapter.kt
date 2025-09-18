package com.example.universityapp.ui.main.faculty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.universityapp.databinding.ItemFacultyBinding
import com.example.universityapp.domain.model.Faculty

class FacultyAdapter(
    private val onItemClick: (Faculty) -> Unit,
    private val onDeleteClick: (Faculty) -> Unit
) : ListAdapter<Faculty, FacultyAdapter.FacultyViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Faculty>() {
            override fun areItemsTheSame(oldItem: Faculty, newItem: Faculty) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Faculty, newItem: Faculty) = oldItem == newItem
        }
    }

    inner class FacultyViewHolder(private val binding: ItemFacultyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(faculty: Faculty) {
            binding.tvFacultyName.text = faculty.name

//            Item bosilganbda qo`shish uchun bu qism
            binding.root.setOnClickListener { onItemClick(faculty) }

            // Delete bosilganda
            binding.btnDelete.setOnClickListener { onDeleteClick(faculty) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacultyViewHolder {
        val binding = ItemFacultyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FacultyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FacultyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
