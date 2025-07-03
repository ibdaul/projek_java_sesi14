package main.java.com.penggajian.dao;



import main.java.com.penggajian.model.Gaji;
import main.java.com.penggajian.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@SuppressWarnings("unused")
public class GajiDAO {

    /**
     * Menyimpan data gaji baru ke database.
     *
     * @param gaji Objek Gaji yang akan disimpan.
     * @return true jika berhasil disimpan, false jika gagal.
     */
    public boolean insertGaji(Gaji gaji) {
        String sql = "INSERT INTO Gaji (id_karyawan, periode_gaji, tanggal_gaji, total_kehadiran, tunjangan, total_potongan, gaji_bersih) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, gaji.getIdKaryawan());
            stmt.setString(2, gaji.getPeriodeGaji());
            stmt.setDate(3, java.sql.Date.valueOf(gaji.getTanggalGaji())); // Konversi LocalDate ke java.sql.Date
            stmt.setInt(4, gaji.getTotalKehadiran());
            stmt.setDouble(5, gaji.getTunjangan());
            stmt.setDouble(6, gaji.getTotalPotongan());
            stmt.setDouble(7, gaji.getGajiBersih());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    gaji.setIdGaji(generatedKeys.getInt(1));
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error inserting Gaji: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
        }
    }

    /**
     * Mengambil objek Gaji berdasarkan ID-nya.
     *
     * @param idGaji ID gaji yang dicari.
     * @return Objek Gaji jika ditemukan, null jika tidak.
     */
    public Gaji getGajiById(int idGaji) {
        String sql = "SELECT * FROM Gaji WHERE id_gaji = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Gaji gaji = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idGaji);
            rs = stmt.executeQuery();

            if (rs.next()) {
                gaji = new Gaji(
                    rs.getInt("id_gaji"),
                    rs.getInt("id_karyawan"),
                    rs.getString("periode_gaji"),
                    rs.getDate("tanggal_gaji").toLocalDate(),
                    rs.getInt("total_kehadiran"),
                    rs.getDouble("tunjangan"),
                    rs.getDouble("total_potongan"),
                    rs.getDouble("gaji_bersih")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error getting Gaji by ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
            if (rs != null) { try { rs.close(); } catch (SQLException e) { System.err.println("Error closing resultset: " + e.getMessage()); } }
        }
        return gaji;
    }

    /**
     * Mengambil semua data gaji dari database.
     *
     * @return List objek Gaji.
     */
    public List<Gaji> getAllGaji() {
        List<Gaji> gajiList = new ArrayList<>();
        // Mengurutkan berdasarkan tanggal gaji terbaru
        String sql = "SELECT * FROM Gaji ORDER BY tanggal_gaji DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                gajiList.add(new Gaji(
                    rs.getInt("id_gaji"),
                    rs.getInt("id_karyawan"),
                    rs.getString("periode_gaji"),
                    rs.getDate("tanggal_gaji").toLocalDate(),
                    rs.getInt("total_kehadiran"),
                    rs.getDouble("tunjangan"),
                    rs.getDouble("total_potongan"),
                    rs.getDouble("gaji_bersih")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all Gaji: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
            if (rs != null) { try { rs.close(); } catch (SQLException e) { System.err.println("Error closing resultset: " + e.getMessage()); } }
        }
        return gajiList;
    }

    /**
     * Mengupdate data gaji yang sudah ada di database.
     *
     * @param gaji Objek Gaji dengan data yang diperbarui dan ID yang valid.
     * @return true jika berhasil diupdate, false jika gagal.
     */
    public boolean updateGaji(Gaji gaji) {
        String sql = "UPDATE Gaji SET id_karyawan = ?, periode_gaji = ?, tanggal_gaji = ?, total_kehadiran = ?, tunjangan = ?, total_potongan = ?, gaji_bersih = ? WHERE id_gaji = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, gaji.getIdKaryawan());
            stmt.setString(2, gaji.getPeriodeGaji());
            stmt.setDate(3, java.sql.Date.valueOf(gaji.getTanggalGaji()));
            stmt.setInt(4, gaji.getTotalKehadiran());
            stmt.setDouble(5, gaji.getTunjangan());
            stmt.setDouble(6, gaji.getTotalPotongan());
            stmt.setDouble(7, gaji.getGajiBersih());
            stmt.setInt(8, gaji.getIdGaji());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating Gaji: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
        }
    }

    /**
     * Menghapus data gaji dari database berdasarkan ID.
     *
     * @param idGaji ID gaji yang akan dihapus.
     * @return true jika berhasil dihapus, false jika gagal.
     */
    public boolean deleteGaji(int idGaji) {
        String sql = "DELETE FROM Gaji WHERE id_gaji = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idGaji);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting Gaji: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
        }
    }
}
