package dao.mysql;

import dao.interfaceDAO.DAO;
import model.Escola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLEscolaDAO implements DAO<Escola, Integer> {
    private Connection connection;

    @Override
    public void createTable(Escola escola) {
        String sql = "INSERT INTO Escola (idEscola, nom, aproximacio, numVies, popularitat, restriccions, idPoblacio) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, escola.getIdEscola());
            stmt.setString(2, escola.getNom());
            stmt.setString(3, escola.getAproximacio());
            stmt.setInt(4, escola.getNumVies());
            stmt.setString(5, escola.getPopularitat());
            stmt.setString(6, escola.getRestriccions());
            stmt.setInt(7, escola.getIdPoblacio());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readTable(Integer id) {
        String sql = "SELECT * FROM Escola WHERE idEscola = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID Escola: " + rs.getInt("idEscola"));
                System.out.println("Nom: " + rs.getString("nom"));
                System.out.println("Aproximacio: " + rs.getString("aproximacio"));
                System.out.println("Num Vies: " + rs.getInt("numVies"));
                System.out.println("Popularitat: " + rs.getString("popularitat"));
                System.out.println("Restriccions: " + rs.getString("restriccions"));
                System.out.println("ID Poblacio: " + rs.getInt("idPoblacio"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTable(Escola escola) {
        String sql = "UPDATE Escola SET nom = ?, aproximacio = ?, numVies = ?, popularitat = ?, restriccions = ?, idPoblacio = ? WHERE idEscola = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, escola.getNom());
            stmt.setString(2, escola.getAproximacio());
            stmt.setInt(3, escola.getNumVies());
            stmt.setString(4, escola.getPopularitat());
            stmt.setString(5, escola.getRestriccions());
            stmt.setInt(6, escola.getIdPoblacio());
            stmt.setInt(7, escola.getIdEscola());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM Escola WHERE idEscola = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}