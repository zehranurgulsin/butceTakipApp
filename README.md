## 📱 Bütçe Takip Uygulaması

Kişisel gelir ve giderlerinizi kaydedebileceğiniz, istatistikleri görüntüleyebileceğiniz basit bir **Android** uygulamasıdır. Firebase entegrasyonu ile verilerinizi bulutta saklar.

---

###  Özellikler

* ✅ Gelir ve gider kayıtları ekleme
* 🔍 Kategori, tarih ve tutara göre filtreleme
* 🗕️ Tarih seçici (DatePicker) ile kolay tarih girişi
* 📊 İstatistik ekranı (gelir, gider, bakiye hesaplama)
* ☁️ Firebase Firestore ile veri saklama
* 🤝 Birinci ekrandan ikinci ekrana geçiş (analiz)
* 🔙 Geri dönme butonu ile ana sayfaya dönüş
* 🎨 Modern ve kullanıcı dostu arayüz tasarımı

---

### 🛠️ Kullanılan Teknolojiler

| Teknoloji                     | Açıklama                       |
| ----------------------------- | ------------------------------ |
| Java                          | Uygulama dili                  |
| Android Studio                | Geliştirme ortamı              |
| Firebase                      | Bulut veri saklama (Firestore) |
| XML                           | Arayüz tasarımı                |
| ConstraintLayout & ScrollView | Ekran düzenleri                |

---

### 🔧 Firebase Kurulumu

1. [Firebase Console](https://console.firebase.google.com) üzerinden yeni bir proje oluştur.
2. Android app ekle: `com.example.butcetakip` gibi.
3. `google-services.json` dosyasını `app/` klasörüne ekle.
4. `build.gradle (project)`:

   ```gradle
   classpath 'com.google.gms:google-services:4.3.15'
   ```
5. `build.gradle (app)`:

   ```gradle
   apply plugin: 'com.google.gms.google-services'
   implementation 'com.google.firebase:firebase-firestore:24.4.5'
   ```

---

### 📸 Ekran Görüntüleri
<img width="272" alt="image" src="https://github.com/user-attachments/assets/f05dc415-93ae-48fa-a277-e2b22ace708b" />


---

### 🚀 Nasıl Çalıştırılır?

1. Android Studio'da projeyi aç.
2. `google-services.json` dosyasını ekle.
3. Uygulamayı çalıştır (`Run > Run app`).
4. Firebase ile bağlantı kurduğunuzdan emin olun.

---

### ✍️ Geliştirici

* *zehranur gülşin
* **e-posta:** zehranurgulsin@gmail.com
* **GitHub:** https://github.com/zehranurgulsin

---

**Not:** Firebase ile bağlantı sağlamak için internete bağlı olmanız gerekir. Firebase'e kayıt edilen veriler çevrimdışı modda saklanmaz.
