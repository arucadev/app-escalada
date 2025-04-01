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
        String sql = "INSERT INTO vies_esportiva (id_via_esportiva, llargada_total, ancoratges_permesos, grau_dificultat, id_via) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, viaEsportiva.getIdViaEsportiva());
            stmt.setInt(2, viaEsportiva.getLlargadaTotal());
            stmt.setString(3, viaEsportiva.getAncoratgesPermesos());
            stmt.setString(4, viaEsportiva.getGrauDificultat());
            stmt.setInt(5, viaEsportiva.getIdVia());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readTable(Integer id) {
        String sql = "SELECT * FROM vies_esportiva WHERE id_via_esportiva = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID Via Esportiva: " + rs.getInt("id_via_esportiva"));
                System.out.println("Llargada Total: " + rs.getInt("llargada_total"));
                System.out.println("Ancoratges Permesos: " + rs.getString("ancoratges_permesos"));
                System.out.println("Grau Dificultat: " + rs.getString("grau_dificultat"));
                System.out.println("ID Via: " + rs.getInt("id_via"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTable(ViaEsportiva viaEsportiva) {
        String sql = "UPDATE vies_esportiva SET llargada_total = ?, ancoratges_permesos = ?, grau_dificultat = ?, id_via = ? WHERE id_via_esportiva = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, viaEsportiva.getLlargadaTotal());
            stmt.setString(2, viaEsportiva.getAncoratgesPermesos());
            stmt.setString(3, viaEsportiva.getGrauDificultat());
            stmt.setInt(4, viaEsportiva.getIdVia());
            stmt.setInt(5, viaEsportiva.getIdViaEsportiva());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM vies_esportiva WHERE id_via_esportiva = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}