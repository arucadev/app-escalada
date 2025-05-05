package dao.SQLite;

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
    public void insertTable(Escalador escalador) {
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
                System.out.printf("%-5s %-20s %-15s %-5s %-10s %-15s %-10s %-10s%n",
                        "ID", "Nom", "Alias", "Edat", "Nivell Max", "Estil Preferit", "Fita", "ID Via Max");
                System.out.println("--------------------------------------------------------------------------------------------");
                System.out.printf("%-5d %-20s %-15s %-5d %-10s %-15s %-10s %-10d%n",
                        rs.getInt("id_escalador"),
                        rs.getString("nom"),
                        rs.getString("alias"),
                        rs.getInt("edat"),
                        rs.getString("nivell_max"),
                        rs.getString("estil_preferit"),
                        rs.getString("fita") == null ? "null" : rs.getString("fita"),
                        rs.getInt("id_via_max"));
            } else {
                System.out.println("El escalador con ID " + id + " no existe.");
            }
        } catch (SQLException e) {
            System.err.println("Error al leer el escalador: " + e.getMessage());
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

    @Override
    public void readAll() {
        String sql = "SELECT * FROM escaladors";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_escalador"));
                System.out.println("Nom: " + rs.getString("nom"));
                System.out.println("Alias: " + rs.getString("alias"));
                System.out.println("Edat: " + rs.getInt("edat"));
                System.out.println("Nivell Max: " + rs.getString("nivell_max"));
                System.out.println("Estil Preferit: " + rs.getString("estil_preferit"));
                System.out.println("Fita: " + rs.getString("fita"));
                System.out.println("ID Via Max: " + rs.getInt("id_via_max"));
                System.out.println("----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}