package main.java.com.penggajian.dao;



import main.java.com.penggajian.model.Karyawan; // Import kelas model Karyawan
import main.java.com.penggajian.util.DatabaseConnection; // Import kelas utilitas koneksi database

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // Untuk mendapatkan auto-generated keys
import java.time.LocalDate; // Digunakan untuk LocalDate

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class KaryawanDAO {

    /**
     * Menyimpan data karyawan baru ke database.
     *
     * @param karyawan Objek Karyawan yang akan disimpan.
     * @return true jika berhasil disimpan, false jika gagal.
     */
    public boolean insertKaryawan(Karyawan karyawan) {
        // SQL query untuk menyisipkan data karyawan.
        // id_karyawan akan auto-incremented oleh database.
        String sql = "INSERT INTO Karyawan (nama_lengkap, tanggal_lahir, jenis_kelamin, alamat, no_telepon, id_jabatan) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection(); // Dapatkan koneksi dari kelas utilitas
            // Gunakan RETURN_GENERATED_KEYS untuk mendapatkan ID yang baru dibuat
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, karyawan.getNamaLengkap());
            // Konversi LocalDate ke java.sql.Date untuk database
            stmt.setDate(2, java.sql.Date.valueOf(karyawan.getTanggalLahir()));
            stmt.setString(3, karyawan.getJenisKelamin());
            stmt.setString(4, karyawan.getAlamat());
            stmt.setString(5, karyawan.getNoTelepon());
            stmt.setInt(6, karyawan.getIdJabatan()); // Foreign Key

            int rowsAffected = stmt.executeUpdate(); // Eksekusi query INSERT

            if (rowsAffected > 0) {
                // Jika berhasil, ambil ID yang di-generate oleh database
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    karyawan.setIdKaryawan(generatedKeys.getInt(1)); // Set ID ke objek model
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error inserting Karyawan: " + e.getMessage());
            e.printStackTrace(); // Cetak stack trace untuk debugging lebih lanjut
            return false;
        } finally {
            // Pastikan resource ditutup
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Error closing statement: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Mengambil objek Karyawan berdasarkan ID-nya.
     *
     * @param idKaryawan ID karyawan yang dicari.
     * @return Objek Karyawan jika ditemukan, null jika tidak.
     */
    public Karyawan getKaryawanById(int idKaryawan) {
        String sql = "SELECT * FROM Karyawan WHERE id_karyawan = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Karyawan karyawan = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idKaryawan);
            rs = stmt.executeQuery(); // Eksekusi query SELECT

            if (rs.next()) {
                // Buat objek Karyawan dari data hasil query
                karyawan = new Karyawan(
                    rs.getInt("id_karyawan"),
                    rs.getString("nama_lengkap"),
                    rs.getDate("tanggal_lahir").toLocalDate(), // Konversi java.sql.Date ke LocalDate
                    rs.getString("jenis_kelamin"),
                    rs.getString("alamat"),
                    rs.getString("no_telepon"),
                    rs.getInt("id_jabatan")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error getting Karyawan by ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
            if (rs != null) { try { rs.close(); } catch (SQLException e) { System.err.println("Error closing resultset: " + e.getMessage()); } }
        }
        return karyawan;
    }

    /**
     * Mengambil semua data karyawan dari database.
     *
     * @return List objek Karyawan.
     */
    public List<Karyawan> getAllKaryawan() {
        List<Karyawan> karyawanList = new ArrayList<>();
        String sql = "SELECT * FROM Karyawan ORDER BY nama_lengkap ASC"; // Urutkan berdasarkan nama
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                karyawanList.add(new Karyawan(
                    rs.getInt("id_karyawan"),
                    rs.getString("nama_lengkap"),
                    rs.getDate("tanggal_lahir").toLocalDate(),
                    rs.getString("jenis_kelamin"),
                    rs.getString("alamat"),
                    rs.getString("no_telepon"),
                    rs.getInt("id_jabatan")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all Karyawan: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
            if (rs != null) { try { rs.close(); } catch (SQLException e) { System.err.println("Error closing resultset: " + e.getMessage()); } }
        }
        return karyawanList;
    }

    /**
     * Mengupdate data karyawan yang sudah ada di database.
     *
     * @param karyawan Objek Karyawan dengan data yang diperbarui dan ID yang valid.
     * @return true jika berhasil diupdate, false jika gagal.
     */
    public boolean updateKaryawan(Karyawan karyawan) {
        String sql = "UPDATE Karyawan SET nama_lengkap = ?, tanggal_lahir = ?, jenis_kelamin = ?, alamat = ?, no_telepon = ?, id_jabatan = ? WHERE id_karyawan = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, karyawan.getNamaLengkap());
            stmt.setDate(2, java.sql.Date.valueOf(karyawan.getTanggalLahir()));
            stmt.setString(3, karyawan.getJenisKelamin());
            stmt.setString(4, karyawan.getAlamat());
            stmt.setString(5, karyawan.getNoTelepon());
            stmt.setInt(6, karyawan.getIdJabatan());
            stmt.setInt(7, karyawan.getIdKaryawan()); // Kondisi WHERE berdasarkan ID karyawan

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating Karyawan: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
        }
    }

    /**
     * Menghapus data karyawan dari database berdasarkan ID.
     * Perhatian: Ini mungkin gagal jika ada data terkait di tabel lain (misal: Kehadiran, Gaji)
     * karena constraint FOREIGN KEY. Anda mungkin perlu menghapus data terkait terlebih dahulu,
     * atau mengaktifkan CASCADE DELETE di level database (tidak disarankan tanpa pemahaman penuh).
     *
     * @param idKaryawan ID karyawan yang akan dihapus.
     * @return true jika berhasil dihapus, false jika gagal.
     */
    public boolean deleteKaryawan(int idKaryawan) {
        String sql = "DELETE FROM Karyawan WHERE id_karyawan = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idKaryawan);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting Karyawan: " + e.getMessage());
            e.printStackTrace();
            // Pesan error ini sering muncul jika ada FOREIGN KEY constraint violation
            System.err.println("Pastikan tidak ada data terkait (seperti kehadiran atau gaji) yang terhubung dengan karyawan ini.");
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
        }
    }
}