package dao.SQLite;

import dao.interfaceDAO.DAO;
import model.Escola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteEscolaDAO implements DAO<Escola, Integer> {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertTable(Escola escola) {
        String sql = "INSERT INTO escoles (id_escola, nom, aproximacio, num_vies, popularitat, restriccions, id_poblacio) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, escola.getIdEscola());
            stmt.setString(2, escola.getNom());
            stmt.setString(3, escola.getAproximacio());
            stmt.setInt(4, escola.getNumVies());
            stmt.setString(5, escola.getPopularitat());
            stmt.setString(6, escola.getRestriccions());
            stmt.setInt(7, escola.getIdPoblacio());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readTable(Integer id) {
        String sql = "SELECT id_escola, nom, aproximacio, num_vies, popularitat, restriccions, id_poblacio FROM escoles WHERE id_escola = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.printf("%-5d %-20s %-40s %-10d %-15s %-30s %-10d%n",
                        "ID", "Nom", "Aproximacio", "Num Vies", "Popularitat", "Restriccions", "ID Poblacio");
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.printf("%-10d %-20s %-15s %-10d %-15s %-15s %-10d%n",
                        rs.getInt("id_escola"),
                        rs.getString("nom"),
                        rs.getString("aproximacio"),
                        rs.getInt("num_vies"),
                        rs.getString("popularitat"),
                        rs.getString("restriccions"),
                        rs.getInt("id_poblacio"));
            } else {
                System.out.println("L'escola amb ID " + id + " no existeix.");
            }
        } catch (SQLException e) {
            System.err.println("Error al llegir l'escola: " + e.getMessage());
        }
    }

    @Override
    public void updateTable(Escola escola) {
        String sql = "UPDATE escoles SET nom = ?, aproximacio = ?, num_vies = ?, popularitat = ?, restriccions = ?, id_poblacio = ? WHERE id_escola = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, escola.getNom());
            stmt.setString(2, escola.getAproximacio());
            stmt.setInt(3, escola.getNumVies());
            stmt.setString(4, escola.getPopularitat());
            stmt.setString(5, escola.getRestriccions());
            stmt.setInt(6, escola.getIdPoblacio());
            stmt.setInt(7, escola.getIdEscola());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(Integer id) {
        String sql = "DELETE FROM escoles WHERE id_escola = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readAll() {
        String sql = "SELECT * FROM escoles";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID Escola: " + rs.getInt("id_escola"));
                System.out.println("Nom: " + rs.getString("nom"));
                System.out.println("Aproximacio: " + rs.getString("aproximacio"));
                System.out.println("Num Vies: " + rs.getInt("numero de vies"));
                System.out.println("Popularitat: " + rs.getString("popularitat"));
                System.out.println("Restriccions: " + rs.getString("restriccions"));
                System.out.println("ID Poblacio: " + rs.getInt("id_poblacio"));
                System.out.println("----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostrarViesDisponibles(Integer id) {
        String sql = "SELECT v.id_sector, v.id_via, v.nom FROM escoles e" +
                " JOIN sectors s ON s.id_escola = e.id_escola" +
                " JOIN vies v ON v.id_sector = s.id_sector" +
                " WHERE v.estat = 'Apte' AND e.id_escola = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            boolean hasResults = false;

            // Encabezado de la tabla
            System.out.printf("%-10s %-10s %-30s%n", "ID Sector", "ID Via", "Nom Via");
            System.out.println("----------------------------------------------------");

            while (rs.next()) {
                hasResults = true;
                // Filas de datos
                System.out.printf("%-10d %-10d %-30s%n",
                        rs.getInt("id_sector"),
                        rs.getInt("id_via"),
                        rs.getString("nom"));
            }

            if (!hasResults) {
                System.out.println("No hi ha vies disponibles per a aquesta escola.");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtenir les vies disponibles: " + e.getMessage());
        }
    }

    public void consultarEscolesAmbRestriccionsActives() {
        String sql = "SELECT id_escola, nom, restriccions FROM escoles WHERE restriccions != 'Cap restricció'";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Print table header
            System.out.printf("%-10s %-30s %-30s%n", "ID Escola", "Nom", "Restriccions");
            System.out.println("-------------------------------------------------------------------");

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                // Print each row
                System.out.printf("%-10d %-30s %-30s%n",
                        rs.getInt("id_escola"),
                        rs.getString("nom"),
                        rs.getString("restriccions"));
            }

            if (!hasResults) {
                System.out.println("No hi ha escoles amb restriccions actives actualment.");
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar escoles amb restriccions actives: " + e.getMessage());
        }
    }
}