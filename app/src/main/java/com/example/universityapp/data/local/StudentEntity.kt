package com.example.universityapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class StudentEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val facultyId: Int,   // Foreign key sifatida Faculty bilan bog‘lanadi
    val direction: String? = null,
    val avatar: String? = null
)
