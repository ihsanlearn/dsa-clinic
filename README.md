# 🏥 Daisuke Clinic - Java CLI App

**Daisuke Clinic** adalah aplikasi Command Line Interface (CLI) berbasis Java yang dibuat untuk mensimulasikan sistem manajemen klinik sederhana. Semua data dikelola menggunakan struktur data buatan sendiri tanpa menggunakan koleksi bawaan Java seperti `ArrayList`, `Queue`, dan `Tree`.

---

## 🧠 Fitur Utama

- ✅ Tambah, lihat, dan hapus data **pasien**.
- ✅ Manajemen data **dokter** menggunakan struktur data **Linked List**.
- ✅ Penjadwalan dan antrian **janji temu** menggunakan **Queue**.
- ✅ Penyimpanan data **dokter** juga tersedia dalam bentuk **BST (Binary Search Tree)**.
- ✅ Interaksi penuh lewat terminal (CLI).

---

## 📂 Struktur Folder


## 📂 Struktur Folder

```
dsa-clinic/
├── .vscode/                
│   → Pengaturan editor (opsional)
├── bin/                    
│   → Folder hasil kompilasi
├── lib/                    
│   → Kosong / opsional
├── src/                    
│   → Source code utama
│   ├── Appointment.java         
│   ├── BST.java                 
│   ├── Doctor.java              
│   ├── DoctorLinkedList.java    
│   ├── LinkedList.java          
│   ├── Main.java                
│   ├── Patient.java             
│   ├── Queue.java               
│   └── Stack.java               
└── README.md                  
    → Dokumentasi proyek ini
```


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



