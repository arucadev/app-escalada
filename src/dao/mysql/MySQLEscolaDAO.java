package dao.mysql;

import dao.interfaceDAO.DAO;
import model.objectes.Escola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLEscolaDAO implements DAO<Escola, Integer> {
    private Connection connection;

    public MySQLEscolaDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable(Escola escola) {
        String sql = "INSERT INTO Escola (nom, lloc, coordenades, aproximacio, numVies, popularitat, restriccions) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, escola.getNom());
            stmt.setString(2, escola.getLloc());
            stmt.setInt(3, escola.getcoordenades());
            stmt.setString(4, escola.getAproximacio());
            stmt.setInt(5, escola.getNumVies());
            stmt.setString(6, escola.getPopularitat());
            stmt.setString(7, escola.getRestriccions());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readTable(Integer id) {
        String sql = "SELECT * FROM Escola WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nom: " + rs.getString("nom"));
                System.out.println("Lloc: " + rs.getString("lloc"));
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
    public void uptadeTable(Escola escola) {
        String sql = "UPDATE Escola SET nom = ?, lloc = ?, coordenades = ?, aproximacio = ?, numVies = ?, popularitat = ?, restriccions = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, escola.getNom());
            stmt.setString(2, escola.getLloc());
            stmt.setInt(3, escola.getcoordenades());
            stmt.setString(4, escola.getAproximacio());
            stmt.setInt(5, escola.getNumVies());
            stmt.setString(6, escola.getPopularitat());
            stmt.setString(7, escola.getRestriccions());
            stmt.setInt(8, escola.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM Escola WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}