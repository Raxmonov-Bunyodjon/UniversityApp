# Data Layer Overview

`data/` papka ilovaning **ma'lumotlar qatlamini** tashkil qiladi.  
Bu qatlam **ma’lumot manbalarini** (Room DB, SharedPreferences, Mapperlar) UI va Domain qatlamidan ajratadi.

---

## Tuzilma

data/
├── local/ # 📦 Mahalliy ma'lumotlar bazasi va DAOlar
│ ├── AppDatabase.kt # Room DB konfiguratsiyasi
│ ├── FacultyDao.kt # Fakultet uchun DAO
│ ├── FacultyEntity.kt # Fakultet Entity modeli
│ ├── StudentDao.kt # Talaba uchun DAO
│ ├── StudentEntity.kt # Talaba Entity modeli
│ ├── StudentWithFaculty.kt# Talaba + Fakultet relatsiyasi
│ ├── UserDao.kt # Foydalanuvchi uchun DAO
│ ├── UserEntity.kt # Foydalanuvchi Entity modeli
│ ├── UserPreferences.kt # SharedPreferences / DataStore wrapper
│ └── readme-local.md # Local papka hujjati

├── mapper/ # 🔄 DTO ↔ Entity mapping logikasi
│ ├── FacultyMapper.kt # Fakultet mapping
│ ├── StudentMapper.kt # Talaba mapping
│ └── readme-mapper.md # Mapperlar haqida hujjat

├── repository/ # 📁 Ma'lumotlar bilan ishlovchi qatlam
│ ├── FacultyRepositoryImpl.kt # Fakultet repository implementatsiyasi
│ ├── StudentRepositoryImpl.kt # Talaba repository implementatsiyasi
│ ├── UniversityRepository.kt # Universitet repository (barcha CRUD)
│ ├── UserRepositoryImpl.kt # Foydalanuvchi repository implementatsiyasi
│ └── readme-repository.md # Repositorylar haqida hujjat



---

## Umumiy Diagramma: Data oqimi

```text
          ┌─────────────┐
          │  Entity     │  <-- Room DB / SharedPreferences
          │ (Faculty,   │
          │  Student,   │
          │  User)      │
          └─────┬───────┘
                │
                │ DAO Layer
                v
          ┌─────────────┐
          │   DAO       │  <-- Room / DataStore interfeysi
          │ (FacultyDao,│
          │  StudentDao,│
          │  UserDao)   │
          └─────┬───────┘
                │
                │ Mapper (Entity ↔ Domain ↔ UI)
                v
          ┌─────────────┐
          │   Repository│
          │ (CRUD logikasi) │
          │ FacultyRepo,   │
          │ StudentRepo,   │
          │ UniversityRepo,│
          │ UserRepo       │
          └─────┬───────┘
                │
                │ Domain Layer
                v
          ┌─────────────┐
          │     UI      │  <-- ViewModel orqali ma'lumotni oladi
          └─────────────┘
Papkalar va vazifalari
1️⃣ local/
Vazifasi: Mahalliy ma’lumotlar bazasi va DataStore bilan ishlash.

Muhim fayllar:

AppDatabase.kt — Room DB konfiguratsiyasi

FacultyDao.kt, StudentDao.kt, UserDao.kt — CRUD operatsiyalar uchun DAO

FacultyEntity.kt, StudentEntity.kt, UserEntity.kt — DB uchun Entity modellari

StudentWithFaculty.kt — Talaba + Fakultet JOIN query natijasi

UserPreferences.kt — DataStore orqali session va username saqlash

2️⃣ mapper/
Vazifasi: DTO ↔ Entity ↔ Domain model konvertatsiyasi

Muhim fayllar:

FacultyMapper.kt — Fakultet mapping

StudentMapper.kt — Talaba mapping

Izoh: Mapperlar yordamida repository va UI qatlamlari o‘rtasida data transformatsiya amalga oshadi.

3️⃣ repository/
Vazifasi: Ma’lumotlar bilan ishlash qatlamini ta’minlaydi, DAO va Mapperlar orqali Domain qatlamiga ma’lumot yetkazadi.

Muhim fayllar:

FacultyRepositoryImpl.kt — Fakultet CRUD logikasi

StudentRepositoryImpl.kt — Talabalar CRUD logikasi

UniversityRepository.kt — Barcha universitetga oid umumiy CRUD operatsiyalari

UserRepositoryImpl.kt — Foydalanuvchi CRUD va DataStore logikasi

🔑 Muhim tushuntirishlar
Repositorylar Domain va Data qatlamlarini ajratadi.

Flow ishlatilgani sababli UI real vaqt rejimida yangilanadi.

Mapperlar yordamida Entity ↔ Domain ↔ UI konvertatsiyasi amalga oshiriladi.

UniversityRepository katta va umumiy CRUD operatsiyalarni birlashtiradi, kichik repositorylar esa faqat o‘z mas’uliyatiga tegishli metodlarni o‘z ichiga oladi.

Har bir repository stateless bo‘lishi tavsiya etiladi (faqat ma’lumotni olish, qo‘shish, o‘chirish, yangilash bilan shug‘ullanadi).