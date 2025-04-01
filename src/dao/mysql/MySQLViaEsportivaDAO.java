package dao.mysql;

import dao.interfaceDAO.DAO;
import model.ViaEsportiva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLViaEsportivaDAO implements DAO<ViaEsportiva, Integer> {
    private Connection connection;

    @Override
    public void createTable(ViaEsportiva viaEsportiva) {
        String sql = "INSERT INTO ViaEsportiva (idViaEsportiva, ancoratgesPermesos, grauDificultat) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, viaEsportiva.getIdViaEsportiva());
            stmt.setString(2, viaEsportiva.getAncoratgesPermesos());
            stmt.setString(3, viaEsportiva.getGrauDificultat());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readTable(Integer id) {
        String sql = "SELECT * FROM ViaEsportiva WHERE idViaEsportiva = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID Via Esportiva: " + rs.getInt("idViaEsportiva"));
                System.out.println("Ancoratges Permesos: " + rs.getString("ancoratgesPermesos"));
                System.out.println("Grau Dificultat: " + rs.getString("grauDificultat"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTable(ViaEsportiva viaEsportiva) {
        String sql = "UPDATE ViaEsportiva SET ancoratgesPermesos = ?, grauDificultat = ? WHERE idViaEsportiva = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, viaEsportiva.getAncoratgesPermesos());
            stmt.setString(2, viaEsportiva.getGrauDificultat());
            stmt.setInt(3, viaEsportiva.getIdViaEsportiva());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM ViaEsportiva WHERE idViaEsportiva = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}