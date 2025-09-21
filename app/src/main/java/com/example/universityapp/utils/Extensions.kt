package com.example.universityapp.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * 🔹 showSnackbar
 *
 * View extension function bo‘lib, har qanday View ustida Snackbar ko‘rsatish imkonini beradi.
 *
 * Foydalanish:
 * ```
 * binding.root.showSnackbar("Xabar matni")
 * ```
 *
 * @param message Ko‘rsatmoqchi bo‘lgan matn
 */
fun View.showSnackbar(message: String) {
    // Snackbar yaratiladi va LENGTH_LONG davomiylik bilan ko‘rsatiladi
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}
