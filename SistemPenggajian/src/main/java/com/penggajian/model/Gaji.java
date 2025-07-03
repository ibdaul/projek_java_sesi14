package main.java.com.penggajian.model;

import java.time.LocalDate; // Digunakan untuk tipe data DATE (tanggal saja)

public class Gaji {
    private int idGaji;           // Corresponds to id_gaji (INT PRIMARY KEY)
    private int idKaryawan;       // Corresponds to id_karyawan (INT, Foreign Key)
    private String periodeGaji;   // Corresponds to periode_gaji (VARCHAR, e.g., "Januari 2025")
    private LocalDate tanggalGaji;  // Corresponds to tanggal_gaji (DATE)
    private int totalKehadiran;   // Corresponds to total_kehadiran (INT)
    private double tunjangan;     // Corresponds to tunjangan (DOUBLE)
    private double totalPotongan; // Corresponds to total_potongan (DOUBLE)
    private double gajiBersih;    // Corresponds to gaji_bersih (DOUBLE)

    // Constructor untuk data Gaji yang sudah ada (dengan ID)
    public Gaji(int idGaji, int idKaryawan, String periodeGaji, LocalDate tanggalGaji, int totalKehadiran, double tunjangan, double totalPotongan, double gajiBersih) {
        this.idGaji = idGaji;
        this.idKaryawan = idKaryawan;
        this.periodeGaji = periodeGaji;
        this.tanggalGaji = tanggalGaji;
        this.totalKehadiran = totalKehadiran;
        this.tunjangan = tunjangan;
        this.totalPotongan = totalPotongan;
        this.gajiBersih = gajiBersih;
    }

    // Constructor untuk data Gaji baru (tanpa ID)
    public Gaji(int idKaryawan, String periodeGaji, LocalDate tanggalGaji, int totalKehadiran, double tunjangan, double totalPotongan, double gajiBersih) {
        this.idKaryawan = idKaryawan;
        this.periodeGaji = periodeGaji;
        this.tanggalGaji = tanggalGaji;
        this.totalKehadiran = totalKehadiran;
        this.tunjangan = tunjangan;
        this.totalPotongan = totalPotongan;
        this.gajiBersih = gajiBersih;
    }

    // --- Getters and Setters ---

    public int getIdGaji() {
        return idGaji;
    }

    public void setIdGaji(int idGaji) {
        this.idGaji = idGaji;
    }

    public int getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(int idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public String getPeriodeGaji() {
        return periodeGaji;
    }

    public void setPeriodeGaji(String periodeGaji) {
        this.periodeGaji = periodeGaji;
    }

    public LocalDate getTanggalGaji() {
        return tanggalGaji;
    }

    public void setTanggalGaji(LocalDate tanggalGaji) {
        this.tanggalGaji = tanggalGaji;
    }

    public int getTotalKehadiran() {
        return totalKehadiran;
    }

    public void setTotalKehadiran(int totalKehadiran) {
        this.totalKehadiran = totalKehadiran;
    }

    public double getTunjangan() {
        return tunjangan;
    }

    public void setTunjangan(double tunjangan) {
        this.tunjangan = tunjangan;
    }

    public double getTotalPotongan() {
        return totalPotongan;
    }

    public void setTotalPotongan(double totalPotongan) {
        this.totalPotongan = totalPotongan;
    }

    public double getGajiBersih() {
        return gajiBersih;
    }

    public void setGajiBersih(double gajiBersih) {
        this.gajiBersih = gajiBersih;
    }

    @Override
    public String toString() {
        return "Gaji{" +
               "idGaji=" + idGaji +
               ", idKaryawan=" + idKaryawan +
               ", periodeGaji='" + periodeGaji + '\'' +
               ", tanggalGaji=" + tanggalGaji +
               ", totalKehadiran=" + totalKehadiran +
               ", tunjangan=" + tunjangan +
               ", totalPotongan=" + totalPotongan +
               ", gajiBersih=" + gajiBersih +
               '}';
    }
}