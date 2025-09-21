📂 Strukturasi
faculty/
└── addfaculty/
├── AddFacultyFragment.kt    # Yangi fakultet qo‘shish UI
├── AddFacultyViewModel.kt   # Qo‘shish logikasi va state boshqaruvi
├── FacultyAdapter.kt        # Fakultetlar ro‘yxatini RecyclerView orqali ko‘rsatish
├── FacultyFragment.kt       # Fakultetlar ro‘yxatini ko‘rsatadigan UI
└── FacultyViewModel.kt      # Fakultetlar ro‘yxatini boshqaruvchi ViewModel

🔹 Fayllar tavsifi
1️⃣ FacultyFragment.kt

Fakultetlar ro‘yxatini ko‘rsatadi.

FacultyAdapter bilan birga ishlaydi.

RecyclerView itemlari uchun: Item click, Edit, Delete listenerlarini boshqaradi.

fabAddFaculty orqali yangi fakultet qo‘shish fragmentiga navigatsiya qiladi.

SearchView orqali fakultetlarni filtrlaydi.

ViewModel (FacultyViewModel) bilan bog‘langan: barcha data va search funksiyasini oladi.

2️⃣ FacultyViewModel.kt

Barcha fakultetlarni va filtrlangan fakultetlarni saqlaydi.

Repository orqali DB bilan ishlaydi (FacultyRepository).

Funksiyalari:

loadFaculties() – DB’dan barcha fakultetlarni yuklaydi.

searchFaculties(query) – SearchView bo‘yicha filtrlaydi.

addFaculty(name) – Yangi fakultet qo‘shadi.

updateFaculty(faculty) – Fakultetni yangilaydi.

deleteFaculty(id) – Fakultetni o‘chiradi.

getFacultyById(id) – Edit rejimi uchun ma’lumotni oladi.

3️⃣ FacultyAdapter.kt

RecyclerView Adapteri.

Fakultetlar ro‘yxatini ko‘rsatadi.

Har bir item uchun:

onItemClick – item bosilganda.

onEditClick – edit button bosilganda.

onDeleteClick – delete button bosilganda.

DiffUtil bilan optimallashtirilgan.

4️⃣ AddFacultyFragment.kt

Yangi fakultet qo‘shish yoki mavjud fakultetni tahrirlash UI.

EditText orqali nom kiritish.

btnSaveFaculty orqali add/update amallarini bajaradi.

Snackbar va Toast orqali feedback beradi.

Fragment argument orqali Edit rejimi uchun facultyId oladi.

AddFacultyViewModel bilan bog‘langan.

5️⃣ AddFacultyViewModel.kt

Qo‘shish va tahrirlash biznes logikasi.

Repository orqali DB bilan ishlaydi (FacultyRepository).

Funksiyalari:

addFaculty(name) – yangi fakultet qo‘shadi.

updateFaculty(id, name) – mavjud fakultetni yangilaydi.

getFacultyById(id) – Edit rejimi uchun ma’lumotni oladi.

viewModelScope.launch(Dispatchers.IO) orqali background thread’da ishlaydi.

🔹 Data Flow Diagram
FacultyFragment/RecyclerView ----> FacultyAdapter
|                                |
|                                v
|                          onItemClick / onEditClick / onDeleteClick
v
FacultyViewModel <----> FacultyRepository <----> Room Database
^
|
| fabAddFaculty click
v
AddFacultyFragment ----> AddFacultyViewModel ----> FacultyRepository ----> Room DB

Izohlar:

FacultyFragment – UI layer, foydalanuvchi interaktivligi.

FacultyViewModel – biznes logikasi va state management.

FacultyRepository – data source abstraction (Room DB bilan ishlaydi).

AddFacultyFragment / ViewModel – yangi fakultet qo‘shish va tahrirlash uchun.

Data Flow – UI → ViewModel → Repository → DB → Repository → ViewModel → UI

🔹 Commit tarzida ishlash logikasi

Foydalanuvchi Search, Edit, Delete, Add tugmalarini bosadi.

Fragment ViewModel orqali Repository bilan muloqot qiladi.

Repository DB bilan ishlaydi.

DB dan olingan data StateFlow orqali Fragment-ga qaytariladi.

UI darajasida adapter yordamida RecyclerView yangilanadi.

Add/Edit Fragment orqali yangi ma’lumot qo‘shiladi yoki mavjudini yangilanadi.