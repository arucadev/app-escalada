package dao.mysql;

import dao.interfaceDAO.DAO;
import model.Sector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteSectorDAO implements DAO<Sector, Integer> {
    private Connection connection;

    @Override
    public void createTable(Sector sector) {
        String sql = "INSERT INTO sectors (id_sector, nom, coordenades_lat, coordenades_long, aproximacio, popularitat, restriccions, id_escola) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, sector.getIdSector());
            stmt.setString(2, sector.getNom());
            stmt.setInt(3, sector.getCoordenadesLat());
            stmt.setInt(4, sector.getCoordenadesLong());
            stmt.setString(5, sector.getAproximacio());
            stmt.setInt(6, sector.getPopularitat());
            stmt.setString(7, sector.getRestriccions());
            stmt.setInt(8, sector.getIdEscola());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readTable(Integer id) {
        String sql = "SELECT * FROM sectors WHERE id_sector = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID Sector: " + rs.getInt("id_sector"));
                System.out.println("Nom: " + rs.getString("nom"));
                System.out.println("Coordenades Lat: " + rs.getInt("coordenades_lat"));
                System.out.println("Coordenades Long: " + rs.getInt("coordenades_long"));
                System.out.println("Aproximacio: " + rs.getString("aproximacio"));
                System.out.println("Popularitat: " + rs.getInt("popularitat"));
                System.out.println("Restriccions: " + rs.getString("restriccions"));
                System.out.println("ID Escola: " + rs.getInt("id_escola"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTable(Sector sector) {
        String sql = "UPDATE sectors SET nom = ?, coordenades_lat = ?, coordenades_long = ?, aproximacio = ?, popularitat = ?, restriccions = ?, id_escola = ? WHERE id_sector = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sector.getNom());
            stmt.setInt(2, sector.getCoordenadesLat());
            stmt.setInt(3, sector.getCoordenadesLong());
            stmt.setString(4, sector.getAproximacio());
            stmt.setInt(5, sector.getPopularitat());
            stmt.setString(6, sector.getRestriccions());
            stmt.setInt(7, sector.getIdEscola());
            stmt.setInt(8, sector.getIdSector());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM sectors WHERE id_sector = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}