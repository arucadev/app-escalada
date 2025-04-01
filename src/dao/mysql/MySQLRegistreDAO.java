package dao.mysql;

import dao.interfaceDAO.DAO;
import model.Registre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLRegistreDAO implements DAO<Registre, Integer> {
    private Connection connection;

    @Override
    public void createTable(Registre registre) {
        String sql = "INSERT INTO Registre (idRegistre, idEscalador, idVia, dataAscensio, estil) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, registre.getIdHistorial());
            stmt.setInt(2, registre.getIdEscalador());
            stmt.setInt(3, registre.getIdVia());
            stmt.setString(4, registre.getDataAscensio());
            stmt.setString(5, registre.getEstil());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readTable(Integer id) {
        String sql = "SELECT * FROM Registre WHERE idRegistre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID Registre: " + rs.getInt("idRegistre"));
                System.out.println("ID Escalador: " + rs.getInt("idEscalador"));
                System.out.println("ID Via: " + rs.getInt("idVia"));
                System.out.println("Data Ascensio: " + rs.getString("dataAscensio"));
                System.out.println("Estil: " + rs.getString("estil"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTable(Registre registre) {
        String sql = "UPDATE Registre SET idEscalador = ?, idVia = ?, dataAscensio = ?, estil = ? WHERE idRegistre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, registre.getIdEscalador());
            stmt.setInt(2, registre.getIdVia());
            stmt.setString(3, registre.getDataAscensio());
            stmt.setString(4, registre.getEstil());
            stmt.setInt(5, registre.getIdHistorial());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM Registre WHERE idRegistre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}