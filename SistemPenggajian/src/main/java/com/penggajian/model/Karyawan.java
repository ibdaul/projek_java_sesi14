// File: src/main/java/com/penggajian/model/Karyawan.java
package main.java.com.penggajian.model;

import java.time.LocalDate; // Use java.time for modern date handling

public class Karyawan {
    private int idKaryawan;
    private String namaLengkap;
    private LocalDate tanggalLahir;
    private String jenisKelamin;
    private String alamat;
    private String noTelepon;
    private int idJabatan; // Foreign Key

    // Constructor for existing Karyawan
    public Karyawan(int idKaryawan, String namaLengkap, LocalDate tanggalLahir, String jenisKelamin, String alamat, String noTelepon, int idJabatan) {
        this.idKaryawan = idKaryawan;
        this.namaLengkap = namaLengkap;
        this.tanggalLahir = tanggalLahir;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
        this.idJabatan = idJabatan;
    }

    // Constructor for new Karyawan (without ID, for insert)
    public Karyawan(String namaLengkap, LocalDate tanggalLahir, String jenisKelamin, String alamat, String noTelepon, int idJabatan) {
        this.namaLengkap = namaLengkap;
        this.tanggalLahir = tanggalLahir;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
        this.idJabatan = idJabatan;
    }

    // Getters and Setters
    public int getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(int idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public LocalDate getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(LocalDate tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public int getIdJabatan() {
        return idJabatan;
    }

    public void setIdJabatan(int idJabatan) {
        this.idJabatan = idJabatan;
    }

    @Override
    public String toString() {
        return "ID: " + idKaryawan + ", Nama: " + namaLengkap + ", Jabatan ID: " + idJabatan;
    }
}