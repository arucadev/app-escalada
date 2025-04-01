package dao.mysql;

import dao.interfaceDAO.DAO;
import model.ViaGel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLViaGelDAO implements DAO<ViaGel, Integer> {
    private Connection connection;

    @Override
    public void createTable(ViaGel viaGel) {
        String sql = "INSERT INTO ViaGel (idViaGel, grauDificultat, llargadaTotal, ancoratgesPermesos) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, viaGel.getIdViaGel());
            stmt.setString(2, viaGel.getGrauDificultat());
            stmt.setInt(3, viaGel.getLlargadaTotal());
            stmt.setString(4, viaGel.getAncoratgesPermesos());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readTable(Integer id) {
        String sql = "SELECT * FROM ViaGel WHERE idViaGel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID Via Gel: " + rs.getInt("idViaGel"));
                System.out.println("Grau Dificultat: " + rs.getString("grauDificultat"));
                System.out.println("Llargada Total: " + rs.getInt("llargadaTotal"));
                System.out.println("Ancoratges Permesos: " + rs.getString("ancoratgesPermesos"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTable(ViaGel viaGel) {
        String sql = "UPDATE ViaGel SET grauDificultat = ?, llargadaTotal = ?, ancoratgesPermesos = ? WHERE idViaGel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, viaGel.getGrauDificultat());
            stmt.setInt(2, viaGel.getLlargadaTotal());
            stmt.setString(3, viaGel.getAncoratgesPermesos());
            stmt.setInt(4, viaGel.getIdViaGel());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM ViaGel WHERE idViaGel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}