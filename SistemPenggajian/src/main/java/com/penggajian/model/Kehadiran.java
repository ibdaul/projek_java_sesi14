package main.java.com.penggajian.model;


import java.time.LocalDate; // Digunakan untuk tipe data DATE (tanggal saja)
import java.sql.Time;      // Digunakan untuk tipe data TIME (jam saja), jika diperlukan

public class Kehadiran {
    private int idKehadiran;       // Corresponds to id_kehadiran (INT PRIMARY KEY)
    private int idKaryawan;        // Corresponds to id_karyawan (INT, Foreign Key)
    private LocalDate tanggalHadir; // Corresponds to tanggal_hadir (DATE)
    private String statusKehadiran; // Corresponds to status_kehadiran (VARCHAR, e.g., "Hadir", "Izin", "Sakit", "Alpha")
    private Time jamMasuk;         // Corresponds to jam_masuk (TIME, nullable)
    private Time jamPulang;        // Corresponds to jam_pulang (TIME, nullable)

    // Constructor untuk data Kehadiran yang sudah ada (dengan ID)
    public Kehadiran(int idKehadiran, int idKaryawan, LocalDate tanggalHadir, String statusKehadiran, Time jamMasuk, Time jamPulang) {
        this.idKehadiran = idKehadiran;
        this.idKaryawan = idKaryawan;
        this.tanggalHadir = tanggalHadir;
        this.statusKehadiran = statusKehadiran;
        this.jamMasuk = jamMasuk;
        this.jamPulang = jamPulang;
    }

    // Constructor untuk data Kehadiran baru (tanpa ID, karena akan di-generate oleh database)
    public Kehadiran(int idKaryawan, LocalDate tanggalHadir, String statusKehadiran, Time jamMasuk, Time jamPulang) {
        this.idKaryawan = idKaryawan;
        this.tanggalHadir = tanggalHadir;
        this.statusKehadiran = statusKehadiran;
        this.jamMasuk = jamMasuk;
        this.jamPulang = jamPulang;
    }

    // --- Getters and Setters ---

    public int getIdKehadiran() {
        return idKehadiran;
    }

    public void setIdKehadiran(int idKehadiran) {
        this.idKehadiran = idKehadiran;
    }

    public int getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(int idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public LocalDate getTanggalHadir() {
        return tanggalHadir;
    }

    public void setTanggalHadir(LocalDate tanggalHadir) {
        this.tanggalHadir = tanggalHadir;
    }

    public String getStatusKehadiran() {
        return statusKehadiran;
    }

    public void setStatusKehadiran(String statusKehadiran) {
        this.statusKehadiran = statusKehadiran;
    }

    public Time getJamMasuk() {
        return jamMasuk;
    }

    public void setJamMasuk(Time jamMasuk) {
        this.jamMasuk = jamMasuk;
    }

    public Time getJamPulang() {
        return jamPulang;
    }

    public void setJamPulang(Time jamPulang) {
        this.jamPulang = jamPulang;
    }

    @Override
    public String toString() {
        return "Kehadiran{" +
               "idKehadiran=" + idKehadiran +
               ", idKaryawan=" + idKaryawan +
               ", tanggalHadir=" + tanggalHadir +
               ", statusKehadiran='" + statusKehadiran + '\'' +
               ", jamMasuk=" + jamMasuk +
               ", jamPulang=" + jamPulang +
               '}';
    }
}