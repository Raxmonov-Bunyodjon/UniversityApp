Repository Layer — README

repository/ papkasi — bu data access abstraction qatlamidir.
Repositorylar UI va biznes logikasini ma’lumotlar bazasi yoki boshqa data source-lardan ajratib turadi.

repository/
├── FacultyRepository.kt
├── StudentRepository.kt
├── UserRepository.kt

📌 Layer vazifasi

Abstraktsiya yaratish:

UI/ViewModel to‘g‘ridan-to‘g‘ri Room DB yoki boshqa data source bilan ishlamaydi.

Repository interfeysi orqali barcha CRUD amallar amalga oshiriladi.

Ma’lumot oqimi (Flow):

Real-time yangilanishlarni qo‘llab-quvvatlash uchun Flow ishlatiladi.

Misol: getAllStudents(): Flow<List<Student>>

Suspend funksiya:

Qo‘shish, o‘chirish, yangilash kabi operatsiyalar korutina ichida ishlaydi, ya’ni background thread-da bajariladi.

📚 Repositorylar va ularning funksiyalari
Repository	Funksiyalari	Izoh
FacultyRepository	getAllFaculties, getFacultyById, insertFaculty, updateFaculty, deleteFaculty	Fakultetlar bilan ishlash uchun CRUD operatsiyalar
StudentRepository	getAllStudents, getStudentsByFaculty, insertStudent, updateStudent, deleteStudent	Talabalar bilan ishlash, fakultet bo‘yicha filtr
UserRepository	getUsers, getUserByUsernameAndPassword, insertUser, deleteUser, signInUser, logout, updateUserAvatar	Foydalanuvchi ma’lumotlari, login/signup, session
📊 Diagramma
┌───────────┐
│   UI/VM   │
└─────┬─────┘
│
▼
┌───────────────┐
│  Repository   │
│ ┌───────────┐ │
│ │ Faculty   │ │
│ │ Repository│ │
│ └───────────┘ │
│ ┌───────────┐ │
│ │ Student   │ │
│ │ Repository│ │
│ └───────────┘ │
│ ┌───────────┐ │
│ │ User      │ │
│ │ Repository│ │
│ └───────────┘ │
└─────┬─────────┘
│
▼
┌───────────────┐
│   Data Layer  │
│ ┌───────────┐ │
│ │ Room DB   │ │
│ └───────────┘ │
│ ┌───────────┐ │
│ │ DataStore │ │
│ └───────────┘ │
└───────────────┘


UI/ViewModel → Repository → Data Layer oqimi ko‘rsatilgan.

Repositorylar domain model bilan ishlaydi, Entity va DB qatlamidan mustaqil.