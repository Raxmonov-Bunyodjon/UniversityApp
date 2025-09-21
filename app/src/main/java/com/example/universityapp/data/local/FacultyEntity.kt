package com.example.universityapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * FacultyEntity — Room uchun Entity klass.
 * Bu klass "faculties" jadvalini ifodalaydi.
 */
@Entity(tableName = "faculties")
data class FacultyEntity(

    /**
     * id — fakultetning noyob identifikatori (Primary Key).
     * autoGenerate = true → Room har safar yangi fakultet qo‘shilganda id ni avtomatik beradi.
     */
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    /**
     * name — fakultet nomi (masalan: "Informatika", "Filologiya").
     */
    val name: String
)