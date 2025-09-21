📂 Strukturasi
profile/
├── ProfileFragment.kt    # Foydalanuvchi profilini ko‘rsatadigan UI komponent
└── ProfileViewModel.kt   # Profil ma'lumotlarini boshqaruvchi ViewModel

🔹 Maqsad

profile moduli foydalanuvchi profilini ko‘rsatish va boshqarish uchun mo‘ljallangan.
Bu modul quyidagi funksiyalarni bajaradi:

Hozirgi login qilgan foydalanuvchini olish va UI-ga chiqarish.

Avatarni tanlash va saqlash.

Foydalanuvchi ma’lumotlarini StateFlow orqali lifecycle-safe kuzatish.

UI bilan ViewModel o‘rtasida ma’lumot oqimini boshqarish.

🔹 Fayllar tafsiloti
1️⃣ ProfileFragment.kt

Fragment darajasida UI va interaktivlikni boshqaradi.

Xususan:

Foydalanuvchi ismi, familiyasi, username va avatarni ko‘rsatadi.

Avatar o‘zgartirish uchun ActivityResultLauncher bilan galereyadan rasm tanlash.

ViewModel-dan userFlow ni kuzatadi va UI-ni yangilaydi.

2️⃣ ProfileViewModel.kt

Profil ma’lumotlarini biznes logika qatlamida boshqaradi.

Xususan:

userFlow orqali login qilgan foydalanuvchi ma’lumotlarini taqdim etadi.

saveAvatar(avatarUri: String) funksiyasi orqali avatar URI-ni saqlaydi.

StateFlow va viewModelScope orqali lifecycle-safe coroutine boshqaruvi.

🔹 Diagramma
┌──────────────────┐
│ ProfileFragment  │
│ - UI: TextView   │
│ - ImageView      │
│ - Button         │
└────────┬─────────┘
│ collects userFlow
▼
┌──────────────────┐
│ ProfileViewModel │
│ - userFlow       │◄───────────────┐
│ - saveAvatar()   │                │
└────────┬─────────┘                │
│ calls updateUserAvatar() │
▼                           │
┌──────────────────┐                 │
│ UserRepository   │─────────────────┘
│ - userUsernameFlow
│ - getUserByUsername()
│ - updateUserAvatar()
└──────────────────┘

🔹 Oqim tushuntirish:

ProfileFragment UI elementlar orqali foydalanuvchi ma’lumotini ko‘rsatadi.

Fragment ProfileViewModel.userFlow ni kuzatadi → StateFlow lifecycle-safe tarzda UI-ni yangilaydi.

Agar foydalanuvchi avatarni o‘zgartirsa, Fragment saveAvatar(uri) ni chaqiradi.

ViewModel UserRepository.updateUserAvatar orqali ma’lumotni saqlaydi.

Repository foydalanuvchi ma’lumotlarini lokal yoki remote (DB/SharedPreferences) saqlashni boshqaradi.

🔹 Xulosa

profile/ moduli UI va biznes logikani ajratib, MVVM arxitekturasiga mos tarzda ishlaydi.

StateFlow va viewModelScope yordamida lifecycle-safe va asynchronous ma’lumot oqimi ta’minlanadi.

Fragment – faqat UI va interaktivlik, ViewModel – ma’lumot va biznes logikani boshqaradi.