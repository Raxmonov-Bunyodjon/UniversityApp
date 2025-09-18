package com.example.universityapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "faculties")
data class  FacultyEntity(
    @PrimaryKey(autoGenerate = true) val id: Long    = 0,
    val name: String
)
