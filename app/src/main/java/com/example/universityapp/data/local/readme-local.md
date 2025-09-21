# Local Data Layer (Room & DataStore)

Bu papka (`local/`) ilovaning **Local Data Layer** qismini tashkil qiladi.  
U Room Database va DataStore orqali ma’lumotlarni saqlash, olish va yangilash vazifasini bajaradi.

---

## Diagramma: fayllar va bog‘lanishlari

```text
                       ┌────────────────────┐
                       │     AppDatabase    │
                       │------------------│
                       │ userDao()          │
                       │ studentDao()       │
                       │ facultyDao()       │
                       └─────────┬─────────┘
                                 │
        ┌─────────────┬──────────┴───────────┬─────────────┐
        │             │                      │             │
 ┌─────────────┐ ┌─────────────┐       ┌─────────────┐ ┌─────────────┐
 │  UserDao    │ │ StudentDao  │       │ FacultyDao  │ │ UserPreferences │
 └─────────────┘ └─────────────┘       └─────────────┘ └─────────────┘
        │             │                      │
        │             │                      │
┌─────────────┐ ┌─────────────┐       ┌─────────────┐
│ UserEntity  │ │ StudentEntity│       │ FacultyEntity│
└─────────────┘ └─────────────┘       └─────────────┘
        │
        │
┌────────────────────┐
│ StudentWithFaculty │
└────────────────────┘
Fayllar va vazifalari
1. AppDatabase.kt
Room Database klassi, barcha DAO larni birlashtiradi.

Maqsad: DB bilan ishlashni markazlashtirish.

2. FacultyEntity.kt & FacultyDao.kt
FacultyEntity → faculties jadvali.

FacultyDao → CRUD amallar:

insert, update, delete, getAll, getById

Maqsad: Fakultet ma’lumotlarini saqlash va olish.

3. StudentEntity.kt & StudentDao.kt
StudentEntity → students jadvali.

StudentDao → CRUD, qidiruv, JOIN querylar:

getAllStudents(), getStudentsByFaculty(), searchStudents()

getStudentsWithFaculty(), getStudentWithFacultyById()

Maqsad: Talaba ma’lumotlarini saqlash, qidirish va fakultet bilan birlashtirish.

4. StudentWithFaculty.kt
DTO klassi, JOIN query natijasini saqlaydi:

id, firstName, lastName, facultyId, facultyName, direction, avatar

Maqsad: Talaba + fakultet ma’lumotini birlashtirib UI yoki domain layerga uzatish.

5. UserEntity.kt & UserDao.kt
UserEntity → users jadvali.

UserDao → CRUD va login operatsiyalari:

insertUser(), deleteUser(), getUserByUsernameAndPassword()

getUserByUsername(), getAllUsers(), updateAvatar()

Maqsad: Foydalanuvchi ma’lumotlarini boshqarish.

6. UserPreferences.kt
DataStore orqali session va preference saqlash:

saveUsername(), userUsernameFlow, clearUser()

Maqsad: Login session va logout operatsiyalarini boshqarish.

🔑 Muhim tushuntirishlar
Entity → DAO → Repository → Domain oqimi saqlanadi.

Flow va suspend funksiyalari real vaqt yangilanish va korutinalar bilan ishlash imkonini beradi.

Mapperlar (ayrim paketda) orqali Entity ↔ Domain ↔ UI konvertatsiyasi amalga oshiriladi.

UserPreferences → DataStore yordamida session va preferences saqlanadi.

Tavsiya
Har bir DAO va Entity ishlatilganda Flow va suspend funksiyalarini to‘g‘ri ishlatish, shunda UI real vaqt rejimida yangilanadi.

Mapperlar yordamida qatlamlar orasidagi bog‘lanishni kamaytirish.
