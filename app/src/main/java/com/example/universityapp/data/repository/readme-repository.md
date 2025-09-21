# Repository Layer

`repository/` papka ilovaning **Repository qatlamini** tashkil qiladi.  
Repositorylar **Domain qatlamini Data qatlamidan ajratadi** va business logic yoki UI qatlamini DB/DAO yoki DataStore bilan bevosita bog‘lamaydi.

---

## Tuzilma

repository/
├── FacultyRepositoryImpl.kt # Fakultet ma'lumotlarini boshqarish
├── StudentRepositoryImpl.kt # Talabalar bilan ishlash logikasi
├── UniversityRepositoryImpl.kt # Universitetga oid CRUD operatsiyalar
├── UserRepositoryImpl.kt # Foydalanuvchi ma'lumotlari bilan ishlash



---

## Diagramma: Repository va bog‘lanishlari

```text
┌─────────────┐        ┌─────────────┐        ┌─────────────┐
│ FacultyDao  │ <----> │ FacultyRepo │ <----> │  Domain/UI  │
└─────────────┘        └─────────────┘        └─────────────┘

┌─────────────┐        ┌─────────────┐        ┌─────────────┐
│ StudentDao  │ <----> │ StudentRepo │ <----> │  Domain/UI  │
└─────────────┘        └─────────────┘        └─────────────┘
           │
           │
           v
┌────────────────────┐
│ UniversityRepo     │  <-- barcha CRUD operatsiyalar
└────────────────────┘

┌─────────────┐        ┌─────────────┐        ┌─────────────┐
│ UserDao     │ <----> │ UserRepo    │ <----> │  Domain/UI  │
│ UserPrefs   │ <----> │             │
└─────────────┘        └─────────────┘
Repositorylar va vazifalari
1. FacultyRepositoryImpl.kt
Vazifasi: Fakultetlar bilan ishlash (DB Entity ↔ Domain Model)

Metodlar:

getAllFaculties() — barcha fakultetlarni Flow orqali olish

getFacultyById(id) — id bo‘yicha bitta fakultet olish

insertFaculty(faculty) — yangi fakultet qo‘shish

updateFaculty(faculty) — mavjud fakultetni yangilash

deleteFaculty(id) — fakultetni o‘chirish

2. StudentRepositoryImpl.kt
Vazifasi: Talabalar bilan ishlash logikasi

Metodlar:

getAllStudents() — barcha talabalarni olish

getStudentsByFaculty(facultyId) — fakultet bo‘yicha talabalarni olish

insertStudent(student) — yangi talaba qo‘shish

updateStudent(student) — talaba ma’lumotini yangilash

deleteStudent(studentId) — talaba o‘chirish

Izoh: Mapper (toDomain(), toEntity()) yordamida Entity ↔ Domain konvertatsiyasi amalga oshiriladi.

3. UniversityRepositoryImpl.kt
Vazifasi: Universitetga oid barcha CRUD operatsiyalarni yagona repositoryda jamlash

Metodlar:

Fakultetlar bilan ishlash: addFaculty(), getFaculties(), deleteFaculty()

Talabalar bilan ishlash: addStudent(), getStudents(), searchStudents(), deleteStudent(), updateStudent()

Talabalar JOIN query bilan: getStudentsWithFaculty(), getStudentWithFacultyById()

Foydalanuvchilar bilan ishlash: registerUser(), getUserByUsernameAndPassword(), updateUserAvatar(), deleteUser()

Izoh: Bu repository kattaroq va umumiy CRUD operatsiyalarni birlashtiradi, shuning uchun kichik repositorylardan farq qiladi.

4. UserRepositoryImpl.kt
Vazifasi: Foydalanuvchi ma’lumotlari bilan ishlash

Metodlar:

getUsers() — barcha foydalanuvchilarni olish

getUserByUsernameAndPassword() — login uchun

getUserByUsername() — signup / tekshiruv uchun

insertUser(user) — yangi foydalanuvchi qo‘shish

deleteUser(user) — foydalanuvchi o‘chirish

signInUser(username) — DataStore orqali username saqlash

logout() — DataStore ma’lumotlarini tozalash

updateUserAvatar(username, avatar) — foydalanuvchi avatarini yangilash

Izoh: DAO + DataStore integratsiyasi yordamida foydalanuvchi session va profiling boshqariladi.

🔑 Muhim tushuntirishlar
Repositorylar Domain va Data qatlamlarni ajratadi.

Flow ishlatilgan metodlar UI ni real vaqt rejimida yangilashga yordam beradi.

Mapperlar yordamida Entity ↔ Domain ↔ UI konvertatsiyasi amalga oshiriladi.

Har bir repository faqat o‘zining mas’uliyatiga tegishli metodlarni o‘z ichiga oladi.

Katta repository (UniversityRepositoryImpl) kichik repositorylar vazifalarini yagona joyda jamlaydi.