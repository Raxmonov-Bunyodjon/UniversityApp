# Mapper Layer

`mapper/` papka ilovaning **Mapper qatlamini** tashkil qiladi.  
Mapperlar **Entity ↔ Domain ↔ UI** o‘rtasida konvertatsiya qilish uchun ishlatiladi.  
Bu qatlam orqali **qatlamlar orasidagi bog‘lanish kamayadi** va Clean Architecture printsiplari qo‘llaniladi.

---

## Diagramma: Mapper va bog‘lanishlari

```text
┌─────────────────────┐       ┌───────────────────┐       ┌────────────────────┐
│   FacultyEntity     │  <-->  │   FacultyMapper    │  <--> │     Faculty        │
└─────────────────────┘       └───────────────────┘       └────────────────────┘

┌─────────────────────┐       ┌─────────────────────┐       ┌────────────────────┐
│   StudentEntity     │  <-->  │   StudentMapper     │  <--> │     Student        │
└─────────────────────┘       └─────────────────────┘       └────────────────────┘
                                   │
                                   │
                                   v
                         ┌────────────────────┐
                         │   StudentWithFaculty│
                         └────────────────────┘
                                   │
                                   v
                         ┌────────────────────┐
                         │   SimpleStudent     │
                         │    (UI DTO)         │
                         └────────────────────┘
Mapper fayllari va vazifalari
1. FacultyMapper.kt
Room Entity (FacultyEntity) ↔ Domain Model (Faculty) konvertatsiyasi.

Funksiyalar:

FacultyEntity.toDomain() → Domain modelga o‘tkazadi.

Faculty.toEntity() → DB Entity ga o‘tkazadi.

Maqsad: DAO orqali olingan ma’lumotlarni domain qatlamida ishlatish.

2. StudentMapper.kt
Talaba bilan bog‘liq Entity, Domain va UI modellari o‘rtasida konvertatsiya.

Funksiyalar:

StudentEntity.toDomain() → DB Entity → Domain model (facultyName null)

Student.toEntity() → Domain model → DB Entity

StudentWithFaculty.toDomain() → JOIN natijasini Domain modelga o‘tkazish

StudentWithFaculty.toSimpleStudent() → JOIN natijasini UI DTO ga aylantirish

Maqsad: Domain va UI qatlamini DB qatlamidan mustaqil qilish.

🔑 Muhim tushuntirishlar
Mapperlar qatlamlar orasidagi bog‘lanishni kamaytiradi.

Clean Architecture va Layered Architecture prinsiplari qo‘llanadi.

JOIN natijalarini UI uchun yengil DTO (SimpleStudent) ga aylantirish mumkin.

Har bir Entity yoki Domain model qo‘shilganda mapper funksiyasini ham yaratish tavsiya qilinadi.