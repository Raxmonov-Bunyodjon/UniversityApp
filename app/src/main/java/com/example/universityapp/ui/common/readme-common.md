
📦 Fayl struktura
common/
├── UiMode.kt    # Umumiy ekranda ishlash rejimini bildiruvchi sealed class

🔹 Tavsif

UiMode sealed class UI ekranlarida obyekt qo‘shish yoki tahrirlash rejimini aniqlash uchun ishlatiladi.
Bu pattern yordamida bir xil fragment yoki activity turli rejimlarda ishlay oladi: yangi obyekt qo‘shish yoki mavjudini tahrirlash.

🔹 Tarkibi
sealed class UiMode {
object Add : UiMode()            // Yangi obyekt qo‘shish rejimi
data class Edit(val id: Long) : UiMode()  // Mavjud obyektni tahrirlash rejimi, id orqali aniqlanadi
}


Add

Parametrsiz

UI yangi obyekt qo‘shish uchun tayyorlanadi (formalar bo‘sh bo‘ladi)

Edit

id: Long parametr bilan

Berilgan ID ga mos obyekt DB dan o‘qiladi va UI formalarini to‘ldiradi

🔹 Foydalanish misollari
1️⃣ Yangi obyekt qo‘shish
val mode: UiMode = UiMode.Add

when(mode) {
is UiMode.Add -> {
// Formalarni bo‘sh holatda ko‘rsatish
}
is UiMode.Edit -> {
// Ma’lumotlarni DB dan olib, formaga joylash
}
}

2️⃣ Mavjud obyektni tahrirlash
val mode: UiMode = UiMode.Edit(id = 42)

when(mode) {
is UiMode.Add -> { /* ... */ }
is UiMode.Edit -> {
val objectId = mode.id
// DB dan objectId orqali ma’lumotlarni olish va formaga joylash
}
}

🔹 Diagramma (UI rejimlari)
[UiMode]
/   \
[Add]   [Edit]
|
v
id: Long
|
v
UI formalarini to‘ldirish


Izoh:

Add → yangi ma’lumot qo‘shish

Edit → mavjud ma’lumotni tahrirlash, id orqali obyekt aniqlanadi