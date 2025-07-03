package main.java.com.penggajian.model;

public class Jabatan {
    private int idJabatan;
    private String namaJabatan;
    private double gajiPokok;

    public Jabatan(int idJabatan, String namaJabatan, double gajiPokok) {
        this.idJabatan = idJabatan;
        this.namaJabatan = namaJabatan;
        this.gajiPokok = gajiPokok;
    }

    // Constructor for new Jabatan (without ID, for insert)
    public Jabatan(String namaJabatan, double gajiPokok) {
        this.namaJabatan = namaJabatan;
        this.gajiPokok = gajiPokok;
    }

    // Getters and Setters
    public int getIdJabatan() {
        return idJabatan;
    }

    public void setIdJabatan(int idJabatan) {
        this.idJabatan = idJabatan;
    }

    public String getNamaJabatan() {
        return namaJabatan;
    }

    public void setNamaJabatan(String namaJabatan) {
        this.namaJabatan = namaJabatan;
    }

    public double getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(double gajiPokok) {
        this.gajiPokok = gajiPokok;
    }

    @Override
    public String toString() {
        return "ID: " + idJabatan + ", Nama Jabatan: " + namaJabatan + ", Gaji Pokok: " + gajiPokok;
    }
}