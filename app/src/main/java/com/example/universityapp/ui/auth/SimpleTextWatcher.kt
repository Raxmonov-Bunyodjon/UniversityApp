package com.example.universityapp.ui.auth

import android.text.Editable
import android.text.TextWatcher

/**
 * SimpleTextWatcher — TextWatcher interfeysini soddalashtirilgan wrapper.
 * Faqat `onTextChanged` callback bilan ishlash uchun mo‘ljallangan.
 *
 * @param onTextChanged Lambda funksiya, har safar matn o‘zgarganda chaqiriladi.
 */
class SimpleTextWatcher(private val onTextChanged: (String?) -> Unit) : TextWatcher {

    /**
     * Text o‘zgarmasidan oldin chaqiriladi.
     * Bu yerda hech narsa qilinmaydi.
     */
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    /**
     * Text o‘zgarganda chaqiriladi.
     * Lambda orqali yangi matnni uzatadi.
     */
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChanged(s?.toString())
    }

    /**
     * Text o‘zgargandan keyin chaqiriladi.
     * Bu yerda ham hech narsa qilinmaydi.
     */
    override fun afterTextChanged(s: Editable?) {}
}
