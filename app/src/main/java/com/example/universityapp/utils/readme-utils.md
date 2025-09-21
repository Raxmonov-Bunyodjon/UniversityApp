utils/ moduli

utils/ moduli loyihaning turli joylarida takrorlanuvchi yordamchi funksiyalar va konstantalarni joylashtirish uchun ishlatiladi. Bu modul orqali kodni qayta ishlatish osonlashadi va loyihani toza saqlash mumkin.

Fayllar tuzilishi
utils/
├── Constants.kt   # Loyihada ishlatiladigan global konstantalar
└── Extensions.kt  # Kotlin extension funksiyalar to‘plami

Constants.kt

Ma’nosi:
Loyihaning turli joylarida ishlatiladigan statik yoki global qiymatlar shu yerda saqlanadi. Hozircha bo‘sh klass, keyinchalik masalan:

class Constants {
companion object {
const val BASE_URL = "https://api.universityapp.com"
const val PREFS_NAME = "university_prefs"
}
}


Foydasi:

Kodda “magic strings” yoki qiymatlarni qayta-qayta yozmaslik.

Oson boshqarish va o‘zgartirish.

Extensions.kt

Ma’nosi:
Kotlin extension funksiyalari yordamida mavjud klasslarni kengaytirish mumkin. Shu modulda umumiy ishlatiladigan extension funksiyalar jamlangan.

Misol:

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: String) {
Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}


Har qanday View ustida ishlatiladi.

Xabar matnini argument sifatida oladi.

Ko‘rsatish davomiyligi LENGTH_LONG.

Foydasi:

Kodni qisqartirish va o‘qilishi oson qilish.

Har bir View uchun alohida Snackbar yaratish shart emas.

Diagramma: modul bog‘lanishi
+----------------+
|     utils/     |
+----------------+
| Constants.kt   | <-- Global qiymatlar (BASE_URL, PREFS_NAME, ...)
| Extensions.kt  | <-- Extension funksiyalar (View.showSnackbar, ...)
+----------------+
^
|
| ishlatiladi
v
+-----------------------+
| UI / ViewModel / Repo |
|   Modullar            |
+-----------------------+


Izoh:

Constants.kt va Extensions.kt boshqa modullar tomonidan chaqiriladi.

UI komponentlar (Fragment, Activity) va ViewModel’lar Extensions.kt funksiyalarini va Constants.kt qiymatlarini ishlatadi.