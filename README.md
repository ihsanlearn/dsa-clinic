# 🏥 Daisuke Clinic - Java CLI App

**Daisuke Clinic** adalah aplikasi Command Line Interface (CLI) berbasis Java yang dibuat untuk mensimulasikan sistem manajemen klinik sederhana. Semua data dikelola menggunakan struktur data buatan sendiri tanpa menggunakan koleksi bawaan Java seperti `ArrayList`, `Queue`, dan `Tree`.

---

## 🧠 Fitur Utama

- ✅ Tambah, lihat, edit, dan hapus data **pasien**.
- ✅ Manajemen data **dokter** menggunakan struktur data **Linked List**.
- ✅ Penjadwalan dan antrian **janji temu** menggunakan **Queue**.
- ✅ Penyimpanan data **dokter** juga tersedia dalam bentuk **BST (Binary Search Tree)**.
- ✅ Struktur **Stack** disediakan untuk fitur undo/redo atau kebutuhan lainnya.
- ✅ Penyimpanan data menggunakan file `.txt` (tanpa database).
- ✅ Interaksi penuh lewat terminal (CLI).

---

## 📂 Struktur Folder

DaisukeClinic/
├── .vscode/ # Pengaturan editor (optional)
├── bin/ # Folder hasil kompilasi
├── lib/ # (Kosong / Optional)
├── src/ # Source code utama
│ ├── Appointment.java # Kelas janji temu
│ ├── BST.java # Binary Search Tree untuk data dokter
│ ├── Doctor.java # Model data dokter
│ ├── DoctorLinkedList.java # Linked List khusus dokter
│ ├── LinkedList.java # Struktur data linked list umum
│ ├── Main.java # Program utama (titik masuk)
│ ├── Patient.java # Model data pasien
│ ├── Queue.java # Struktur antrian janji temu
│ └── Stack.java # Struktur stack (opsional)
└── README.md # Dokumentasi proyek ini


---

## 🚀 Cara Menjalankan

### 1. Kompilasi

Masuk ke folder `src`:

```bash
cd src
javac *.java

javac -d ../bin *.java # kompilasi masuk ke file bin
cd ../bin
java Main # jalankan code yang sudah dikompilasi ke bin



