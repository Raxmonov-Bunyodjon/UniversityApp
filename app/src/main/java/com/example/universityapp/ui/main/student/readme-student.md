📚 student/ moduli

Bu modul universitet ilovasida talabalar bilan ishlash uchun javob beradi. U talabalar ro‘yxatini ko‘rsatish, qo‘shish, tahrirlash va o‘chirish funksiyalarini boshqaradi. Modulli dizayn va MVVM arxitekturasi asosida tuzilgan.

🔹 Papka tarkibi
student/
└── addstudent/
├── AddStudentFragment.kt      # Talaba qo‘shish/edit UI
├── AddStudentViewModel.kt     # Qo‘shish/edit logikasi va state boshqaruvi
├── SimpleStudent.kt           # Soddalashtirilgan talaba model (data class)
├── StudentAdapter.kt          # RecyclerView adapter: talaba ro‘yxati
├── StudentFragment.kt         # Talabalar ro‘yxatini ko‘rsatadigan UI
└── StudentViewModel.kt        # Talabalar ro‘yxatini boshqaruvchi ViewModel

🔹 Modul funksiyalari
1️⃣ StudentFragment.kt

Talabalar ro‘yxatini ekranda ko‘rsatadi.

Qidiruv (search) funksiyasini qo‘llab-quvvatlaydi.

Edit va delete tugmalari bilan interaktivlikni boshqaradi.

FloatingActionButton orqali yangi talaba qo‘shish ekraniga o‘tadi.

2️⃣ StudentViewModel.kt

Talabalar ma’lumotlarini repository orqali yuklaydi.

Qidiruv, qo‘shish, yangilash va o‘chirish funksiyalarini boshqaradi.

StateFlow orqali UI bilan ma’lumot almashadi.

3️⃣ StudentAdapter.kt

RecyclerView uchun adapter.

Har bir talaba itemini ko‘rsatadi: ism, fakultet, yo‘nalish.

onClick, onEditClick, onDeleteClick callback’larini qo‘llab-quvvatlaydi.

DiffUtil yordamida list yangilanishini optimallashtiradi.

4️⃣ AddStudentFragment.kt

Talaba qo‘shish va tahrirlash uchun UI.

Fakultetlar spinnersi orqali talaba fakultetini tanlash.

Yangi talaba qo‘shish yoki mavjudini yangilash logikasi bilan birlashtirilgan.

5️⃣ AddStudentViewModel.kt

Talaba qo‘shish/edit logikasini boshqaradi.

Fakultetlar oqimi (Spinner uchun) va tanlangan fakultetni saqlash.

CRUD amallarni repository orqali bajaradi (addStudent, updateStudent, deleteStudent, getStudentById).

6️⃣ SimpleStudent.kt

Talabaning soddalashtirilgan modeli.

UI va Adapter’lar uchun ishlatiladi.

Asosiy maydonlar: id, firstName, lastName, facultyName, direction.

🔹 Data Flow va MVVM Diagramma
┌───────────────────┐
│     Repository     │ <---> Database / Remote
└───────────────────┘
^
│
│ Flow / suspend functions
v
┌────────────────────┐
│  StudentViewModel   │ <-------------------┐
│  AddStudentViewModel│                     │
└────────────────────┘                     │
^                                │
│ StateFlow / LiveData           │
v                                │
┌────────────────────┐                     │
│  StudentFragment    │ <--- RecyclerView -┘
│  AddStudentFragment │       Adapter
└────────────────────┘


Repository – ma’lumotlarni (DB yoki API) olish va saqlash.

ViewModel – biznes logikasi, UI state va CRUD amallar.

Fragment – UI komponenti, ViewModel bilan bog‘lanadi.

Adapter – RecyclerView itemlarini render qiladi va click event’larni yuboradi.