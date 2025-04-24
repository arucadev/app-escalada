package dao.mysql;

import dao.interfaceDAO.DAO;
import model.Escalador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteEscaladorDAO implements DAO<Escalador, Integer> {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable(Escalador escalador) {
        String sql = "INSERT INTO escaladors (id_escalador, nom, alias, edat, nivell_max, estil_preferit, fita, id_via_max) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, escalador.getIdEscalador());
            stmt.setString(2, escalador.getNom());
            stmt.setString(3, escalador.getAlies());
            stmt.setInt(4, escalador.getEdat());
            stmt.setString(5, escalador.getNivellMax());
            stmt.setString(6, escalador.getEstilPreferit());
            stmt.setString(7, escalador.getFita());
            stmt.setInt(8, escalador.getIdViaMax());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readTable(Integer id) {
        String sql = "SELECT * FROM escaladors WHERE id_escalador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_escalador"));
                System.out.println("Nom: " + rs.getString("nom"));
                System.out.println("Alias: " + rs.getString("alias"));
                System.out.println("Edat: " + rs.getInt("edat"));
                System.out.println("Nivell Max: " + rs.getString("nivell_max"));
                System.out.println("Estil Preferit: " + rs.getString("estil_preferit"));
                System.out.println("Fita: " + rs.getString("fita"));
                System.out.println("ID Via Max: " + rs.getInt("id_via_max"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTable(Escalador escalador) {
        String sql = "UPDATE escaladors SET nom = ?, alias = ?, edat = ?, nivell_max = ?, estil_preferit = ?, fita = ?, id_via_max = ? WHERE id_escalador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, escalador.getNom());
            stmt.setString(2, escalador.getAlies());
            stmt.setInt(3, escalador.getEdat());
            stmt.setString(4, escalador.getNivellMax());
            stmt.setString(5, escalador.getEstilPreferit());
            stmt.setString(6, escalador.getFita());
            stmt.setInt(7, escalador.getIdViaMax());
            stmt.setInt(8, escalador.getIdEscalador());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM escaladors WHERE id_escalador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}