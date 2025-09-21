📦 di/ — Dependency Injection (Hilt) modullari

di/ papkasi Dagger Hilt orqali ilovada dependency injection (DI) konfiguratsiyasini saqlaydi. Bu papka orqali Room database, SharedPreferences va Repositorylar Hilt yordamida injekt qilinadi.

📁 Fayllar tuzilishi
di/
├── DatabaseModule.kt       # Room DB uchun DI konfiguratsiyasi
├── PreferencesModule.kt    # SharedPreferences uchun DI moduli
├── RepositoryModule.kt     # Repositorylar uchun DI bindinglar

🔹 1. DatabaseModule.kt

Ma’qsad: Room database (AppDatabase) va barcha DAOlarni singleton sifatida Hilt orqali injekt qilish.

Asosiy funksiya va izohlar:

@Provides
@Singleton
fun provideDatabase(@ApplicationContext context: Context): AppDatabase


AppDatabase singleton yaratadi va ilova davomida bir marta ishlaydi.

@Provides
fun provideUserDao(db: AppDatabase): UserDao
@Provides
fun provideStudentDao(db: AppDatabase): StudentDao
@Provides
fun provideFacultyDao(db: AppDatabase): FacultyDao


DAOlarni Hilt orqali injekt qiladi.

Diagramma:

AppDatabase
├── UserDao
├── StudentDao
└── FacultyDao

🔹 2. PreferencesModule.kt

Ma’qsad: SharedPreferences wrapper (UserPreferences) ni singleton sifatida Hilt orqali injekt qilish.

Asosiy funksiya va izohlar:

@Provides
@Singleton
fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences


UserPreferences singleton yaratadi va ilova davomida bir marta ishlaydi.

Diagramma:

UserPreferences
└── context (ApplicationContext)

🔹 3. RepositoryModule.kt

Ma’qsad: Repository interfeyslarini ularning implementatsiyalari bilan bog‘lash (@Binds) va singleton sifatida Hilt orqali injekt qilish.

Asosiy funksiya va izohlar:

@Binds
@Singleton
abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

@Binds
@Singleton
abstract fun bindFacultyRepository(impl: FacultyRepositoryImpl): FacultyRepository

@Binds
@Singleton
abstract fun bindStudentRepository(impl: StudentRepositoryImpl): StudentRepository


Interfeyslarni implementatsiyalar bilan bog‘laydi, Hilt esa repositoryni injekt qiladi.

Diagramma:

UserRepository <—— UserRepositoryImpl
FacultyRepository <—— FacultyRepositoryImpl
StudentRepository <—— StudentRepositoryImpl

🔹 DI umumiy oqimi (Database + Preferences + Repository)
[ApplicationContext]
│
▼
DatabaseModule
│
├── AppDatabase
│     ├── UserDao
│     ├── StudentDao
│     └── FacultyDao
│
PreferencesModule
│
└── UserPreferences
│
RepositoryModule
│
├── UserRepositoryImpl  <—— UserRepository
├── FacultyRepositoryImpl <—— FacultyRepository
└── StudentRepositoryImpl <—— StudentRepository


✅ Shu diagramma orqali Hilt dependency injection oqimi to‘liq tushunarli bo‘ladi:

ViewModel yoki boshqa classlarda interfeyslar orqali repositorylar ishlatiladi.

Hilt @Provides va @Binds yordamida singleton obyektlarni yaratadi va injekt qiladi.

🔹 Asosiy izohlar

@Module — Hilt modul ekanini bildiradi.

@InstallIn(SingletonComponent::class) — modul app darajasida singleton bo‘ladi.

@Provides — funksiya orqali obyekt yaratish va Hilt’ga berish.

@Binds — interfeysni uning implementatsiyasi bilan bog‘lash.

Singleton — obyekt bir marta yaratiladi va ilova davomida ishlatiladi.