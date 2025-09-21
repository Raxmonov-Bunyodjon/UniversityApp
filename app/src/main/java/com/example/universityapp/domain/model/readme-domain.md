📦 domain/ — Domen qatlam modeli

domain/ papkasi ilovaning biznes logikasi qatlamini tashkil qiladi. Bu qatlam UI va data layer orasidagi ko‘prik vazifasini bajaradi.

Ma’qsad: Ma’lumotlar bazasi (Entity) va UI qatlamidagi DTO lar o‘rtasida ishlash.

Afzallik: Domain modeli UI va repository qatlamiga to‘g‘ridan-to‘g‘ri bog‘lanmaydi, shuning uchun kod modular va test qilinishi oson bo‘ladi.

📁 Fayllar tuzilishi
domain/
├── model/
│   ├── Faculty.kt   # Fakultet entity (biznes logika darajasida)
│   ├── Student.kt   # Talaba entity
│   ├── User.kt      # Foydalanuvchi entity
│   └── readme-domain.md

🔹 Faculty.kt

Ma’qsad: Fakultet ma’lumotlarini saqlash.

Maydonlar:

id: Long — unikal identifikator

name: String — fakultet nomi

Izoh: Bu model FacultyEntity dan mapping orqali olinadi.

🔹 Student.kt

Ma’qsad: Talabalar ma’lumotlarini saqlash va UI / biznes logikada ishlatish.

Maydonlar:

id: Long — unikal identifikator

firstName: String — ism

lastName: String — familiya

facultyId: Long — fakultet ID

direction: String — yo‘nalish / mutaxassislik

avatar: String? — talabaga tegishli rasm (nullable)

facultyName: String? — fakultet nomi (JOIN natijasi, nullable)

Izoh: Bu model StudentEntity va StudentWithFaculty dan mapping orqali olinadi (mapper/StudentMapper.kt).

🔹 User.kt

Ma’qsad: Foydalanuvchi ma’lumotlarini saqlash va autentifikatsiya / profil logikasida ishlatish.

Maydonlar:

id: Long — unikal identifikator

firstName: String — ism

lastName: String — familiya

username: String — login uchun username

password: String — login uchun parol

faculty: String? — fakultet (nullable)

direction: String? — yo‘nalish (nullable)

avatar: String? — rasm (nullable)

Izoh: Bu model UserEntity dan mapping orqali olinadi (mapper/UserMapper.kt).

🔹 Domain layer diagrammasi
[FacultyEntity]        [StudentEntity]             [StudentWithFaculty]          [UserEntity]
│                     │                               │                      │
▼                     ▼                               ▼                      ▼
Faculty  <— mapping —  Student  <— mapping —  StudentWithFaculty  <— mapping —  User
│                     │                               │                      │
▼                     ▼                               ▼                      ▼
Repository layer (FacultyRepository, StudentRepository, UserRepository)
│                     │                               │                      │
▼                     ▼                               ▼                      ▼
ViewModel / UI layer


✅ Izohlar:

Domain layer UI va data layer o‘rtasida loose coupling ta’minlaydi.

Mapping (toDomain() / toEntity()) orqali Entity → Domain → UI oqimi ishlaydi.

Bu struktura test qilinishi, modularligi va maintainability ni oshiradi.