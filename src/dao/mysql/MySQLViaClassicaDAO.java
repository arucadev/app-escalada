package dao.mysql;

import dao.interfaceDAO.DAO;
import model.ViaClassica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLViaClassicaDAO implements DAO<ViaClassica, Integer> {
    private Connection connection;

    @Override
    public void createTable(ViaClassica viaClassica) {
        String sql = "INSERT INTO ViaClassica (idViaClassica, llargadaTotal, ancoratgesPermesos, grauDificultat) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, viaClassica.getIdViaClassica());
            stmt.setInt(2, viaClassica.getLlargadaTotal());
            stmt.setString(3, viaClassica.getAncoratgesPermesos());
            stmt.setString(4, viaClassica.getGrauDificultat());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readTable(Integer id) {
        String sql = "SELECT * FROM ViaClassica WHERE idViaClassica = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID Via Classica: " + rs.getInt("idViaClassica"));
                System.out.println("Llargada Total: " + rs.getInt("llargadaTotal"));
                System.out.println("Ancoratges Permesos: " + rs.getString("ancoratgesPermesos"));
                System.out.println("Grau Dificultat: " + rs.getString("grauDificultat"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTable(ViaClassica viaClassica) {
        String sql = "UPDATE ViaClassica SET llargadaTotal = ?, ancoratgesPermesos = ?, grauDificultat = ? WHERE idViaClassica = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, viaClassica.getLlargadaTotal());
            stmt.setString(2, viaClassica.getAncoratgesPermesos());
            stmt.setString(3, viaClassica.getGrauDificultat());
            stmt.setInt(4, viaClassica.getIdViaClassica());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM ViaClassica WHERE idViaClassica = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}