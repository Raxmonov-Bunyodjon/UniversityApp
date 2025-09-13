package com.example.universityapp.ui.main.faculty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.universityapp.databinding.ItemFacultyBinding
import com.example.universityapp.domain.model.Faculty

class FacultyAdapter(
    private val items: List<Faculty>,
    private val onClick: (Faculty) -> Unit
) : RecyclerView.Adapter<FacultyAdapter.FacultyViewHolder>() {

    inner class FacultyViewHolder(private val binding: ItemFacultyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(faculty: Faculty) {
            binding.tvFacultyName.text = faculty.name
            binding.root.setOnClickListener { onClick(faculty) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacultyViewHolder {
        val binding = ItemFacultyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FacultyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FacultyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
