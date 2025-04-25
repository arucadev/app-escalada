package dao.mysql;

import dao.interfaceDAO.DAO;
import model.Registre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteRegistreDAO implements DAO<Registre, Integer> {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertTable(Registre registre) {
        String sql = "INSERT INTO registres (id_registre, data_ascensio, estil, id_escalador, id_via) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, registre.getIdHistorial());
            stmt.setString(2, registre.getDataAscensio());
            stmt.setString(3, registre.getEstil());
            stmt.setInt(4, registre.getIdEscalador());
            stmt.setInt(5, registre.getIdVia());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readTable(Integer id) {
        String sql = "SELECT * FROM registres WHERE id_registre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID Registre: " + rs.getInt("id_registre"));
                System.out.println("Data Ascensio: " + rs.getString("data_ascensio"));
                System.out.println("Estil: " + rs.getString("estil"));
                System.out.println("ID Escalador: " + rs.getInt("id_escalador"));
                System.out.println("ID Via: " + rs.getInt("id_via"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTable(Registre registre) {
        String sql = "UPDATE registres SET data_ascensio = ?, estil = ?, id_escalador = ?, id_via = ? WHERE id_registre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, registre.getDataAscensio());
            stmt.setString(2, registre.getEstil());
            stmt.setInt(3, registre.getIdEscalador());
            stmt.setInt(4, registre.getIdVia());
            stmt.setInt(5, registre.getIdHistorial());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM registres WHERE id_registre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}