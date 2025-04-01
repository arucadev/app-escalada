package dao.mysql;

import dao.interfaceDAO.DAO;
import model.objectes.Escalador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLEscaladorDAO implements DAO<Escalador, Integer> {
    private Connection connection;

    public MySQLEscaladorDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable(Escalador escalador) {
        String sql = "INSERT INTO Escalador (id, nom, alies, edat, nivell, nomDeLaVia, estilPreferit, historial, fita) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, escalador.getId());
            stmt.setString(2, escalador.getNom());
            stmt.setString(3, escalador.getAlies());
            stmt.setInt(4, escalador.getEdat());
            stmt.setString(5, escalador.getNivell());
            stmt.setString(6, escalador.getNomDeLaVia());
            stmt.setString(7, escalador.getEstilPreferit());
            stmt.setString(8, escalador.getHistorial());
            stmt.setString(9, escalador.getFita());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readTable(Integer id) {
        String sql = "SELECT * FROM Escalador WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nom: " + rs.getString("nom"));
                System.out.println("Alies: " + rs.getString("alies"));
                System.out.println("Edat: " + rs.getInt("edat"));
                System.out.println("Nivell: " + rs.getString("nivell"));
                System.out.println("Nom de la Via: " + rs.getString("nomDeLaVia"));
                System.out.println("Estil Preferit: " + rs.getString("estilPreferit"));
                System.out.println("Historial: " + rs.getString("historial"));
                System.out.println("Fita: " + rs.getString("fita"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uptadeTable(Escalador escalador) {
        String sql = "UPDATE Escalador SET nom = ?, alies = ?, edat = ?, nivell = ?, nomDeLaVia = ?, estilPreferit = ?, historial = ?, fita = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, escalador.getId());
            stmt.setString(2, escalador.getNom());
            stmt.setString(3, escalador.getAlies());
            stmt.setInt(4, escalador.getEdat());
            stmt.setString(5, escalador.getNivell());
            stmt.setString(6, escalador.getNomDeLaVia());
            stmt.setString(7, escalador.getEstilPreferit());
            stmt.setString(8, escalador.getHistorial());
            stmt.setString(9, escalador.getFita());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM Escalador WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}