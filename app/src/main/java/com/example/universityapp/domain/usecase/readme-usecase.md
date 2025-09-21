Repository Interfaces — README

repository/ papkasi data access abstraction qatlamini o‘z ichiga oladi.
Bu qatlam UI yoki biznes logikasi (UseCase) uchun DB yoki boshqa data source bilan to‘g‘ridan-to‘g‘ri ishlashni yashiradi.

repository/
├── FacultyRepository.kt     # Fakultetlar bilan ishlash uchun repository interfeysi
├── StudentRepository.kt     # Talabalar bilan ishlash uchun repository interfeysi
├── UserRepository.kt        # Foydalanuvchi ma’lumotlari bilan ishlash repository interfeysi
└── readme-repository-interface.md  # Interfeyslar haqida hujjat va misollar

📌 Layer vazifasi

Abstraktsiya yaratish

UI/ViewModel to‘g‘ridan-to‘g‘ri DB yoki network bilan ishlamaydi.

Repository interfeysi orqali CRUD operatsiyalari chaqiriladi.

Ma’lumot oqimi (Flow)

Real-time yangilanishlar uchun Flow ishlatiladi.

Misol: getAllStudents(): Flow<List<Student>>

Suspend funksiya

Qo‘shish, o‘chirish, yangilash operatsiyalari korutina ichida ishlaydi.

📚 Interfeyslar va ularning metodlari
Interfeys	Metodlar	Tavsif
FacultyRepository	getAllFaculties(), getFacultyById(id), insertFaculty(faculty), updateFaculty(faculty), deleteFaculty(id)	Fakultetlar bilan CRUD operatsiyalarini ta’minlaydi
StudentRepository	getAllStudents(), getStudentsByFaculty(facultyId), insertStudent(student), updateStudent(student), deleteStudent(studentId)	Talabalar bilan ishlash, fakultet bo‘yicha filtr
UserRepository	getUsers(), getUserByUsernameAndPassword(username, password), getUserByUsername(username), insertUser(user), deleteUser(user), signInUser(username), logout(), updateUserAvatar(username, avatar)	Foydalanuvchi ma’lumotlari, login/signup, session boshqaruvi
📊 Diagramma
┌───────────────────────┐
│      UI / ViewModel   │
└───────────┬───────────┘
│
▼
┌───────────────────────┐
│   Use Cases / Domain  │
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


UI / ViewModel: Interfeys orqali ma’lumot oladi.

Use Case / Domain: Biznes logikasi, ma’lumotni filtrlaydi yoki transformatsiya qiladi.

Repository Interfaces: CRUD va biznes logikasi uchun abstraktsiya.

Data Source: Room DB, SharedPreferences, yoki Remote API.