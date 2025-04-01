package dao.mysql;

import dao.interfaceDAO.DAO;
import model.TramsClassica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLTramsClassicaDAO implements DAO<TramsClassica, Integer> {
    private Connection connection;

    @Override
    public void createTable(TramsClassica tramsClassica) {
        String sql = "INSERT INTO TramsClassica (idTramClassica, llarg, grauDificultat, idViaClassica) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tramsClassica.getIdTramClassica());
            stmt.setInt(2, tramsClassica.getLlarg());
            stmt.setString(3, tramsClassica.getGrauDificultat());
            stmt.setInt(4, tramsClassica.getIdViaClassica());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readTable(Integer id) {
        String sql = "SELECT * FROM TramsClassica WHERE idTramClassica = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID Tram Classica: " + rs.getInt("idTramClassica"));
                System.out.println("Llarg: " + rs.getInt("llarg"));
                System.out.println("Grau Dificultat: " + rs.getString("grauDificultat"));
                System.out.println("ID Via Classica: " + rs.getInt("idViaClassica"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTable(TramsClassica tramsClassica) {
        String sql = "UPDATE TramsClassica SET llarg = ?, grauDificultat = ?, idViaClassica = ? WHERE idTramClassica = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tramsClassica.getLlarg());
            stmt.setString(2, tramsClassica.getGrauDificultat());
            stmt.setInt(3, tramsClassica.getIdViaClassica());
            stmt.setInt(4, tramsClassica.getIdTramClassica());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM TramsClassica WHERE idTramClassica = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}