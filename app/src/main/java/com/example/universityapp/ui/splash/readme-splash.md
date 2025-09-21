SplashFragment Moduli

Joylashuvi: ui.splash.SplashFragment.kt

Vazifasi:
Ilova ishga tushganda foydalanuvchi login qilgan yoki qilinmaganini aniqlaydi va tegishli ekranga yo‘naltiradi:

Agar foydalanuvchi login qilmagan bo‘lsa → LoginFragment

Agar foydalanuvchi login qilingan bo‘lsa → HomeFragment

Bog‘liqlik:

AuthViewModel → foydalanuvchi login holatini tekshiradi (currentUserFlow).

NavController → navgraf orqali ekranlar orasida navigatsiya qiladi.

Funksionallik bosqichlari

Fragment yaratish (onCreateView)

fragment_splash.xml layoutini inflate qiladi.

Login holatini tekshirish (onViewCreated)

AuthViewModel dan currentUserFlow ni collect qiladi.

Agar username bo‘sh bo‘lsa → LoginFragment ga yo‘naltirish.

Aks holda → HomeFragment ga yo‘naltirish.

Navigation

findNavController().navigate() orqali tegishli fragmentga o‘tish.

Diagramma
+----------------+
| SplashFragment |
+----------------+
|
v
Collect currentUserFlow (AuthViewModel)
|
+-------+--------+
|                |
username.isBlank()     username mavjud
|                |
v                v
+----------------+  +----------------+
| LoginFragment  |  | HomeFragment   |
+----------------+  +----------------+


Izoh:

SplashFragment o‘zi UI komponent emas, faqat boshlang‘ich yo‘naltirish vazifasini bajaradi.

Foydalanuvchi login holati AuthViewModel orqali oqim sifatida olinadi.