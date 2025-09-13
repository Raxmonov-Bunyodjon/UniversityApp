

📚 UniversityApp
UniversityApp — bu Android ilovasi bo‘lib, universitet talabalar, fakultetlar va foydalanuvchilarni boshqarish uchun mo‘ljallangan. Ilova orqali foydalanuvchi ro‘yxatdan o‘tishi, tizimga kirishi va ma’lumotlarni ko‘rishi mumkin.


🚀 Asosiy funksiyalar

👤 Foydalanuvchi ro‘yxatdan o‘tishi va login

🎓 Talabalar ro‘yxatini ko‘rish va tahrirlash

🏫 Fakultetlar, guruhlar, yo‘nalishlar haqida ma’lumotlar

📄 Room Database orqali CRUD operatsiyalar

🧩 MVVM arxitekturasi + Hilt (DI)

🌐 Retrofit orqali tarmoq aloqasi

⚙️ Coroutine bilan asinxron jarayonlar

📊 ViewModel, LiveData, Navigation Component



## 🛠 Texnologiyalar

| Layer        | Texnologiya                          |
|--------------|--------------------------------------|
| UI           | XML, ConstraintLayout, GridView      |
| Architecture | MVVM, Repository Pattern             |
| Database     | Room, DAO                            |
| DI           | Hilt (dagger-hilt)                   |
| Network      | Retrofit                             |
| Async        | Coroutine                            |
| State        | ViewModel, LiveData                  |
| Navigation   | Navigation Component                 |
| Versioning   | Git + GitLab                         |


📦 Loyihaning tuzilmasi
📦 Loyihaning tuzilmasi — tavsif
Bu loyiha modullarga ajratilgan, MVVM arxitekturasiga asoslangan Android ilova bo‘lib, quyidagi asosiy papkalar mavjud:
app/src/main/java/com/example/universityapp/ Loyihaning asosiy Kotlin kodlari shu yerda joylashgan. Ichida quyidagi modullar mavjud:
ui/ — foydalanuvchi interfeysi: fragmentlar, adapterlar, viewmodel’lar
data/ — ma’lumotlar manbasi: Room entity’lar, DAO’lar, repository implementatsiyasi
domain/ — biznes logika: model va repository interfeyslar
di/ — dependency injection modullari (Hilt)
utils/ — yordamchi funksiyalar, konstantalar
res/ XML layoutlar, drawable fayllar, string resurslar va navigation graph shu yerda
build.gradle.kts (app va root) Gradle konfiguratsiyasi — dependency’lar, plugin’lar va versiyalar
settings.gradle.kts Modul konfiguratsiyasi va loyihani build qilish uchun kerakli fayl


📲 Ishga tushirish

Android Studio’da loyihani oching
gradlew build orqali build qiling
Emulator yoki real qurilmada ishga tushiring



🤝 Hissa qo‘shish
Pull requestlar ochiq! Taklif, xatolik yoki yangi funksiya bo‘lsa — bemalol issue oching yoki PR yuboring.


📧 Aloqa
Savollar yoki hamkorlik uchun:
Email: bunyodjonraxmonov22@gmail.com
Telegram @RaxmonovBunyodjon