package com.example.universityapp.data.local

/**
 * StudentWithFaculty — Student va Faculty ma’lumotlarini birlashtirib turadigan model.
 * Bu klass Room’da JOIN query natijasini olish uchun ishlatiladi.
 */
data class StudentWithFaculty(

    /**
     * id — talabani noyob identifikatori.
     * Bu `students` jadvalidagi Primary Key.
     */
    val id: Long,

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
     * Bu `faculties` jadvalidagi id bilan bog‘liq.
     */
    val facultyId: Long,      // YANGI: facultyId qo'shildi

    /**
     * facultyName — fakultet nomi.
     * Bu JOIN orqali `faculties` jadvalidan olinadi.
     */
    val facultyName: String,  // fakultet nomi (JOIN natijasi)

    /**
     * direction — talabaning yo‘nalishi (masalan: IT, Fizika, Filologiya).
     */
    val direction: String,

    /**
     * avatar — talabaning rasmi (URL yoki fayl yo‘li).
     * Null bo‘lishi mumkin (agar rasm kiritilmagan bo‘lsa).
     */
    val avatar: String?
)
