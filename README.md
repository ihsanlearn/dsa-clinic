# Clinic Sehat Tentram Sejahtera Indonesia Raya

## Daftar Isi

- [Kelompok](#kelompok)
- [Struktur Folder](#struktur-folder)

## 👨🏻‍💻 Kelompok

1. Faris Syahrul Muhammad
2. Ihsan Restu Adi
3. Andika Bahari
4. Arul


## 📂 Struktur Folder

```
dsa-clinic/
├── .vscode/                
│   → Pengaturan editor (opsional)
├── lib/                    
│   → Kosong / opsional
├── assets/                    
│   → gambar untuk keperluan markdown file
├── src/ 
│   ├── models/    
│   │   ├── Doctor.java              
│   │   ├── Appointment.java    
│   │   └── Patient.java    
│   ├── structures/    
│   │   ├── AppointmentMap.java              
│   │   ├── BST.java    
│   │   ├── LinkedList.java       
│   │   ├── Node.java       
│   │   └── PriorityQueue.java       
│   ├── utils/    
│   │   ├── FileManager.java              
│   │   └── IdGenerator.java                   
│   └── Main.java 
├── .gitignore              
└── README.md                  
```

# 🧩 Penjelasan Kode - `src/`

## `Main.java`
<p align="center">
  <img src="assets/Main.png" width="600"/>
  <br/>
</p>


File `Main.java` adalah titik masuk utama (entry point) dari aplikasi **Daisuke Clinic**, sebuah sistem manajemen klinik berbasis Command Line Interface (CLI) yang dibuat dengan Java. File ini bertanggung jawab atas alur utama program dan interaksi pengguna melalui menu terminal.

### 📌 Ringkasan Fungsi

- **Load Data Awal:** Mengambil data dari file menggunakan `FileManager.loadAll()` ke dalam struktur data internal seperti `LinkedList`, `BST`, dan `AppointmentMap`.
- **Menu Utama:** Menampilkan menu pilihan kepada pengguna menggunakan sistem perulangan `do-while`.
- **Pemrosesan Input:** Menerima dan memproses pilihan pengguna menggunakan struktur `switch-case` modern (Java 14+).
- **Fungsi Klinik:** Menyediakan berbagai fitur untuk mengelola data pasien, login/logout dokter, jadwal janji temu, dan operasi dengan pohon BST.
- **Simpan Data Akhir:** Menyimpan seluruh data kembali ke file dengan `FileManager.saveAll()` saat pengguna memilih keluar (`choice == 0`).

### 📌 Struktur Data yang Digunakan

- `LinkedList`: Menyimpan daftar pasien (`patientList`) dan dokter (`doctorList`).
- `BST` (Binary Search Tree): Menyimpan data pasien berdasarkan ID untuk pencarian yang lebih cepat.
- `AppointmentMap`: Mengelola penjadwalan dan pemrosesan janji temu pasien berdasarkan dokter.


Blok kode ini merupakan pusat pengontrol dari seluruh fitur sistem klinik dan mengatur bagaimana interaksi antar struktur data berlangsung melalui antarmuka terminal.

---

# 🧩 Penjelasan Kode - `src/models/`

Folder `models/` berisi **representasi data utama** dalam sistem Daisuke Clinic. File-file di dalam folder ini mendefinisikan struktur dan perilaku objek penting seperti pasien, dokter, dan janji temu. Kelas-kelas ini digunakan di berbagai struktur data (`LinkedList`, `BST`, `AppointmentMap`) untuk menyimpan dan memanipulasi data secara efisien.

## 📄 Isi File di `models/`

### 👤 `Patient.java`
Kelas ini merepresentasikan **data pasien** dalam sistem.

#### Atribut umum:
- `id` – ID pasien (unik)
- `name` – Nama pasien
- `age` – Umur pasien
- `disease` – Jenis penyakit atau keluhan

#### Fitur:
- Constructor untuk inisialisasi objek
- Getter dan setter untuk manipulasi data
- `toString()` untuk menampilkan data pasien dengan format yang rapi

---

### 🩺 `Doctor.java`
Kelas ini menyimpan data dan perilaku **dokter** yang tersedia di sistem.

#### Atribut umum:
- `id` – ID dokter
- `name` – Nama dokter
- `specialty` – Spesialisasi medis
- `password` – Kata sandi untuk login

#### Fitur:
- Login dan logout dengan autentikasi sederhana
- Penyimpanan status login terakhir
- `toString()` untuk menampilkan data dokter

---

### 📅 `Appointment.java`
Kelas ini merepresentasikan **janji temu** antara pasien dan dokter.

#### Atribut umum:
- `patient` – Objek pasien yang membuat janji
- `doctor` – Objek dokter tujuan
- `date` – Tanggal janji temu (biasanya berupa string atau objek tanggal)

#### Fitur:
- Constructor untuk membuat janji temu baru
- Akses data pasien & dokter yang terkait
- Digunakan dalam `AppointmentMap` untuk pengelolaan antrian janji temu per dokter

---

Kelas-kelas dalam `models/` ini adalah pondasi utama dari data yang dikelola sistem. Struktur mereka sederhana namun cukup fleksibel untuk diperluas jika proyek berkembang di masa depan.
