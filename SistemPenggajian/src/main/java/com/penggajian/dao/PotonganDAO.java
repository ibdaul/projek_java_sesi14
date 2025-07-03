// File: src/main/java/com/penggajian/dao/PotonganDAO.java
package main.java.com.penggajian.dao;

import main.java.com.penggajian.model.Potongan;
import main.java.com.penggajian.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PotonganDAO {

    /**
     * Menyimpan data potongan baru ke database.
     *
     * @param potongan Objek Potongan yang akan disimpan.
     * @return true jika berhasil disimpan, false jika gagal.
     */
    public boolean insertPotongan(Potongan potongan) {
        String sql = "INSERT INTO Potongan (nama_potongan, jumlah_potongan) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, potongan.getNamaPotongan());
            stmt.setDouble(2, potongan.getJumlahPotongan());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    potongan.setIdPotongan(generatedKeys.getInt(1));
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error inserting Potongan: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
        }
    }

    /**
     * Mengambil objek Potongan berdasarkan ID-nya.
     *
     * @param idPotongan ID potongan yang dicari.
     * @return Objek Potongan jika ditemukan, null jika tidak.
     */
    public Potongan getPotonganById(int idPotongan) {
        String sql = "SELECT * FROM Potongan WHERE id_potongan = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Potongan potongan = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPotongan);
            rs = stmt.executeQuery();

            if (rs.next()) {
                potongan = new Potongan(
                    rs.getInt("id_potongan"),
                    rs.getString("nama_potongan"),
                    rs.getDouble("jumlah_potongan")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error getting Potongan by ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
            if (rs != null) { try { rs.close(); } catch (SQLException e) { System.err.println("Error closing resultset: " + e.getMessage()); } }
        }
        return potongan;
    }

    /**
     * Mengambil semua data potongan dari database.
     *
     * @return List objek Potongan.
     */
    public List<Potongan> getAllPotongan() {
        List<Potongan> potonganList = new ArrayList<>();
        String sql = "SELECT * FROM Potongan ORDER BY nama_potongan ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                potonganList.add(new Potongan(
                    rs.getInt("id_potongan"),
                    rs.getString("nama_potongan"),
                    rs.getDouble("jumlah_potongan")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all Potongan: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
            if (rs != null) { try { rs.close(); } catch (SQLException e) { System.err.println("Error closing resultset: " + e.getMessage()); } }
        }
        return potonganList;
    }

    /**
     * Mengupdate data potongan yang sudah ada di database.
     *
     * @param potongan Objek Potongan dengan data yang diperbarui dan ID yang valid.
     * @return true jika berhasil diupdate, false jika gagal.
     */
    public boolean updatePotongan(Potongan potongan) {
        String sql = "UPDATE Potongan SET nama_potongan = ?, jumlah_potongan = ? WHERE id_potongan = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, potongan.getNamaPotongan());
            stmt.setDouble(2, potongan.getJumlahPotongan());
            stmt.setInt(3, potongan.getIdPotongan());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating Potongan: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
        }
    }

    /**
     * Menghapus data potongan dari database berdasarkan ID.
     *
     * @param idPotongan ID potongan yang akan dihapus.
     * @return true jika berhasil dihapus, false jika gagal.
     */
    public boolean deletePotongan(int idPotongan) {
        String sql = "DELETE FROM Potongan WHERE id_potongan = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPotongan);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting Potongan: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) { System.err.println("Error closing statement: " + e.getMessage()); } }
        }
    }
}