package dao.SQLite;

import dao.interfaceDAO.DAO;
import model.Via;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteViaDAO implements DAO<Via, Integer> {
    public static Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertTable(Via via) {
        String sql = "INSERT INTO vies (id_via, nom, orientacio, estat, ancoratges, tipus_roca, id_creador, id_sector) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, via.getIdVia());
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
        String sql = "SELECT * FROM vies WHERE id_via = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.printf("%-10s %-20s %-15s %-10s %-15s %-15s %-10s %-10s%n",
                        "ID Via", "Nom", "Orientacio", "Estat", "Ancoratges", "Tipus De Roca", "ID Creador", "ID Sector");
                System.out.println("---------------------------------------------------------------------------------------------");
                System.out.printf("%-10d %-20s %-15s %-10s %-15s %-15s %-10d %-10d%n",
                        rs.getInt("id_via"),
                        rs.getString("nom"),
                        rs.getString("orientacio"),
                        rs.getString("estat"),
                        rs.getString("ancoratges"),
                        rs.getString("tipus_roca"),
                        rs.getInt("id_creador"),
                        rs.getInt("id_sector"));
            } else {
                System.out.println("La via amb ID " + id + " no existeix.");
            }
        } catch (SQLException e) {
            System.err.println("Error al llegir la via: " + e.getMessage());
        }
    }

    @Override
    public void updateTable(Via via) {
        String sql = "UPDATE vies SET nom = ?, orientacio = ?, estat = ?, ancoratges = ?, tipus_roca = ?, id_creador = ?, id_sector = ? WHERE id_via = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, via.getNom());
            stmt.setString(2, via.getOrientacio());
            stmt.setString(3, via.getEstat());
            stmt.setString(4, via.getAncoratges());
            stmt.setString(5, via.getTipusDeRoca());
            stmt.setInt(6, via.getIdCreador());
            stmt.setInt(7, via.getIdSector());
            stmt.setInt(8, via.getIdVia());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM vies WHERE id_via = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readAll() {
        String sql = "SELECT * FROM vies";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID Via: " + rs.getInt("id_via"));
                System.out.println("Nom: " + rs.getString("nom"));
                System.out.println("Orientacio: " + rs.getString("orientacio"));
                System.out.println("Estat: " + rs.getString("estat"));
                System.out.println("Ancoratges: " + rs.getString("ancoratges"));
                System.out.println("Tipus De Roca: " + rs.getString("tipus_roca"));
                System.out.println("ID Creador: " + rs.getInt("id_creador"));
                System.out.println("ID Sector: " + rs.getInt("id_sector"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cercarViesPerDificultat(String minGrau, String maxGrau) {
        String sql = "SELECT v.nom AS nom_via, v.grau_dificultat, s.nom AS nom_sector, e.nom AS nom_escola " +
                "FROM vies v " +
                "JOIN sectors s ON v.id_sector = s.id_sector " +
                "JOIN escoles e ON s.id_escola = e.id_escola " +
                "WHERE v.grau_dificultat BETWEEN ? AND ? " +
                "ORDER BY v.grau_dificultat";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, minGrau);
            stmt.setString(2, maxGrau);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.printf("%-30s %-15s %-20s %-20s%n", "Nom Via", "Grau", "Nom Sector", "Nom Escola");
                System.out.println("------------------------------------------------------------------------------------");
                boolean hasResults = false;
                while (rs.next()) {
                    hasResults = true;
                    System.out.printf("%-30s %-15s %-20s %-20s%n",
                            rs.getString("nom_via"),
                            rs.getString("grau_dificultat"),
                            rs.getString("nom_sector"),
                            rs.getString("nom_escola"));
                }
                if (!hasResults) {
                    System.out.println("No s'han trobat vies en aquest rang de dificultat.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al cercar vies per dificultat: " + e.getMessage());
        }
    }

    public static void cercarViesPerEstat(String estat) {
        String sql = "SELECT id_via, nom, estat, orientacio, ancoratges, tipus_roca, id_creador, id_sector FROM vies WHERE estat = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, estat);
            ResultSet rs = stmt.executeQuery();

            // Print table header
            System.out.printf("%-10s %-20s %-15s %-15s %-15s %-15s %-10s %-10s%n",
                    "ID Via", "Nom", "Estat", "Orientacio", "Ancoratges", "Tipus Roca", "ID Creador", "ID Sector");
            System.out.println("------------------------------------------------------------------------------------------------------");

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                // Print each row
                System.out.printf("%-10d %-20s %-15s %-15s %-15s %-15s %-10d %-10d%n",
                        rs.getInt("id_via"),
                        rs.getString("nom"),
                        rs.getString("estat"),
                        rs.getString("orientacio"),
                        rs.getString("ancoratges"),
                        rs.getString("tipus_roca"),
                        rs.getInt("id_creador"),
                        rs.getInt("id_sector"));
            }

            if (!hasResults) {
                System.out.println("No s'han trobat vies amb l'estat especificat.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cercar vies per estat: " + e.getMessage());
        }
    }
}