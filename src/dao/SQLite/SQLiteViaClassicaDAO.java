package dao.mysql;

import dao.interfaceDAO.DAO;
import model.ViaClassica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteViaClassicaDAO implements DAO<ViaClassica, Integer> {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable(ViaClassica viaClassica) {
        String sql = "INSERT INTO vies_classica (id_via_classica, llargada_total, ancoratges_permesos, id_via) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, viaClassica.getIdViaClassica());
            stmt.setInt(2, viaClassica.getLlargadaTotal());
            stmt.setString(3, viaClassica.getAncoratgesPermesos());
            stmt.setInt(4, viaClassica.getIdVia());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readTable(Integer id) {
        String sql = "SELECT * FROM vies_classica WHERE id_via_classica = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID Via Classica: " + rs.getInt("id_via_classica"));
                System.out.println("Llargada Total: " + rs.getInt("llargada_total"));
                System.out.println("Ancoratges Permesos: " + rs.getString("ancoratges_permesos"));
                System.out.println("ID Via: " + rs.getInt("id_via"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTable(ViaClassica viaClassica) {
        String sql = "UPDATE vies_classica SET llargada_total = ?, ancoratges_permesos = ?, id_via = ? WHERE id_via_classica = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, viaClassica.getLlargadaTotal());
            stmt.setString(2, viaClassica.getAncoratgesPermesos());
            stmt.setInt(3, viaClassica.getIdVia());
            stmt.setInt(4, viaClassica.getIdViaClassica());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM vies_classica WHERE id_via_classica = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}