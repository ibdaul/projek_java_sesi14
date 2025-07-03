// File: src/main/java/com/penggajian/dao/JabatanDAO.java
package main.java.com.penggajian.dao;

import main.java.com.penggajian.model.Jabatan;
import main.java.com.penggajian.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JabatanDAO {

    /**
     * Menyimpan data jabatan baru ke database.
     *
     * @param jabatan Objek Jabatan yang akan disimpan.
     * @return true jika berhasil disimpan, false jika gagal.
     */
    public boolean insertJabatan(Jabatan jabatan) {
        // SQL query disesuaikan: Hapus 'tunjangan_jabatan'
        String sql = "INSERT INTO Jabatan (nama_jabatan, gaji_pokok) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, jabatan.getNamaJabatan());
            stmt.setDouble(2, jabatan.getGajiPokok());
            // Hapus baris ini: stmt.setDouble(3, jabatan.getTunjanganJabatan());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    jabatan.setIdJabatan(generatedKeys.getInt(1));
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error inserting Jabatan: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
        }
    }

    /**
     * Mengambil objek Jabatan berdasarkan ID-nya.
     *
     * @param idJabatan ID jabatan yang dicari.
     * @return Objek Jabatan jika ditemukan, null jika tidak.
     */
    public Jabatan getJabatanById(int idJabatan) {
        String sql = "SELECT * FROM Jabatan WHERE id_jabatan = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Jabatan jabatan = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idJabatan);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Konstruktor disesuaikan: Hapus parameter 'tunjangan_jabatan'
                jabatan = new Jabatan(
                    rs.getInt("id_jabatan"),
                    rs.getString("nama_jabatan"),
                    rs.getDouble("gaji_pokok")
                    // Hapus baris ini: , rs.getDouble("tunjangan_jabatan")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error getting Jabatan by ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
            if (rs != null) { try { rs.close(); } catch (SQLException e) { System.err.println("Error closing resultset: " + e.getMessage()); } }
        }
        return jabatan;
    }

    /**
     * Mengambil semua data jabatan dari database.
     *
     * @return List objek Jabatan.
     */
    public List<Jabatan> getAllJabatan() {
        List<Jabatan> jabatanList = new ArrayList<>();
        String sql = "SELECT * FROM Jabatan ORDER BY nama_jabatan ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                // Konstruktor disesuaikan: Hapus parameter 'tunjangan_jabatan'
                jabatanList.add(new Jabatan(
                    rs.getInt("id_jabatan"),
                    rs.getString("nama_jabatan"),
                    rs.getDouble("gaji_pokok")
                    // Hapus baris ini: , rs.getDouble("tunjangan_jabatan")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all Jabatan: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
            if (rs != null) { try { rs.close(); } catch (SQLException e) { System.err.println("Error closing resultset: " + e.getMessage()); } }
        }
        return jabatanList;
    }

    /**
     * Mengupdate data jabatan yang sudah ada di database.
     *
     * @param jabatan Objek Jabatan dengan data yang diperbarui dan ID yang valid.
     * @return true jika berhasil diupdate, false jika gagal.
     */
    public boolean updateJabatan(Jabatan jabatan) {
        // SQL query disesuaikan: Hapus 'tunjangan_jabatan'
        String sql = "UPDATE Jabatan SET nama_jabatan = ?, gaji_pokok = ? WHERE id_jabatan = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, jabatan.getNamaJabatan());
            stmt.setDouble(2, jabatan.getGajiPokok());
            // Hapus baris ini: stmt.setDouble(3, jabatan.getTunjanganJabatan());
            stmt.setInt(3, jabatan.getIdJabatan()); // Index parameter disesuaikan

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating Jabatan: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
        }
    }

    /**
     * Menghapus data jabatan dari database berdasarkan ID.
     *
     * @param idJabatan ID jabatan yang akan dihapus.
     * @return true jika berhasil dihapus, false jika gagal.
     */
    public boolean deleteJabatan(int idJabatan) {
        String sql = "DELETE FROM Jabatan WHERE id_jabatan = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idJabatan);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting Jabatan: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
        }
    }
}