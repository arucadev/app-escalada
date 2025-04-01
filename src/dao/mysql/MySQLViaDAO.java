package dao.mysql;

import dao.interfaceDAO.DAO;
import model.Via;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLViaDAO implements DAO<Via, Integer> {
    private Connection connection;

    @Override
    public void createTable(Via via) {
        String sql = "INSERT INTO Via (idVia, nom, orientacio, estat, ancoratges, tipusDeRoca, idCreador, idSector) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, via.getId());
            stmt.setString(2, via.getNom());
            stmt.setString(3, via.getOrientacio());
            stmt.setString(4, via.getEstat());
            stmt.setString(5, via.getAncoratges());
            stmt.setString(6, via.getTipusDeRoca());
            stmt.setInt(7, via.getIdCreador());
            stmt.setInt(8, via.getIdSector());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readTable(Integer id) {
        String sql = "SELECT * FROM Via WHERE idVia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID Via: " + rs.getInt("idVia"));
                System.out.println("Nom: " + rs.getString("nom"));
                System.out.println("Orientacio: " + rs.getString("orientacio"));
                System.out.println("Estat: " + rs.getString("estat"));
                System.out.println("Ancoratges: " + rs.getString("ancoratges"));
                System.out.println("Tipus De Roca: " + rs.getString("tipusDeRoca"));
                System.out.println("ID Creador: " + rs.getInt("idCreador"));
                System.out.println("ID Sector: " + rs.getInt("idSector"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTable(Via via) {
        String sql = "UPDATE Via SET nom = ?, orientacio = ?, estat = ?, ancoratges = ?, tipusDeRoca = ?, idCreador = ?, idSector = ? WHERE idVia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, via.getNom());
            stmt.setString(2, via.getOrientacio());
            stmt.setString(3, via.getEstat());
            stmt.setString(4, via.getAncoratges());
            stmt.setString(5, via.getTipusDeRoca());
            stmt.setInt(6, via.getIdCreador());
            stmt.setInt(7, via.getIdSector());
            stmt.setInt(8, via.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM Via WHERE idVia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}