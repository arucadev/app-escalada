package dao.mysql;

import dao.interfaceDAO.DAO;
import model.objectes.Sector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLSectorDAO implements DAO<Sector, Integer> {
    private Connection connection;

    public MySQLSectorDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable(Sector sector) {
        String sql = "INSERT INTO Sector (nom, coordenades, aproximacio, numVies, popularitat, restriccions) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sector.getNom());
            stmt.setInt(2, sector.getcoordenades());
            stmt.setString(3, sector.getAproximacio());
            stmt.setInt(4, sector.getNumVies());
            stmt.setString(5, sector.getPopularitat());
            stmt.setString(6, sector.getRestriccions());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readTable(Integer id) {
        String sql = "SELECT * FROM Sector WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nom: " + rs.getString("nom"));
                System.out.println("Coordenades: " + rs.getInt("coordenades"));
                System.out.println("Aproximacio: " + rs.getString("aproximacio"));
                System.out.println("Num Vies: " + rs.getInt("numVies"));
                System.out.println("Popularitat: " + rs.getString("popularitat"));
                System.out.println("Restriccions: " + rs.getString("restriccions"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uptadeTable(Sector sector) {
        String sql = "UPDATE Sector SET nom = ?, coordenades = ?, aproximacio = ?, numVies = ?, popularitat = ?, restriccions = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sector.getNom());
            stmt.setInt(2, sector.getcoordenades());
            stmt.setString(3, sector.getAproximacio());
            stmt.setInt(4, sector.getNumVies());
            stmt.setString(5, sector.getPopularitat());
            stmt.setString(6, sector.getRestriccions());
            stmt.setInt(7, sector.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM Sector WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}