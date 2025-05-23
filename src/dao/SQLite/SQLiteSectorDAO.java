package dao.SQLite;

import dao.interfaceDAO.DAO;
import model.Sector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteSectorDAO implements DAO<Sector, Integer> {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertTable(Sector sector) {
        String sql = "INSERT INTO sectors (id_sector, nom, coordenades_lat, coordenades_long, aproximacio, popularitat, restriccions, id_escola) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, sector.getIdSector());
            stmt.setString(2, sector.getNom());
            stmt.setDouble(3, sector.getCoordenadesLat());
            stmt.setDouble(4, sector.getCoordenadesLong());
            stmt.setString(5, sector.getAproximacio());
            stmt.setString(6, sector.getPopularitat());
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
                System.out.printf("%-10s %-20s %-15s %-15s %-15s %-10s %-15s %-10s%n",
                        "ID", "Nom", "Latitud", "Longitud", "Aproximacio", "Popularitat", "Restriccions", "ID Escola");
                System.out.println("-----------------------------------------------------------------------------------------------");
                System.out.printf("%-10d %-20s %-15d %-15d %-15s %-10d %-15s %-10d%n",
                        rs.getInt("id_sector"),
                        rs.getString("nom"),
                        rs.getInt("coordenades_lat"),
                        rs.getInt("coordenades_long"),
                        rs.getString("aproximacio"),
                        rs.getInt("popularitat"),
                        rs.getString("restriccions") == null ? "null" : rs.getString("restriccions"),
                        rs.getInt("id_escola"));
            } else {
                System.out.println("El sector amb ID " + id + " no existeix.");
            }
        } catch (SQLException e) {
            System.err.println("Error al llegir el sector: " + e.getMessage());
        }
    }

    @Override
    public void updateTable(Sector sector) {
        String sql = "UPDATE sectors SET nom = ?, coordenades_lat = ?, coordenades_long = ?, aproximacio = ?, popularitat = ?, restriccions = ?, id_escola = ? WHERE id_sector = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sector.getNom());
            stmt.setDouble(2, sector.getCoordenadesLat());
            stmt.setDouble(3, sector.getCoordenadesLong());
            stmt.setString(4, sector.getAproximacio());
            stmt.setString(5, sector.getPopularitat());
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

    @Override
    public void readAll() {
        String sql = "SELECT * FROM sectors";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
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
}