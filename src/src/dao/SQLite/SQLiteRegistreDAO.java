package dao.SQLite;

import dao.interfaceDAO.DAO;
import model.Registre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteRegistreDAO implements DAO<Registre, Integer> {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertTable(Registre registre) {
        String sql = "INSERT INTO registres (id_registre, data_ascensio, estil, id_escalador, id_via) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, registre.getIdHistorial());
            stmt.setString(2, registre.getDataAscensio());
            stmt.setString(3, registre.getEstil());
            stmt.setInt(4, registre.getIdEscalador());
            stmt.setInt(5, registre.getIdVia());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readTable(Integer id) {
        String sql = "SELECT * FROM registres WHERE id_registre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.printf("%-10s %-20s %-15s %-15s %-10s%n", "ID", "Data Ascensio", "Estil", "ID Escalador", "ID Via");
                System.out.println("---------------------------------------------------------------------");
                System.out.printf("%-10d %-20s %-15s %-15d %-10d%n",
                        rs.getInt("id_registre"),
                        rs.getString("data_ascensio"),
                        rs.getString("estil"),
                        rs.getInt("id_escalador"),
                        rs.getInt("id_via"));
            } else {
                System.out.println("El registre amb ID " + id + " no existeix.");
            }
        } catch (SQLException e) {
            System.err.println("Error al llegir el registre: " + e.getMessage());
        }
    }

    @Override
    public void updateTable(Registre registre) {
        String sql = "UPDATE registres SET data_ascensio = ?, estil = ?, id_escalador = ?, id_via = ? WHERE id_registre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, registre.getDataAscensio());
            stmt.setString(2, registre.getEstil());
            stmt.setInt(3, registre.getIdEscalador());
            stmt.setInt(4, registre.getIdVia());
            stmt.setInt(5, registre.getIdHistorial());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM registres WHERE id_registre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readAll() {
        String sql = "SELECT * FROM registres";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID Registre: " + rs.getInt("id_registre"));
                System.out.println("Data Ascensio: " + rs.getString("data_ascensio"));
                System.out.println("Estil: " + rs.getString("estil"));
                System.out.println("ID Escalador: " + rs.getInt("id_escalador"));
                System.out.println("ID Via: " + rs.getInt("id_via"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}