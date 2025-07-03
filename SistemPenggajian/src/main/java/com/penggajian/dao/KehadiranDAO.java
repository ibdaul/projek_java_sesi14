package main.java.com.penggajian.dao;

// File: src/main/java/com/penggajian/dao/KehadiranDAO.java


import main.java.com.penggajian.model.Kehadiran;
import main.java.com.penggajian.util.DatabaseConnection; // Import kelas utilitas untuk koneksi database

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // Untuk mendapatkan auto-generated keys
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class KehadiranDAO {

    /**
     * Menyimpan data kehadiran baru ke database.
     *
     * @param kehadiran Objek Kehadiran yang akan disimpan.
     * @return true jika berhasil disimpan, false jika gagal.
     */
    public boolean insertKehadiran(Kehadiran kehadiran) {
        // SQL query untuk menyisipkan data kehadiran.
        // id_kehadiran akan auto-incremented oleh database.
        String sql = "INSERT INTO Kehadiran (id_karyawan, tanggal_hadir, status_kehadiran, jam_masuk, jam_pulang) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection(); // Dapatkan koneksi dari kelas utilitas
            // Gunakan RETURN_GENERATED_KEYS untuk mendapatkan ID yang baru dibuat
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, kehadiran.getIdKaryawan());
            // Konversi LocalDate ke java.sql.Date untuk database
            stmt.setDate(2, java.sql.Date.valueOf(kehadiran.getTanggalHadir()));
            stmt.setString(3, kehadiran.getStatusKehadiran());
            stmt.setTime(4, kehadiran.getJamMasuk());
            stmt.setTime(5, kehadiran.getJamPulang());

            int rowsAffected = stmt.executeUpdate(); // Eksekusi query INSERT

            if (rowsAffected > 0) {
                // Jika berhasil, ambil ID yang di-generate oleh database
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    kehadiran.setIdKehadiran(generatedKeys.getInt(1)); // Set ID ke objek model
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error inserting Kehadiran: " + e.getMessage());
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
     * Mengambil objek Kehadiran berdasarkan ID-nya.
     *
     * @param idKehadiran ID kehadiran yang dicari.
     * @return Objek Kehadiran jika ditemukan, null jika tidak.
     */
    public Kehadiran getKehadiranById(int idKehadiran) {
        String sql = "SELECT * FROM Kehadiran WHERE id_kehadiran = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Kehadiran kehadiran = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idKehadiran);
            rs = stmt.executeQuery(); // Eksekusi query SELECT

            if (rs.next()) {
                // Buat objek Kehadiran dari data hasil query
                kehadiran = new Kehadiran(
                    rs.getInt("id_kehadiran"),
                    rs.getInt("id_karyawan"),
                    rs.getDate("tanggal_hadir").toLocalDate(), // Konversi java.sql.Date ke LocalDate
                    rs.getString("status_kehadiran"),
                    rs.getTime("jam_masuk"),
                    rs.getTime("jam_pulang")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error getting Kehadiran by ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
            if (rs != null) { try { rs.close(); } catch (SQLException e) { System.err.println("Error closing resultset: " + e.getMessage()); } }
        }
        return kehadiran;
    }

    /**
     * Mengambil semua data kehadiran dari database.
     *
     * @return List objek Kehadiran.
     */
    public List<Kehadiran> getAllKehadiran() {
        List<Kehadiran> kehadiranList = new ArrayList<>();
        String sql = "SELECT * FROM Kehadiran ORDER BY tanggal_hadir DESC, id_karyawan ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                kehadiranList.add(new Kehadiran(
                    rs.getInt("id_kehadiran"),
                    rs.getInt("id_karyawan"),
                    rs.getDate("tanggal_hadir").toLocalDate(),
                    rs.getString("status_kehadiran"),
                    rs.getTime("jam_masuk"),
                    rs.getTime("jam_pulang")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all Kehadiran: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
            if (rs != null) { try { rs.close(); } catch (SQLException e) { System.err.println("Error closing resultset: " + e.getMessage()); } }
        }
        return kehadiranList;
    }

    /**
     * Mengupdate data kehadiran yang sudah ada di database.
     *
     * @param kehadiran Objek Kehadiran dengan data yang diperbarui dan ID yang valid.
     * @return true jika berhasil diupdate, false jika gagal.
     */
    public boolean updateKehadiran(Kehadiran kehadiran) {
        String sql = "UPDATE Kehadiran SET id_karyawan = ?, tanggal_hadir = ?, status_kehadiran = ?, jam_masuk = ?, jam_pulang = ? WHERE id_kehadiran = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, kehadiran.getIdKaryawan());
            stmt.setDate(2, java.sql.Date.valueOf(kehadiran.getTanggalHadir()));
            stmt.setString(3, kehadiran.getStatusKehadiran());
            stmt.setTime(4, kehadiran.getJamMasuk());
            stmt.setTime(5, kehadiran.getJamPulang());
            stmt.setInt(6, kehadiran.getIdKehadiran());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating Kehadiran: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
        }
    }

    /**
     * Menghapus data kehadiran dari database berdasarkan ID.
     *
     * @param idKehadiran ID kehadiran yang akan dihapus.
     * @return true jika berhasil dihapus, false jika gagal.
     */
    public boolean deleteKehadiran(int idKehadiran) {
        String sql = "DELETE FROM Kehadiran WHERE id_kehadiran = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idKehadiran);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting Kehadiran: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
        }
    }

    /**
     * Menghitung jumlah hari 'Hadir' untuk seorang karyawan dalam periode tertentu.
     * Ini penting untuk perhitungan tunjangan kehadiran.
     *
     * @param idKaryawan ID karyawan.
     * @param startDate Tanggal mulai periode.
     * @param endDate Tanggal akhir periode.
     * @return Jumlah hari 'Hadir' untuk karyawan tersebut dalam periode yang ditentukan.
     */
    public int getJumlahKehadiranHadirByKaryawanAndPeriod(int idKaryawan, LocalDate startDate, LocalDate endDate) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Kehadiran WHERE id_karyawan = ? AND status_kehadiran = 'Hadir' AND tanggal_hadir BETWEEN ? AND ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idKaryawan);
            stmt.setDate(2, java.sql.Date.valueOf(startDate));
            stmt.setDate(3, java.sql.Date.valueOf(endDate));
            rs = stmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1); // Ambil hasil COUNT
            }
        } catch (SQLException e) {
            System.err.println("Error counting Hadir Kehadiran: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
            if (rs != null) { try { rs.close(); } catch (SQLException e) { System.err.println("Error closing resultset: " + e.getMessage()); } }
        }
        return count;
    }
}