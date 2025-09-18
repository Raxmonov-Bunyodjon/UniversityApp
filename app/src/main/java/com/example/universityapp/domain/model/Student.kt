package com.example.universityapp.domain.model

data class Student(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val facultyId: Int,
    val direction: String,
    val avatar: String
)