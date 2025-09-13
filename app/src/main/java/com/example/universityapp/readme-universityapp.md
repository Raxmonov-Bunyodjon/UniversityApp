📂 com.example.universityapp (Root Package)

📌 Bu loyiha ichidagi asosiy package hisoblanadi.
U ichida barcha arxitektura qatlamlari (layers) joylashgan bo‘ladi:

📦 Papkalar tarkibi

di/
🔹 Dependency Injection uchun.
Bu yerda Hilt modullari joylashadi:
DatabaseModule
RepositoryModule
NetworkModule

data/
🔹 Ma’lumotlar qatlami.
local/ → Room (Entity, DAO, Database).
repository/ → Repository (ma’lumotni olish va ViewModel’ga yetkazish).

domain/
🔹 Biznes qoidalar qatlami.
Model (asosiy obyektlar).
DTO (data transfer obyektlar).
UseCase (ish jarayonlari va biznes qoidalar).

ui/
🔹 Foydalanuvchi interfeysi.
Activity, Fragment
Adapter, ViewModel
Har bir ekran uchun alohida package.

utils/
🔹 Umumiy yordamchi klasslar va funksiyalar.
Masalan: Extensions, Constants, Validators, Resource.

⚡ Maqsad
Loyiha arxitekturasini Clean Architecture asosida tartibga solish.
Har bir qatlamni o‘z vazifasiga ajratish.
Kodni boshqarishni osonlashtirish va kelajakda kengaytirishga tayyorlash.
Loyihani tartibli va tushunarli qilish.