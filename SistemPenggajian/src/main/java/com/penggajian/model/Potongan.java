package main.java.com.penggajian.model;


public class Potongan {
    private int idPotongan;       // Corresponds to id_potongan (INT PRIMARY KEY)
    private String namaPotongan;  // Corresponds to nama_potongan (VARCHAR, e.g., "BPJS Kesehatan", "PPh 21")
    private double jumlahPotongan; // Corresponds to jumlah_potongan (DOUBLE)

    // Constructor untuk data Potongan yang sudah ada (dengan ID)
    public Potongan(int idPotongan, String namaPotongan, double jumlahPotongan) {
        this.idPotongan = idPotongan;
        this.namaPotongan = namaPotongan;
        this.jumlahPotongan = jumlahPotongan;
    }

    // Constructor untuk data Potongan baru (tanpa ID)
    public Potongan(String namaPotongan, double jumlahPotongan) {
        this.namaPotongan = namaPotongan;
        this.jumlahPotongan = jumlahPotongan;
    }

    // --- Getters and Setters ---

    public int getIdPotongan() {
        return idPotongan;
    }

    public void setIdPotongan(int idPotongan) {
        this.idPotongan = idPotongan;
    }

    public String getNamaPotongan() {
        return namaPotongan;
    }

    public void setNamaPotongan(String namaPotongan) {
        this.namaPotongan = namaPotongan;
    }

    public double getJumlahPotongan() {
        return jumlahPotongan;
    }

    public void setJumlahPotongan(double jumlahPotongan) {
        this.jumlahPotongan = jumlahPotongan;
    }

    @Override
    public String toString() {
        return "Potongan{" +
               "idPotongan=" + idPotongan +
               ", namaPotongan='" + namaPotongan + '\'' +
               ", jumlahPotongan=" + jumlahPotongan +
               '}';
    }
}