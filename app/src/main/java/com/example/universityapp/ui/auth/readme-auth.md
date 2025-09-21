auth/ Papka README
📦 Strukturasi
auth/
├── AuthState.kt          # UI holatlarini ifodalovchi sealed class
├── AuthViewModel.kt      # Login va Signup logikasini boshqaruvchi ViewModel
├── LoginFragment.kt      # Login UI va interaktivlik
├── SignupFragment.kt     # Ro‘yxatdan o‘tish UI va interaktivlik
└── SimpleTextWatcher.kt  # EditText uchun soddalashtirilgan TextWatcher utility

🔹 Fayllar tavsifi
1️⃣ AuthState.kt

Ma’no: UI holatlarini ifodalaydi.

Tarkibi:

sealed class AuthState {
object Idle : AuthState()                     // Boshlang'ich holat
data class Success(val message: String) : AuthState()  // Muvaffaqiyat holati
data class Error(val message: String) : AuthState()    // Xatolik holati
}


Izoh:
UI StateFlow<AuthState> yoki LiveData<AuthState> orqali bu holatlarni kuzatadi va mos ravishda foydalanuvchiga xabar beradi (Snackbar, BottomSheet, progress).

2️⃣ AuthViewModel.kt

Ma’no: Login va Signup logikasini boshqaradi.

Asosiy vazifalar:

login(username, password) — foydalanuvchini tekshiradi va AuthState emit qiladi.

signup(firstName, lastName, username, password) — yangi foydalanuvchi qo‘shadi va AuthState emit qiladi.

currentUserFlow — hozirgi login foydalanuvchi username’ini kuzatadi.

Flow diagram:

[AuthViewModel]
|
| -- login() / signup()
v
[UserRepository]  -- getUserByUsernameAndPassword(), insertUser()
|
v
[UserDao / UserPreferences]  -- DB va SharedPreferences

3️⃣ LoginFragment.kt

Ma’no: Login UI va interaktivlikni boshqaradi.

Asosiy vazifalar:

EditText dan username va password olish.

Button bosilishi → viewModel.login().

ProgressBar va BottomSheet orqali feedback berish.

Login muvaffaqiyatli bo‘lsa HomeFragment ga navigatsiya.

Ishlash diagrammasi:

[LoginFragment UI]
|
| (login button clicked)
v
[AuthViewModel.login()]
|
v
[UserRepository.getUserByUsernameAndPassword()]
|
v
[UserDao -> SQLite / UserPreferences]
|
v
[AuthState Success/Error] -> [LoginFragment UI]

4️⃣ SignupFragment.kt

Ma’no: Ro‘yxatdan o‘tish UI va interaktivlik.

Asosiy vazifalar:

Input validatsiyasi (SimpleTextWatcher)

Button bosilishi → viewModel.signup()

Success → Login ekraniga qaytish

Error → Snackbar bilan xabar

Ishlash diagrammasi:

[SignupFragment UI]
|
| (signup button clicked)
v
[AuthViewModel.signup()]
|
v
[UserRepository.insertUser()]
|
v
[UserDao -> SQLite]
|
v
[AuthState Success/Error] -> [SignupFragment UI]

5️⃣ SimpleTextWatcher.kt

Ma’no: EditText uchun soddalashtirilgan TextWatcher.

Foydasi: har bir EditText uchun alohida TextWatcher yozmaslik, faqat onTextChanged callback bilan ishlash.

class SimpleTextWatcher(private val onTextChanged: (String?) -> Unit) : TextWatcher {
override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
onTextChanged(s?.toString())
}
override fun afterTextChanged(s: Editable?) {}
}

🔹 Yagona diagramma (auth qatlam umumiy bog‘lanishi)
[LoginFragment / SignupFragment]
|
| calls login() / signup()
v
[AuthViewModel]  
|
| uses
v
[UserRepository]
|
| interacts with
v
[UserDao / UserPreferences]
|
v
SQLite DB / DataStore


Izoh:

UI → ViewModel → Repository → Local DB / Preferences

AuthState orqali holatlar UI ga qaytariladi.