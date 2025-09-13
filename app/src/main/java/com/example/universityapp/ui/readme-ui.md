# 📂 ui/ (UI Layer)

📌 Bu papka **foydalanuvchi interfeysi** bilan ishlash uchun.  
Har bir ekran yoki fragment uchun alohida package ochiladi.

- **auth/** → Login, Signup fragmentlari + AuthViewModel.
- **main/** → MainActivity va uning ichidagi fragmentlar:
    - **faculty/** → Fakultetlar ro‘yxati (Fragment, Adapter, ViewModel).
    - **student/** → Talabalar ro‘yxati (Fragment, Adapter, ViewModel).
    - **profile/** → Foydalanuvchi profili (Fragment, ViewModel).

⚡ Maqsad:  
UI komponentlarini (Activity, Fragment, Adapter, ViewModel) tartibli joylashtirish va boshqarish.