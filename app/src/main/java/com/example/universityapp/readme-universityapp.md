UniversityApp

📘 Umumiy tavsif
UniversityApp – bu Android ilovasi bo‘lib, unda universitet talabalar, fakultetlar va foydalanuvchi profillari bilan ishlash mumkin. Loyiha MVVM arxitekturasi va Hilt dependency injection yordamida tuzilgan.

Ilova quyidagi qatlamlardan iborat:

Data layer – Room database, repository implementatsiyasi, mapperlar.

Domain layer – biznes logikasi, model va repository interfeyslari, use-case’lar.

UI layer – Fragment, ViewModel, Adapter, user interaction.

DI layer – Hilt modullari va provider’lar.

Utils – global constants va Kotlin extension functions.

Application class – UniversityApp ilovaning entry point’i.

📁 Loyiha tuzilmasi
kotlin+java/
└── com/
└── example/
└── universityapp/
├── data/        # 📦 Ma'lumotlar qatlam (Room, RepositoryImpl, Mapper)
├── di/          # 🧬 Dependency Injection moduli (Hilt/Dagger)
├── domain/      # 🧠 Biznes logika (Model, Repository interface, UseCase)
├── ui/          # 🎨 Foydalanuvchi interfeysi (Fragment, ViewModel, Adapter)
├── utils/       # 🛠️ Extensionlar va konstantalar
├── UniversityApp.kt  # 🚀 Asosiy Application klass (entry point)
└── readme-universityapp.md  # 📘 Loyihaning umumiy hujjati

🧩 Qatlamlar tavsifi
1. Data Layer (data/)

Vazifasi: barcha ma’lumotlarni saqlash va ularga ishlov berish.

Ichida:

local/ – Room Entity, DAO, relationlar.

mapper/ – database obyektlarini domain modelga o‘tkazish.

repository/ – ma’lumotlarni olish va saqlash logikasi.

2. Domain Layer (domain/)

Vazifasi: biznes logikasi va domain modeli.

Ichida:

model/ – Student, Faculty, User data class’lari.

repository/ – interfeyslar (faqat metodlar imzosi).

usecase/ – ma’lumotlarni olish, filtrlash va transformatsiya qilish logikasi.

3. UI Layer (ui/)

Vazifasi: foydalanuvchi interfeysi.

Ichida:

auth/ – login va signup ekranlari.

splash/ – splash ekran.

home/ – home fragment.

main/ – talabalar va fakultetlar ro‘yxati, qo‘shish/edit fragmentlari.

profile/ – foydalanuvchi profilini boshqarish.

Pattern: MVVM (Fragment/ViewModel/Adapter).

4. DI Layer (di/)

Vazifasi: dependency injection.

Izoh: Hilt modul va provider’lari orqali repository, database, Retrofit va boshqa obyektlarni inject qiladi.

5. Utils (utils/)

Vazifasi: umumiy foydali funksiya va konstantalar.

Ichida:

Constants.kt – global konstantalar.

Extensions.kt – Kotlin extension functions (masalan, View.showSnackbar).

6. Application class (UniversityApp.kt)

Vazifasi: ilovaning entry point’i va Hilt’ni ishga tushiradi.

Annotatsiya: @HiltAndroidApp.

Shu yerda analytics, logging va boshqa global initializations qilinadi.

🔄 Diagramma (Bog‘liqlik)
flowchart LR
UniversityApp["UniversityApp.kt"] -->|DI Container| DI["di/ Hilt modules"]
DI --> Data["data/ Repository & Room"]
Data --> Domain["domain/ Models & Repository Interfaces"]
Domain --> UI["ui/ Fragments & ViewModels"]
UI --> Utils["utils/ Extensions & Constants"]

    UI -->|User interacts| Data
    UI -->|Uses| Domain
    Domain --> Data


Izoh diagramma:

UniversityApp boshlang‘ich entry point.

Hilt DI orqali repository va service’lar UI qatlamiga inject qilinadi.

UI qatlamida Fragment/ViewModel/Adapter ishlaydi.

Domain qatlamida biznes logikasi ishlaydi, Data qatlamidan ma’lumot oladi.

Utils qatlamidagi yordamchi funksiyalar ham UI va Data qatlamida ishlatiladi.

🔹 Xulosa

Bu struktura modullar va qatlamlarni aniq ajratib, ilovani testlash, yangilash va kengaytirishni osonlashtiradi.

MVVM + Hilt = modular va maintainable architecture

Room + Repository pattern = ma’lumotlar qatlamini mustahkamlash

Extension functions + constants = kodni qayta ishlatishni osonlashtirish