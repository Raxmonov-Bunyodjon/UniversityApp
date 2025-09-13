# DI (Dependency Injection) moduli

Ushbu modul orqali **Hilt** yordamida:

- `AppDatabase` (Room) yaratiladi va `FacultyDao`, `StudentDao` taqdim etiladi.
- `UniversityRepository` singleton sifatida taqdim etiladi.

## Fayllar

- `DatabaseModule.kt` → Room database va DAO providerlari
- `RepositoryModule.kt` → Repository providerlari
