// 🔹 Bu top-level build.gradle fayl barcha sub-project/module lar uchun umumiy sozlamalarni beradi.

plugins {
    // 🔹 Android application plugin (false = module ichida qo‘llanadi)
    alias(libs.plugins.android.application) apply false

    // 🔹 Kotlin plugin (false = module ichida qo‘llanadi)
    alias(libs.plugins.kotlin.android) apply false

    // 🔹 Hilt plugin (Dependency Injection uchun)
    // version = "2.52", apply false = faqat module ichida apply qilinadi
    id("com.google.dagger.hilt.android") version "2.52" apply false

    // 🔹 KSP (Kotlin Symbol Processing) plugin
    // version = Kotlin + KSP mos versiyasi
    id("com.google.devtools.ksp") version "2.0.21-1.0.25" apply false

    // 🔹 Navigation Safe Args plugin (tipli argumentlar uchun)
    id("androidx.navigation.safeargs.kotlin") version "2.8.3" apply false
}
