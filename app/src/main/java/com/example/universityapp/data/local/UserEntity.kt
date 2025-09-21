package com.example.universityapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * UserEntity — Room uchun Entity klass.
 * Bu klass "users" jadvalini ifodalaydi.
 */
@Entity(tableName = "users")
data class UserEntity(

    /**
     * id — foydalanuvchining noyob identifikatori (Primary Key).
     * autoGenerate = true → Room yangi user qo‘shilganda id ni avtomatik beradi.
     */
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    /**
     * firstName — foydalanuvchining ismi.
     */
    val firstName: String,

    /**
     * lastName — foydalanuvchining familiyasi.
     */
    val lastName: String,

    /**
     * username — foydalanuvchi login nomi.
     * Unique bo‘lishi tavsiya qilinadi (login uchun ishlatiladi).
     */
    val username: String,

    /**
     * password — foydalanuvchi paroli.
     */
    val password: String,

    /**
     * faculty — foydalanuvchining fakulteti (nullable).
     */
    val faculty: String? = null,

    /**
     * direction — foydalanuvchining yo‘nalishi (nullable).
     */
    val direction: String? = null,

    /**
     * avatar — foydalanuvchining rasmi (URL yoki fayl yo‘li).
     * Nullable → agar rasm kiritilmagan bo‘lsa, null bo‘ladi.
     */
    val avatar: String? = null
)