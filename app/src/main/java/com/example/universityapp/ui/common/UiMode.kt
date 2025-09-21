package com.example.universityapp.ui.common

/**
 * 🔹 UiMode -> umumiy ekranda qaysi rejimda ishlayotganini bildiradi.
 *
 *  Add  -> yangi obyekt qo‘shish rejimi
 *  Edit -> mavjud obyektni tahrirlash rejimi (id orqali aniqlanadi)
 */
sealed class UiMode {
    object Add : UiMode()
    data class Edit(val id: Long) : UiMode()
}