home/ papkasi – README

Home modul foydalanuvchi tizimga kirgandan keyin ko‘rinadigan asosiy ekran va uning biznes logikasini boshqaradi. Bu modul quyidagi fayllarni o‘z ichiga oladi:

home/
├── HomeFragment.kt  # Home ekranining UI komponenti
└── HomeViewModel.kt # Home ekranining biznes logikasi va state

1️⃣ HomeFragment.kt

Vazifalari:

UI elementlarni boshqarish (BottomNavigation, Logout tugmasi).

NavController orqali fragmentlar o‘rtasida navigatsiya.

Logout dialogini ko‘rsatish.

Home ichidagi child nav graph bilan integratsiya.

Asosiy elementlar:

Element	Vazifasi
binding.logout	Logout tugmasi bosilganda dialog ko‘rsatadi
NavHostFragment	Home ichidagi ichki navigatsiyani boshqaradi
BottomNavigation	Fragmentlar o‘rtasida tab navigatsiya qiladi
showLogoutDialog()	Logout qilishni tasdiqlash uchun MaterialAlertDialogBuilder ishlatadi

Commit tarzida izohlangan qismlar:

binding.logout.setOnClickListener {
showLogoutDialog() // 🔹 Logout tugmasi bosilganda funksiya ishga tushadi
}

val navHostFragment = childFragmentManager
.findFragmentById(R.id.home_nav_host_fragment) as NavHostFragment

val navController = navHostFragment.navController
binding.bottomNavigation.setupWithNavController(navController)


setPopUpTo(R.id.main_nav_graph, inclusive = true) → LoginFragment va Home stackni tozalash, foydalanuvchi logout qilganda eski ekranlarga qaytmaslik.

2️⃣ HomeViewModel.kt

Vazifalari:

Logout amallarini bajaradi.

UI bilan hech qanday bog‘lanmasdan biznes logikani ajratadi.

fun logout() {
viewModelScope.launch(Dispatchers.IO) {
userRepository.logout() // 🔹 SharedPreferences orqali username/token tozalanadi
}
}


Izohlar:

viewModelScope.launch(Dispatchers.IO) → Disk/IO operatsiyalari uchun background thread ishlatiladi.

Repository ishlatiladi → Clean Architecture prinsipi bo‘yicha data qatlamini ViewModeldan ajratadi.

3️⃣ Home modul diagrammasi
+-------------------+
|   HomeFragment    |
|-------------------|
| - UI: BottomNav   |
| - Logout button   |
| - Child NavHost   |
+--------+----------+
|
v
+-------------------+
|  HomeViewModel    |
|-------------------|
| - logout()        |
| - viewModelScope  |
+--------+----------+
|
v
+-------------------+
| UserRepository    |
|-------------------|
| - logout()        |
+-------------------+
|
v
+-------------------+
| UserPreferences   |
|-------------------|
| - username/token  |
| - clearUser()     |
+-------------------+


Izoh:

HomeFragment UIni boshqaradi va foydalanuvchi interaktivligini qabul qiladi.

Logout tugmasi bosilganda HomeViewModel.logout() chaqiriladi.

ViewModel UserRepository orqali UserPreferences ni tozalaydi.

Shu bilan foydalanuvchi tizimdan chiqariladi va stack tozalanadi.