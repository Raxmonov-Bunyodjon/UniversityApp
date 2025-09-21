Domain Layer — README

domain/ papkasi biznes logikasi va data abstraction qatlamini o‘z ichiga oladi. Bu qatlam UI yoki network direkt ishlamaydi, balki quyidagi uch asosiy qatlamga bo‘linadi:

domain/
├── model/       → Biznes logikaga tegishli entitylar
├── repository/  → Data access interfeyslar (abstraktsiya)
├── usecase/     → Biznes harakatlar (UseCase)

📁 1. model/

Vazifa: Ma’lumotlar strukturasi (entitylar) va biznes logikasi uchun model yaratish.

Fayllar:

Fayl	Tavsif
Faculty.kt	Fakultet entity (id, name)
Student.kt	Talaba entity (id, firstName, lastName, facultyId, direction, avatar, facultyName)
User.kt	Foydalanuvchi entity (id, firstName, lastName, username, password, faculty?, direction?, avatar?)

Izoh: Bu qatlam UI yoki repository dan mustaqil ishlaydi, mapping va use-case lar bilan bog‘lanadi.

📁 2. repository/

Vazifa: Data access abstraction — UI/ViewModel va data source (DB/API) orasida interfeys qatlam.

Fayllar:

Fayl	Tavsif
FacultyRepository.kt	Fakultetlar bilan ishlash interfeysi
StudentRepository.kt	Talabalar bilan ishlash interfeysi
UserRepository.kt	Foydalanuvchi ma’lumotlari bilan ishlash interfeysi

Izoh:

CRUD metodlari abstraktsiya qilinadi.

Repository interfeysi orqali UseCase lar DB yoki Remote API bilan ishlaydi.

📁 3. usecase/

Vazifa: Biznes harakatlar logikasi.

Fayllar:

Fayl	Tavsif
GetUsersUseCase.kt	Foydalanuvchilarni olish
LoginUserUseCase.kt	Login qilish
RegisterUserUseCase.kt	Ro‘yxatdan o‘tkazish

Izoh:

UI/ViewModel faqat UseCase lardan chaqiradi.

UseCase lar repository interfeyslariga murojaat qiladi.

📊 Umumiy Diagramma (domain qatlam bog‘lanishi)
┌───────────────────────┐
│      UI / ViewModel   │
└───────────┬───────────┘
│
▼
┌───────────────────────┐
│   Use Cases / Domain  │
│  (Login, Register,   │
│   GetUsers va h.k.) │
└───────────┬───────────┘
│
▼
┌─────────────── Repository Interfaces ──────────────┐
│                                                    │
│  FacultyRepository       StudentRepository        UserRepository │
│  (CRUD Fakultet)         (CRUD Talaba)           (CRUD + Auth)  │
└───────────────┬───────────┬───────────┬───────────┘
│           │           │
▼           ▼           ▼
Data Source / Local DB / Remote API


UI/ViewModel: Foydalanuvchi bilan ishlaydi.

UseCase: Biznes harakatlarni amalga oshiradi, repository lar bilan bog‘lanadi.

Repository Interfaces: CRUD va autentifikatsiya uchun abstraktsiya qatlam.

Data Source: Room DB, SharedPreferences, yoki Remote API.