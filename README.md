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

# 🖥️ Penjelasan Kode - `src/`

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

### 🧩 Struktur Data yang Digunakan

- `LinkedList`: Menyimpan daftar pasien (`patientList`) dan dokter (`doctorList`).
- `BST` (Binary Search Tree): Menyimpan data pasien berdasarkan ID untuk pencarian yang lebih cepat.
- `AppointmentMap`: Mengelola penjadwalan dan pemrosesan janji temu pasien berdasarkan dokter.


Blok kode ini merupakan pusat pengontrol dari seluruh fitur sistem klinik dan mengatur bagaimana interaksi antar struktur data berlangsung melalui antarmuka terminal.
