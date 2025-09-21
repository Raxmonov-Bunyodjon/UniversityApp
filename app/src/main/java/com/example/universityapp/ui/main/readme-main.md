UniversityApp – Main Module README
📂 Strukturasi
main/
├── faculty/
│   └── addfaculty/
│       ├── FacultyAdapter.kt       # Fakultetlar ro‘yxatini ko‘rsatish uchun adapter
│       ├── FacultyFragment.kt      # Fakultetlar UI komponenti
│       ├── FacultyViewModel.kt     # Fakultetlar logikasi va state boshqaruvi
│       └── readme-faculty.md      # Fakultet moduli hujjati
├── profile/
│   ├── ProfileFragment.kt          # Profil UI komponenti
│   ├── ProfileViewModel.kt         # Profil logikasi va state boshqaruvi
│   └── readme-profile.md           # Profil moduli hujjati
├── student/
│   └── addstudent/
│       ├── SimpleStudent.kt        # Soddalashtirilgan talaba model
│       ├── StudentAdapter.kt       # Talabalar ro‘yxatini ko‘rsatish uchun adapter
│       ├── StudentFragment.kt      # Talabalar UI komponenti
│       ├── StudentViewModel.kt     # Talabalar logikasi va state boshqaruvi
│       └── readme-student.md       # Talaba moduli hujjati

📌 Modul vazifalari
1️⃣ Faculty Moduli

FacultyFragment.kt – Fakultetlar ro‘yxatini UI orqali ko‘rsatadi.

FacultyAdapter.kt – RecyclerView orqali fakultetlarni list sifatida chiqaradi va har bir element uchun click, edit, delete funksiyalarini qo‘llaydi.

FacultyViewModel.kt – Fakultetlar ma’lumotlarini repository dan oladi, qidiruv, qo‘shish, yangilash va o‘chirish funksiyalarini bajaradi.

Bog‘liqligi – Talabalar moduli bilan fakultet ID orqali bog‘lanadi (har bir student fakultetga tegishli).

2️⃣ Profile Moduli

ProfileFragment.kt – Foydalanuvchi profilini ko‘rsatadi (ism, familiya, username, avatar).

ProfileViewModel.kt – Foydalanuvchi ma’lumotlarini repository orqali olib, avatarni yangilashni boshqaradi.

Bog‘liqligi – Umumiy ilova state bilan bog‘liq; login qilgan foydalanuvchi ma’lumotlarini ishlatadi.

3️⃣ Student Moduli

StudentFragment.kt – Talabalar ro‘yxatini UI orqali ko‘rsatadi, qidiruv, edit va delete funksiyalarini qo‘llaydi.

StudentAdapter.kt – RecyclerView adapteri; talabalar listini ko‘rsatadi.

StudentViewModel.kt – Talabalar ma’lumotlarini repository orqali boshqaradi, qidiruv, qo‘shish, yangilash va o‘chirish funksiyalarini bajaradi.

SimpleStudent.kt – Soddalashtirilgan talaba modeli (UI va listlarda ishlatish uchun).

Bog‘liqligi – Fakultet modulidan facultyId orqali bog‘lanadi; har bir talaba qaysi fakultetga tegishli ekanini ko‘rsatadi.

🔗 Modul bog‘liqligi diagrammasi
+------------------+
|  Faculty Module  |
|------------------|
| FacultyFragment  |
| FacultyAdapter   |
| FacultyViewModel |
+------------------+
^
| facultyId
|
+------------------+
|  Student Module  |
|------------------|
| StudentFragment  |
| StudentAdapter   |
| StudentViewModel |
| SimpleStudent    |
+------------------+

          +------------------+
          |  Profile Module  |
          |------------------|
          | ProfileFragment  |
          | ProfileViewModel |
          +------------------+

         (Ilova umumiy state orqali foydalanuvchi va login ma’lumotlari bilan bog‘langan)

⚡ Umumiy ishlash tartibi

Home / Main Screen:

Ilova ishga tushganda foydalanuvchi login holatini tekshiradi (AuthViewModel).

Agar login qilgan bo‘lsa, HomeFragment ochiladi, aks holda LoginFragment.

Fakultetlar bilan ishlash:

FacultyFragment ro‘yxatni ko‘rsatadi va foydalanuvchi qo‘shish, tahrirlash yoki o‘chirish mumkin.

FacultyViewModel repository orqali barcha CRUD operatsiyalarni bajaradi.

Talabalar bilan ishlash:

StudentFragment talabalar ro‘yxatini ko‘rsatadi.

Har bir talaba facultyId orqali tegishli fakultet bilan bog‘langan.

StudentViewModel barcha CRUD va qidiruv funksiyalarini boshqaradi.

Profil bilan ishlash:

ProfileFragment foydalanuvchi ma’lumotlarini ko‘rsatadi va avatarni o‘zgartirish imkonini beradi.

ProfileViewModel repository orqali foydalanuvchi ma’lumotlarini yangilaydi.