package dao.SQLite;

import dao.interfaceDAO.DAO;
import model.Poblacio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLitePoblacioDAO implements DAO<Poblacio, Integer> {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertTable(Poblacio poblacio) {
        String sql = "INSERT INTO poblacions (id_poblacio, nom) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, poblacio.getIdPoblacio());
            stmt.setString(2, poblacio.getNom());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readTable(Integer id) {
        String sql = "SELECT * FROM poblacions WHERE id_poblacio = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.printf("%-5s %-20s%n", "ID", "Nom");
                System.out.println("----------------------------");
                System.out.printf("%-5d %-20s%n",
                        rs.getInt("id_poblacio"),
                        rs.getString("nom"));
            } else {
                System.out.println("La població amb ID " + id + " no existeix.");
            }
        } catch (SQLException e) {
            System.err.println("Error al llegir la població: " + e.getMessage());
        }
    }

    @Override
    public void updateTable(Poblacio poblacio) {
        String sql = "UPDATE poblacions SET nom = ? WHERE id_poblacio = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, poblacio.getNom());
            stmt.setInt(2, poblacio.getIdPoblacio());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM poblacions WHERE id_poblacio = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readAll() {
        String sql = "SELECT * FROM poblacions";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID Poblacio: " + rs.getInt("id_poblacio"));
                System.out.println("Nom: " + rs.getString("nom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}