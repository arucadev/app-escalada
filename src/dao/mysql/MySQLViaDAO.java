package dao.mysql;

import dao.interfaceDAO.DAO;
import model.objectes.Via;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLViaDAO implements DAO<Via, Integer> {
    private Connection connection;

    public MySQLViaDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable(Via via) {
        String sql = "INSERT INTO Via (id, nom, Llargada, grauDificultat, orientacio, estat, lloc, coordendes, ancoratges, tipusDeRoca, creadaPer) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, via.getId());
            stmt.setString(2, via.getNom());
            stmt.setInt(3, via.getLlargada());
            stmt.setString(4, via.getGrauDificultat());
            stmt.setString(5, via.getOrientacio());
            stmt.setString(6, via.getEstat());
            stmt.setString(7, via.getLloc());
            stmt.setInt(8, via.getCoordendes());
            stmt.setString(9, via.getAncoratges());
            stmt.setString(10, via.getTipusDeRoca());
            stmt.setString(11, via.getCreadaPer());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readTable(Integer id) {
        String sql = "SELECT * FROM Via WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nom: " + rs.getString("nom"));
                System.out.println("Llargada: " + rs.getInt("Llargada"));
                System.out.println("Grau Dificultat: " + rs.getString("grauDificultat"));
                System.out.println("Orientacio: " + rs.getString("orientacio"));
                System.out.println("Estat: " + rs.getString("estat"));
                System.out.println("Lloc: " + rs.getString("lloc"));
                System.out.println("Coordendes: " + rs.getInt("coordendes"));
                System.out.println("Ancoratges: " + rs.getString("ancoratges"));
                System.out.println("Tipus De Roca: " + rs.getString("tipusDeRoca"));
                System.out.println("Creada Per: " + rs.getString("creadaPer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uptadeTable(Via via) {
        String sql = "UPDATE Via SET nom = ?, Llargada = ?, grauDificultat = ?, orientacio = ?, estat = ?, lloc = ?, coordendes = ?, ancoratges = ?, tipusDeRoca = ?, creadaPer = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, via.getNom());
            stmt.setInt(2, via.getLlargada());
            stmt.setString(3, via.getGrauDificultat());
            stmt.setString(4, via.getOrientacio());
            stmt.setString(5, via.getEstat());
            stmt.setString(6, via.getLloc());
            stmt.setInt(7, via.getCoordendes());
            stmt.setString(8, via.getAncoratges());
            stmt.setString(9, via.getTipusDeRoca());
            stmt.setString(10, via.getCreadaPer());
            stmt.setInt(11, via.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM Via WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}