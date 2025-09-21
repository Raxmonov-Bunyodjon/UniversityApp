package com.example.universityapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * StudentEntity — Room uchun Entity klass.
 * Bu klass "students" jadvalini ifodalaydi.
 */
@Entity(tableName = "students")
data class StudentEntity(

    /**
     * id — talabani noyob identifikatori (Primary Key).
     * autoGenerate = true → Room yangi student qo‘shilganda id ni avtomatik beradi.
     */
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    /**
     * firstName — talabaning ismi.
     */
    val firstName: String,

    /**
     * lastName — talabaning familiyasi.
     */
    val lastName: String,

    /**
     * facultyId — talabaning qaysi fakultetga tegishli ekanini bildiradi.
     * Bu FacultyEntity jadvalidagi id bilan bog‘lanadi (foreign key vazifasida).
     */
    val facultyId: Long,

    /**
     * direction — talabaning yo‘nalishi (masalan: "Dasturiy injiniring", "Filologiya").
     */
    val direction: String,   // yangi maydon

    /**
     * avatar — talabaning rasmi (URL yoki fayl yo‘li).
     * Nullable → agar rasm yo‘q bo‘lsa, null bo‘lishi mumkin.
     */
    val avatar: String? = null // yangi maydon
)