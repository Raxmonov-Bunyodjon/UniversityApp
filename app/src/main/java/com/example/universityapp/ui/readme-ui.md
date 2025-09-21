UniversityApp UI Modullari – Umumiy README

Joylashuvi: ui/

Vazifasi:
UI papkasi ilovaning barcha foydalanuvchi interfeysi komponentlarini va ularning logikasi (ViewModel) hamda navigatsiyasini o‘z ichiga oladi. Har bir modul o‘ziga xos ekran va vazifalarni bajaradi.

Modullar va ularning vazifalari
1. auth/ – Autentifikatsiya

Login/Signup UI va foydalanuvchi kirish/ro‘yxatdan o‘tish logikasi.

Fayllar:

AuthState.kt – login/signup holatlarini ifodalovchi sealed class.

AuthViewModel.kt – autentifikatsiya logikasi, foydalanuvchi holatini boshqaradi.

LoginFragment.kt – login ekran UI.

SignupFragment.kt – ro‘yxatdan o‘tish UI.

SimpleTextWatcher.kt – EditText uchun maxsus TextWatcher.

2. common/ – Umumiy komponentlar

UI rejimlari va boshqa umumiy helper klasslar.

Fayllar:

UiMode.kt – UI rejimlarini boshqarish uchun enum/class.

3. home/ – Asosiy ekran

Foydalanuvchi tizimga kirgandan keyingi asosiy ekran.

Fayllar:

HomeFragment.kt – Home ekranining UI komponenti.

HomeViewModel.kt – Home ekranining biznes logikasi va state boshqaruvi.

4. main/ – Bosh modul
   a) faculty/addfaculty/ – Fakultetlar

Fakultetlar ro‘yxatini ko‘rsatish va qo‘shish UI.

Fayllar:

FacultyAdapter.kt – RecyclerView adapteri.

FacultyFragment.kt – Fakultetlar ro‘yxati UI.

FacultyViewModel.kt – Fakultetlar logikasi va state boshqaruvi.

AddFacultyFragment.kt – Yangi fakultet qo‘shish UI.

AddFacultyViewModel.kt – Qo‘shish logikasi.

b) student/addstudent/ – Talabalar

Talabalar ro‘yxati, qo‘shish, tahrirlash va o‘chirish UI.

Fayllar:

StudentAdapter.kt – Talabalar ro‘yxati adapteri.

StudentFragment.kt – Talabalar ro‘yxati UI.

StudentViewModel.kt – Talabalar logikasi.

AddStudentFragment.kt – Talaba qo‘shish UI.

AddStudentViewModel.kt – Qo‘shish logikasi.

MainActivity.kt – Asosiy activity, barcha fragmentlarni o‘z ichiga oladi.

5. splash/ – Splash ekran

Ilova ishga tushganda login tekshiradi va foydalanuvchini yo‘naltiradi.

Fayllar:

SplashFragment.kt – Splash ekran UI.

Modul bog‘liqlik diagrammasi
+-----------------+
|   SplashFragment|
+-----------------+
|
check currentUserFlow
|
+-----------+-----------+
|                       |
+------------+          +------------+
| LoginFragment |        | HomeFragment |
+------------+          +------------+
|
+-------------+------------------+
|                                |
+----------------+                 +----------------+
| FacultyModule  |                 | StudentModule  |
+----------------+                 +----------------+
| FacultyFragment|                 | StudentFragment|
| AddFacultyFrag |                 | AddStudentFrag|
+----------------+                 +----------------+
|
MainActivity

Umumiy tavsif

Navigatsiya:

NavController orqali fragmentlar orasida navigatsiya qilinadi.

MainActivity barcha main/ fragmentlarini boshqaradi.

ViewModel:

Har bir modul o‘z ViewModel orqali state va biznes logikasini boshqaradi.

Hilt yordamida dependency injection ishlatilgan.

RecyclerView Adapterlari:

FacultyAdapter va StudentAdapter ro‘yxatlarni ko‘rsatadi, item click/edit/delete funksiyalarini qo‘llab-quvvatlaydi.

Asosiy oqim:

SplashFragment → login holatini tekshiradi.

Agar foydalanuvchi login qilmagan bo‘lsa → LoginFragment.

Agar foydalanuvchi login qilingan bo‘lsa → HomeFragment.

Home ekranidan foydalanuvchi Faculty yoki Student modullariga kirishi mumkin.i (Activity, Fragment, Adapter, ViewModel) tartibli joylashtirish va boshqarish.