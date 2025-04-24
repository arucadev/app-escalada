package dao.mysql;

import dao.interfaceDAO.DAO;
import model.ViaGel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteViaGelDAO implements DAO<ViaGel, Integer> {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable(ViaGel viaGel) {
        String sql = "INSERT INTO vies_gel (id_via_gel, llargada_total, ancoratges_permesos, id_via) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, viaGel.getIdViaGel());
            stmt.setInt(2, viaGel.getLlargadaTotal());
            stmt.setString(3, viaGel.getAncoratgesPermesos());
            stmt.setInt(4, viaGel.getIdVia());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readTable(Integer id) {
        String sql = "SELECT * FROM vies_gel WHERE id_via_gel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID Via Gel: " + rs.getInt("id_via_gel"));
                System.out.println("Llargada Total: " + rs.getInt("llargada_total"));
                System.out.println("Ancoratges Permesos: " + rs.getString("ancoratges_permesos"));
                System.out.println("ID Via: " + rs.getInt("id_via"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTable(ViaGel viaGel) {
        String sql = "UPDATE vies_gel SET llargada_total = ?, ancoratges_permesos = ?, id_via = ? WHERE id_via_gel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, viaGel.getLlargadaTotal());
            stmt.setString(2, viaGel.getAncoratgesPermesos());
            stmt.setInt(3, viaGel.getIdVia());
            stmt.setInt(4, viaGel.getIdViaGel());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM vies_gel WHERE id_via_gel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}