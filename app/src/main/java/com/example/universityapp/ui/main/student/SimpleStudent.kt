package com.example.universityapp.ui.main.student

/**
 * 🔹 SimpleStudent
 *
 * Bu data class UI qatlamida talabalarni ko‘rsatish uchun mo‘ljallangan.
 * Biznes logikadagi Student modelidan farqli o‘laroq:
 * - Faqat UI uchun kerakli maydonlar saqlanadi
 * - Fakultet nomi va yo‘nalish string sifatida qo‘shilgan
 *
 * ⚡ Maqsad: RecyclerView yoki boshqa UI elementlarda tezkor render uchun yengil model.
 */
data class SimpleStudent(
    val id: Long,              // Talabaning unikal ID-si (DB bilan bog‘lash uchun)
    val firstName: String,     // Talabaning ismi
    val lastName: String,      // Talabaning familiyasi
    val facultyName: String,   // Talabaning fakulteti (UI uchun display)
    val direction: String      // Talabaning yo‘nalishi (UI uchun display)
)
