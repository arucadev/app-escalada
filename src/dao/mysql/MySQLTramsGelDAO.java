package dao.mysql;

import dao.interfaceDAO.DAO;
import model.TramsGel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLTramsGelDAO implements DAO<TramsGel, Integer> {
    private Connection connection;

    @Override
    public void createTable(TramsGel tramsGel) {
        String sql = "INSERT INTO TramsGel (idTramGel, llarg, grauDificultat, idViaGel) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tramsGel.getIdTramGel());
            stmt.setInt(2, tramsGel.getLlarg());
            stmt.setString(3, tramsGel.getGrauDificultat());
            stmt.setInt(4, tramsGel.getIdViaGel());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readTable(Integer id) {
        String sql = "SELECT * FROM TramsGel WHERE idTramGel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID Tram Gel: " + rs.getInt("idTramGel"));
                System.out.println("Llarg: " + rs.getInt("llarg"));
                System.out.println("Grau Dificultat: " + rs.getString("grauDificultat"));
                System.out.println("ID Via Gel: " + rs.getInt("idViaGel"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTable(TramsGel tramsGel) {
        String sql = "UPDATE TramsGel SET llarg = ?, grauDificultat = ?, idViaGel = ? WHERE idTramGel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tramsGel.getLlarg());
            stmt.setString(2, tramsGel.getGrauDificultat());
            stmt.setInt(3, tramsGel.getIdViaGel());
            stmt.setInt(4, tramsGel.getIdTramGel());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM TramsGel WHERE idTramGel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}