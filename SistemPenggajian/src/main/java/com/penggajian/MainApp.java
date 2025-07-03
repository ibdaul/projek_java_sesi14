package main.java.com.penggajian;


import main.java.com.penggajian.dao.JabatanDAO;
import main.java.com.penggajian.dao.KaryawanDAO;
import main.java.com.penggajian.dao.KehadiranDAO;
import main.java.com.penggajian.dao.PotonganDAO;
import main.java.com.penggajian.dao.GajiDAO;
import main.java.com.penggajian.model.Jabatan;
import main.java.com.penggajian.model.Karyawan;
import main.java.com.penggajian.model.Kehadiran;
import main.java.com.penggajian.model.Potongan;
import main.java.com.penggajian.model.Gaji;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static Scanner scanner = new Scanner(System.in);
    private static JabatanDAO jabatanDAO = new JabatanDAO();
    private static KaryawanDAO karyawanDAO = new KaryawanDAO();
    private static KehadiranDAO kehadiranDAO = new KehadiranDAO();
    private static PotonganDAO potonganDAO = new PotonganDAO();
    private static GajiDAO gajiDAO = new GajiDAO();

    public static void main(String[] args) {
        displayMenu();
    }

    private static void displayMenu() {
        int choice;
        do {
            System.out.println("\n--- Sistem Informasi Penggajian Karyawan ---");
            System.out.println("1. Manajemen Jabatan");
            System.out.println("2. Manajemen Karyawan");
            System.out.println("3. Input Kehadiran");
            System.out.println("4. Manajemen Potongan");
            System.out.println("5. Hitung & Catat Gaji");
            System.out.println("6. Lihat Data Gaji");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manageJabatan();
                    break;
                case 2:
                    manageKaryawan();
                    break;
                case 3:
                    inputKehadiran();
                    break;
                case 4:
                    managePotongan();
                    break;
                case 5:
                    hitungDanCatatGaji();
                    break;
                case 6:
                    viewGajiRecords();
                    break;
                case 0:
                    System.out.println("Terima kasih! Program berakhir.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (choice != 0);
    }

    // --- Manajemen Jabatan ---
    private static void manageJabatan() {
        int choice;
        do {
            System.out.println("\n--- Manajemen Jabatan ---");
            System.out.println("1. Tambah Jabatan");
            System.out.println("2. Lihat Semua Jabatan");
            System.out.println("3. Update Jabatan");
            System.out.println("4. Hapus Jabatan");
            System.out.println("0. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addJabatan();
                    break;
                case 2:
                    viewAllJabatan();
                    break;
                case 3:
                    updateJabatan();
                    break;
                case 4:
                    deleteJabatan();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (choice != 0);
    }

    private static void addJabatan() {
        System.out.print("Masukkan Nama Jabatan: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Gaji Pokok: ");
        double gajiPokok = scanner.nextDouble();
        scanner.nextLine();

        Jabatan jabatan = new Jabatan(nama, gajiPokok);
        if (jabatanDAO.insertJabatan(jabatan)) {
            System.out.println("Jabatan berhasil ditambahkan.");
        } else {
            System.out.println("Gagal menambahkan jabatan.");
        }
    }

    private static void viewAllJabatan() {
        List<Jabatan> jabatanList = jabatanDAO.getAllJabatan();
        if (jabatanList.isEmpty()) {
            System.out.println("Belum ada data jabatan.");
            return;
        }
        System.out.println("\n--- Daftar Jabatan ---");
        for (Jabatan j : jabatanList) {
            System.out.println(j);
        }
    }

    private static void updateJabatan() {
        System.out.print("Masukkan ID Jabatan yang akan diupdate: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Jabatan existingJabatan = jabatanDAO.getJabatanById(id);
        if (existingJabatan == null) {
            System.out.println("Jabatan dengan ID " + id + " tidak ditemukan.");
            return;
        }

        System.out.print("Masukkan Nama Jabatan Baru (kosongkan jika tidak diubah): ");
        String namaBaru = scanner.nextLine();
        if (!namaBaru.isEmpty()) {
            existingJabatan.setNamaJabatan(namaBaru);
        }

        System.out.print("Masukkan Gaji Pokok Baru (0 jika tidak diubah): ");
        double gajiBaru = scanner.nextDouble();
        scanner.nextLine();
        if (gajiBaru > 0) {
            existingJabatan.setGajiPokok(gajiBaru);
        }

        if (jabatanDAO.updateJabatan(existingJabatan)) {
            System.out.println("Jabatan berhasil diupdate.");
        } else {
            System.out.println("Gagal mengupdate jabatan.");
        }
    }

    private static void deleteJabatan() {
        System.out.print("Masukkan ID Jabatan yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (jabatanDAO.deleteJabatan(id)) {
            System.out.println("Jabatan berhasil dihapus.");
        } else {
            System.out.println("Gagal menghapus jabatan. Pastikan tidak ada karyawan yang terhubung dengan jabatan ini.");
        }
    }

    // --- Manajemen Karyawan ---
    private static void manageKaryawan() {
        int choice;
        do {
            System.out.println("\n--- Manajemen Karyawan ---");
            System.out.println("1. Tambah Karyawan");
            System.out.println("2. Lihat Semua Karyawan");
            System.out.println("3. Update Karyawan");
            System.out.println("4. Hapus Karyawan");
            System.out.println("0. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addKaryawan();
                    break;
                case 2:
                    viewAllKaryawan();
                    break;
                case 3:
                    updateKaryawan();
                    break;
                case 4:
                    deleteKaryawan();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (choice != 0);
    }

    private static void addKaryawan() {
        System.out.print("Masukkan Nama Lengkap: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Tanggal Lahir (YYYY-MM-DD): ");
        LocalDate tanggalLahir = LocalDate.parse(scanner.nextLine());
        System.out.print("Masukkan Jenis Kelamin (Laki-laki/Perempuan): ");
        String jk = scanner.nextLine();
        System.out.print("Masukkan Alamat: ");
        String alamat = scanner.nextLine();
        System.out.print("Masukkan Nomor Telepon: ");
        String telepon = scanner.nextLine();

        viewAllJabatan(); // Tampilkan daftar jabatan untuk memudahkan pemilihan
        System.out.print("Masukkan ID Jabatan: ");
        int idJabatan = scanner.nextInt();
        scanner.nextLine();

        Karyawan karyawan = new Karyawan(nama, tanggalLahir, jk, alamat, telepon, idJabatan);
        if (karyawanDAO.insertKaryawan(karyawan)) {
            System.out.println("Karyawan berhasil ditambahkan.");
        } else {
            System.out.println("Gagal menambahkan karyawan. Pastikan ID Jabatan valid.");
        }
    }

    private static void viewAllKaryawan() {
        List<Karyawan> karyawanList = karyawanDAO.getAllKaryawan();
        if (karyawanList.isEmpty()) {
            System.out.println("Belum ada data karyawan.");
            return;
        }
        System.out.println("\n--- Daftar Karyawan ---");
        for (Karyawan k : karyawanList) {
            // Untuk menampilkan nama jabatan, Anda bisa mengambil data jabatan juga
            Jabatan jabatan = jabatanDAO.getJabatanById(k.getIdJabatan());
            String namaJabatan = (jabatan != null) ? jabatan.getNamaJabatan() : "N/A";
            System.out.println("ID: " + k.getIdKaryawan() + ", Nama: " + k.getNamaLengkap() + ", Jabatan: " + namaJabatan);
        }
    }

    private static void updateKaryawan() {
        System.out.print("Masukkan ID Karyawan yang akan diupdate: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Karyawan existingKaryawan = karyawanDAO.getKaryawanById(id);
        if (existingKaryawan == null) {
            System.out.println("Karyawan dengan ID " + id + " tidak ditemukan.");
            return;
        }

        System.out.print("Masukkan Nama Lengkap Baru (kosongkan jika tidak diubah): ");
        String namaBaru = scanner.nextLine();
        if (!namaBaru.isEmpty()) {
            existingKaryawan.setNamaLengkap(namaBaru);
        }

        System.out.print("Masukkan Tanggal Lahir Baru (YYYY-MM-DD, kosongkan jika tidak diubah): ");
        String tglLahirStr = scanner.nextLine();
        if (!tglLahirStr.isEmpty()) {
            existingKaryawan.setTanggalLahir(LocalDate.parse(tglLahirStr));
        }

        System.out.print("Masukkan Jenis Kelamin Baru (kosongkan jika tidak diubah): ");
        String jkBaru = scanner.nextLine();
        if (!jkBaru.isEmpty()) {
            existingKaryawan.setJenisKelamin(jkBaru);
        }

        System.out.print("Masukkan Alamat Baru (kosongkan jika tidak diubah): ");
        String alamatBaru = scanner.nextLine();
        if (!alamatBaru.isEmpty()) {
            existingKaryawan.setAlamat(alamatBaru);
        }

        System.out.print("Masukkan Nomor Telepon Baru (kosongkan jika tidak diubah): ");
        String teleponBaru = scanner.nextLine();
        if (!teleponBaru.isEmpty()) {
            existingKaryawan.setNoTelepon(teleponBaru);
        }

        viewAllJabatan();
        System.out.print("Masukkan ID Jabatan Baru (0 jika tidak diubah): ");
        int idJabatanBaru = scanner.nextInt();
        scanner.nextLine();
        if (idJabatanBaru > 0) {
            existingKaryawan.setIdJabatan(idJabatanBaru);
        }

        if (karyawanDAO.updateKaryawan(existingKaryawan)) {
            System.out.println("Karyawan berhasil diupdate.");
        } else {
            System.out.println("Gagal mengupdate karyawan.");
        }
    }

    private static void deleteKaryawan() {
        System.out.print("Masukkan ID Karyawan yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (karyawanDAO.deleteKaryawan(id)) {
            System.out.println("Karyawan berhasil dihapus.");
        } else {
            System.out.println("Gagal menghapus karyawan. Pastikan tidak ada data terkait (kehadiran, gaji) yang terhubung.");
        }
    }

    // --- Input Kehadiran ---
    private static void inputKehadiran() {
        viewAllKaryawan();
        System.out.print("Masukkan ID Karyawan untuk input kehadiran: ");
        int idKaryawan = scanner.nextInt();
        scanner.nextLine();

        // Cek apakah karyawan ada
        Karyawan karyawan = karyawanDAO.getKaryawanById(idKaryawan);
        if (karyawan == null) {
            System.out.println("Karyawan dengan ID " + idKaryawan + " tidak ditemukan.");
            return;
        }

        System.out.print("Masukkan Tanggal Kehadiran (YYYY-MM-DD): ");
        LocalDate tanggalHadir = LocalDate.parse(scanner.nextLine());
        System.out.print("Masukkan Status Kehadiran (Hadir/Izin/Sakit/Alpha): ");
        String status = scanner.nextLine();
        System.out.print("Masukkan Jam Masuk (HH:MM:SS, kosongkan jika tidak ada): ");
        String jamMasukStr = scanner.nextLine();
        System.out.print("Masukkan Jam Pulang (HH:MM:SS, kosongkan jika tidak ada): ");
        String jamPulangStr = scanner.nextLine();

        // Handle Time parsing (optional, can be null in DB)
        java.sql.Time jamMasuk = null;
        if (!jamMasukStr.isEmpty()) {
            jamMasuk = java.sql.Time.valueOf(jamMasukStr);
        }
        java.sql.Time jamPulang = null;
        if (!jamPulangStr.isEmpty()) {
            jamPulang = java.sql.Time.valueOf(jamPulangStr);
        }

        Kehadiran kehadiran = new Kehadiran(idKaryawan, tanggalHadir, status, jamMasuk, jamPulang);
        if (kehadiranDAO.insertKehadiran(kehadiran)) {
            System.out.println("Kehadiran berhasil dicatat.");
        } else {
            System.out.println("Gagal mencatat kehadiran.");
        }
    }

    // --- Manajemen Potongan ---
    private static void managePotongan() {
        int choice;
        do {
            System.out.println("\n--- Manajemen Potongan ---");
            System.out.println("1. Tambah Potongan");
            System.out.println("2. Lihat Semua Potongan");
            System.out.println("3. Update Potongan");
            System.out.println("4. Hapus Potongan");
            System.out.println("0. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addPotongan();
                    break;
                case 2:
                    viewAllPotongan();
                    break;
                case 3:
                    updatePotongan();
                    break;
                case 4:
                    deletePotongan();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (choice != 0);
    }

    private static void addPotongan() {
        System.out.print("Masukkan Nama Potongan: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Jumlah Potongan: ");
        double jumlah = scanner.nextDouble();
        scanner.nextLine();

        Potongan potongan = new Potongan(nama, jumlah);
        if (potonganDAO.insertPotongan(potongan)) {
            System.out.println("Potongan berhasil ditambahkan.");
        } else {
            System.out.println("Gagal menambahkan potongan.");
        }
    }

    private static void viewAllPotongan() {
        List<Potongan> potonganList = potonganDAO.getAllPotongan();
        if (potonganList.isEmpty()) {
            System.out.println("Belum ada data potongan.");
            return;
        }
        System.out.println("\n--- Daftar Potongan ---");
        for (Potongan p : potonganList) {
            System.out.println(p);
        }
    }

    private static void updatePotongan() {
        System.out.print("Masukkan ID Potongan yang akan diupdate: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Potongan existingPotongan = potonganDAO.getPotonganById(id);
        if (existingPotongan == null) {
            System.out.println("Potongan dengan ID " + id + " tidak ditemukan.");
            return;
        }

        System.out.print("Masukkan Nama Potongan Baru (kosongkan jika tidak diubah): ");
        String namaBaru = scanner.nextLine();
        if (!namaBaru.isEmpty()) {
            existingPotongan.setNamaPotongan(namaBaru);
        }

        System.out.print("Masukkan Jumlah Potongan Baru (0 jika tidak diubah): ");
        double jumlahBaru = scanner.nextDouble();
        scanner.nextLine();
        if (jumlahBaru > 0) {
            existingPotongan.setJumlahPotongan(jumlahBaru);
        }

        if (potonganDAO.updatePotongan(existingPotongan)) {
            System.out.println("Potongan berhasil diupdate.");
        } else {
            System.out.println("Gagal mengupdate potongan.");
        }
    }

    private static void deletePotongan() {
        System.out.print("Masukkan ID Potongan yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (potonganDAO.deletePotongan(id)) {
            System.out.println("Potongan berhasil dihapus.");
        } else {
            System.out.println("Gagal menghapus potongan.");
        }
    }

    // --- Hitung & Catat Gaji (Transaksi Keterhubungan Utama) ---
    private static void hitungDanCatatGaji() {
        viewAllKaryawan();
        System.out.print("Masukkan ID Karyawan untuk menghitung gaji: ");
        int idKaryawan = scanner.nextInt();
        scanner.nextLine();

        Karyawan karyawan = karyawanDAO.getKaryawanById(idKaryawan);
        if (karyawan == null) {
            System.out.println("Karyawan dengan ID " + idKaryawan + " tidak ditemukan.");
            return;
        }

        Jabatan jabatan = jabatanDAO.getJabatanById(karyawan.getIdJabatan());
        if (jabatan == null) {
            System.out.println("Jabatan untuk karyawan ini tidak ditemukan. Tidak bisa menghitung gaji.");
            return;
        }

        System.out.print("Masukkan Periode Gaji (contoh: Januari 2025): ");
        String periodeGaji = scanner.nextLine();
        System.out.print("Masukkan Tanggal Gaji Dibayarkan (YYYY-MM-DD): ");
        LocalDate tanggalGaji = LocalDate.parse(scanner.nextLine());

        // --- Logika Transaksi Keterhubungan ---
        // 1. Ambil total kehadiran untuk periode ini
        // Asumsi: menghitung kehadiran Hadir dalam satu bulan terakhir (sederhana)
        // Anda bisa memperluas ini untuk memilih rentang tanggal secara spesifik
        LocalDate startDate = tanggalGaji.minusMonths(1).withDayOfMonth(1); // Mulai dari awal bulan sebelumnya
        LocalDate endDate = tanggalGaji.withDayOfMonth(tanggalGaji.lengthOfMonth()); // Sampai akhir bulan ini
        int totalKehadiran = kehadiranDAO.getJumlahKehadiranHadirByKaryawanAndPeriod(idKaryawan, startDate, endDate);
        System.out.println("Jumlah Kehadiran (Hadir) untuk " + karyawan.getNamaLengkap() + " dalam periode ini: " + totalKehadiran + " hari.");

        // 2. Hitung tunjangan (contoh: tunjangan kehadiran)
        // Asumsi: Tunjangan 50.000 per hari hadir
        double tunjangan = totalKehadiran * 50000.00;
        System.out.println("Total Tunjangan Kehadiran: " + tunjangan);

        // 3. Ambil total potongan
        // Asumsi: Semua potongan yang ada di tabel Potongan akan diterapkan
        // Jika ada logika potongan per karyawan, Anda perlu menambahkannya
        List<Potongan> allPotongan = potonganDAO.getAllPotongan();
        double totalPotongan = 0;
        for (Potongan p : allPotongan) {
            totalPotongan += p.getJumlahPotongan();
        }
        System.out.println("Total Potongan: " + totalPotongan);


        // 4. Hitung Gaji Bersih
        double gajiPokok = jabatan.getGajiPokok();
        double gajiBersih = gajiPokok + tunjangan - totalPotongan;

        System.out.println("\n--- Detail Perhitungan Gaji ---");
        System.out.println("Gaji Pokok: " + gajiPokok);
        System.out.println("Tunjangan: " + tunjangan);
        System.out.println("Total Potongan: " + totalPotongan);
        System.out.println("Gaji Bersih: " + gajiBersih);

        // 5. Simpan hasil perhitungan ke tabel Gaji
        Gaji gajiBaru = new Gaji(idKaryawan, periodeGaji, tanggalGaji, totalKehadiran, tunjangan, totalPotongan, gajiBersih);
        if (gajiDAO.insertGaji(gajiBaru)) {
            System.out.println("Perhitungan gaji berhasil dicatat.");
        } else {
            System.out.println("Gagal mencatat perhitungan gaji.");
        }
    }

    // --- Lihat Data Gaji ---
    private static void viewGajiRecords() {
        List<Gaji> gajiList = gajiDAO.getAllGaji();
        if (gajiList.isEmpty()) {
            System.out.println("Belum ada data gaji yang tercatat.");
            return;
        }
        System.out.println("\n--- Riwayat Gaji Karyawan ---");
        for (Gaji g : gajiList) {
            Karyawan karyawan = karyawanDAO.getKaryawanById(g.getIdKaryawan());
            String namaKaryawan = (karyawan != null) ? karyawan.getNamaLengkap() : "N/A";
            System.out.println("ID Gaji: " + g.getIdGaji() +
                               ", Karyawan: " + namaKaryawan +
                               ", Periode: " + g.getPeriodeGaji() +
                               ", Tgl Bayar: " + g.getTanggalGaji() +
                               ", Hadir: " + g.getTotalKehadiran() + " hari" +
                               ", Tunjangan: " + g.getTunjangan() +
                               ", Potongan: " + g.getTotalPotongan() +
                               ", Gaji Bersih: " + g.getGajiBersih());
        }
    }
}