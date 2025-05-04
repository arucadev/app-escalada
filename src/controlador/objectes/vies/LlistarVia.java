package controlador.objects.vies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LlistarVia {
    private Connection connection;
    private Scanner scanner;
    private AuxVia auxVia;

    public LlistarVia(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
        this.auxVia = new AuxVia(connection, scanner);
    }

    // Listar todas las vías
    public void llistarTotesLesVies() {
        try {
            System.out.println("\n=== LLISTA DE TOTES LES VIES ===");

            String sql = "SELECT v.id_via, v.nom, v.orientacio, v.estat, " +
                        "s.nom AS nom_sector, e.nom AS nom_escola " +
                        "FROM vies v " +
                        "JOIN sectors s ON v.id_sector = s.id_sector " +
                        "JOIN escoles e ON s.id_escola = e.id_escola " +
                        "ORDER BY e.nom, s.nom, v.nom";

            try (PreparedStatement stmt = connection.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                mostrarResultatVies(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al llistar les vies: " + e.getMessage());
        }
    }

    // Listar vías de una escuela
    public void llistarViesEscola() {
        try {
            System.out.print("\nIntrodueix el nom de l'escola: ");
            String nomEscola = scanner.nextLine().trim();

            if (nomEscola.isEmpty()) {
                System.out.println("El nom de l'escola no pot estar buit.");
                return;
            }

            String sql = "SELECT v.id_via, v.nom, v.orientacio, v.estat, " +
                        "s.nom AS nom_sector, e.nom AS nom_escola " +
                        "FROM vies v " +
                        "JOIN sectors s ON v.id_sector = s.id_sector " +
                        "JOIN escoles e ON s.id_escola = e.id_escola " +
                        "WHERE e.nom LIKE ? " +
                        "ORDER BY s.nom, v.nom";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, "%" + nomEscola + "%");
                ResultSet rs = stmt.executeQuery();

                System.out.println("\n=== VIES DE L'ESCOLA " + nomEscola + " ===");
                mostrarResultatVies(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al llistar les vies de l'escola: " + e.getMessage());
        }
    }

    // Listar vías por dificultad
    public void llistarViesPerDificultat() {
        try {
            System.out.print("\nIntrodueix el grau de dificultat: ");
            String grauDificultat = scanner.nextLine().trim();

            if (grauDificultat.isEmpty()) {
                System.out.println("El grau de dificultat no pot estar buit.");
                return;
            }

            String sqlEsportiva = "SELECT 'Esportiva' AS tipus, v.id_via, v.nom, ve.grau_dificultat, " +
                                 "s.nom AS nom_sector, e.nom AS nom_escola " +
                                 "FROM vies v " +
                                 "JOIN vies_esportiva ve ON v.id_via = ve.id_via " +
                                 "JOIN sectors s ON v.id_sector = s.id_sector " +
                                 "JOIN escoles e ON s.id_escola = e.id_escola " +
                                 "WHERE ve.grau_dificultat = ? " +
                                 "UNION " +
                                 "SELECT 'Clàssica' AS tipus, v.id_via, v.nom, vc.grau_dificultat, " +
                                 "s.nom AS nom_sector, e.nom AS nom_escola " +
                                 "FROM vies v " +
                                 "JOIN vies_classica vc ON v.id_via = vc.id_via " +
                                 "JOIN sectors s ON v.id_sector = s.id_sector " +
                                 "JOIN escoles e ON s.id_escola = e.id_escola " +
                                 "WHERE vc.grau_dificultat = ? " +
                                 "UNION " +
                                 "SELECT 'Gel' AS tipus, v.id_via, v.nom, vg.grau_dificultat, " +
                                 "s.nom AS nom_sector, e.nom AS nom_escola " +
                                 "FROM vies v " +
                                 "JOIN vies_gel vg ON v.id_via = vg.id_via " +
                                 "JOIN sectors s ON v.id_sector = s.id_sector " +
                                 "JOIN escoles e ON s.id_escola = e.id_escola " +
                                 "WHERE vg.grau_dificultat = ? " +
                                 "ORDER BY nom_escola, nom_sector, nom";

            try (PreparedStatement stmt = connection.prepareStatement(sqlEsportiva)) {
                stmt.setString(1, grauDificultat);
                stmt.setString(2, grauDificultat);
                stmt.setString(3, grauDificultat);
                ResultSet rs = stmt.executeQuery();

                System.out.println("\n=== VIES AMB DIFICULTAT " + grauDificultat + " ===");
                mostrarResultatViesDificultat(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al llistar les vies per dificultat: " + e.getMessage());
        }
    }

    // Listar vías por estado
    public void llistarViesPerEstat() {
        try {
            System.out.println("\nEstat disponibles: Equipant, En Revisió, Apte");
            System.out.print("Introdueix l'estat de les vies: ");
            String estat = scanner.nextLine().trim();

            if (!estat.equals("Equipant") && !estat.equals("En Revisió") && !estat.equals("Apte")) {
                System.out.println("Estat no vàlid. Ha de ser: Equipant, En Revisió o Apte.");
                return;
            }

            String sql = "SELECT v.id_via, v.nom, v.orientacio, v.estat, " +
                        "s.nom AS nom_sector, e.nom AS nom_escola, " +
                        "CASE " +
                        "   WHEN ve.id_via_esportiva IS NOT NULL THEN 'Esportiva' " +
                        "   WHEN vc.id_via_classica IS NOT NULL THEN 'Clàssica' " +
                        "   WHEN vg.id_via_gel IS NOT NULL THEN 'Gel' " +
                        "END AS tipus " +
                        "FROM vies v " +
                        "LEFT JOIN vies_esportiva ve ON v.id_via = ve.id_via " +
                        "LEFT JOIN vies_classica vc ON v.id_via = vc.id_via " +
                        "LEFT JOIN vies_gel vg ON v.id_via = vg.id_via " +
                        "JOIN sectors s ON v.id_sector = s.id_sector " +
                        "JOIN escoles e ON s.id_escola = e.id_escola " +
                        "WHERE v.estat = ? " +
                        "ORDER BY e.nom, s.nom, v.nom";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, estat);
                ResultSet rs = stmt.executeQuery();

                System.out.println("\n=== VIES EN ESTAT " + estat + " ===");
                mostrarResultatViesEstat(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al llistar les vies per estat: " + e.getMessage());
        }
    }

    // Listar vías recientemente aptas
    public void llistarViesAptesRecentment() {
        try {
            System.out.print("\nIntrodueix el nombre de dies (des d'avui cap enrere): ");
            int dies = Integer.parseInt(scanner.nextLine());

            if (dies <= 0) {
                System.out.println("El nombre de dies ha de ser positiu.");
                return;
            }

            String sql = "SELECT v.id_via, v.nom, v.orientacio, v.estat, " +
                        "s.nom AS nom_sector, e.nom AS nom_escola, " +
                        "CASE " +
                        "   WHEN ve.id_via_esportiva IS NOT NULL THEN 'Esportiva' " +
                        "   WHEN vc.id_via_classica IS NOT NULL THEN 'Clàssica' " +
                        "   WHEN vg.id_via_gel IS NOT NULL THEN 'Gel' " +
                        "END AS tipus, " +
                        "vh.data_canvi " +
                        "FROM vies v " +
                        "LEFT JOIN vies_esportiva ve ON v.id_via = ve.id_via " +
                        "LEFT JOIN vies_classica vc ON v.id_via = vc.id_via " +
                        "LEFT JOIN vies_gel vg ON v.id_via = vg.id_via " +
                        "JOIN sectors s ON v.id_sector = s.id_sector " +
                        "JOIN escoles e ON s.id_escola = e.id_escola " +
                        "JOIN (SELECT id_via, MAX(data_canvi) as data_canvi FROM vies_historic " +
                        "      WHERE estat_nou = 'Apte' GROUP BY id_via) vh ON v.id_via = vh.id_via " +
                        "WHERE v.estat = 'Apte' " +
                        "AND vh.data_canvi >= DATE_SUB(CURDATE(), INTERVAL ? DAY) " +
                        "ORDER BY vh.data_canvi DESC";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, dies);
                ResultSet rs = stmt.executeQuery();

                System.out.println("\n=== VIES APTES EN ELS ÚLTIMS " + dies + " DIES ===");
                mostrarResultatViesAptes(rs);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Format de número incorrecte.");
        } catch (SQLException e) {
            System.err.println("Error al llistar les vies aptes recentment: " + e.getMessage());
        }
    }

    // Listar vías más largas de una escuela
    public void llistarViesMesLlargues() {
        try {
            System.out.print("\nIntrodueix el nom de l'escola: ");
            String nomEscola = scanner.nextLine().trim();

            if (nomEscola.isEmpty()) {
                System.out.println("El nom de l'escola no pot estar buit.");
                return;
            }

            System.out.print("Nombre de vies a mostrar: ");
            int limit = Integer.parseInt(scanner.nextLine());

            if (limit <= 0) {
                System.out.println("El nombre de vies ha de ser positiu.");
                return;
            }

            String sql = "SELECT 'Esportiva' AS tipus, v.id_via, v.nom, ve.llargada_total, " +
                        "s.nom AS nom_sector, e.nom AS nom_escola " +
                        "FROM vies v " +
                        "JOIN vies_esportiva ve ON v.id_via = ve.id_via " +
                        "JOIN sectors s ON v.id_sector = s.id_sector " +
                        "JOIN escoles e ON s.id_escola = e.id_escola " +
                        "WHERE e.nom LIKE ? " +
                        "UNION " +
                        "SELECT 'Clàssica' AS tipus, v.id_via, v.nom, vc.llargada_total, " +
                        "s.nom AS nom_sector, e.nom AS nom_escola " +
                        "FROM vies v " +
                        "JOIN vies_classica vc ON v.id_via = vc.id_via " +
                        "JOIN sectors s ON v.id_sector = s.id_sector " +
                        "JOIN escoles e ON s.id_escola = e.id_escola " +
                        "WHERE e.nom LIKE ? " +
                        "UNION " +
                        "SELECT 'Gel' AS tipus, v.id_via, v.nom, vg.llargada_total, " +
                        "s.nom AS nom_sector, e.nom AS nom_escola " +
                        "FROM vies v " +
                        "JOIN vies_gel vg ON v.id_via = vg.id_via " +
                        "JOIN sectors s ON v.id_sector = s.id_sector " +
                        "JOIN escoles e ON s.id_escola = e.id_escola " +
                        "WHERE e.nom LIKE ? " +
                        "ORDER BY llargada_total DESC " +
                        "LIMIT ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, "%" + nomEscola + "%");
                stmt.setString(2, "%" + nomEscola + "%");
                stmt.setString(3, "%" + nomEscola + "%");
                stmt.setInt(4, limit);
                ResultSet rs = stmt.executeQuery();

                System.out.println("\n=== " + limit + " VIES MÉS LLARGUES DE L'ESCOLA " + nomEscola + " ===");
                mostrarResultatViesLlargada(rs);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Format de número incorrecte.");
        } catch (SQLException e) {
            System.err.println("Error al llistar les vies més llargues: " + e.getMessage());
        }
    }

    // Métodos auxiliares para mostrar resultados
    private void mostrarResultatVies(ResultSet rs) throws SQLException {
        boolean hayResultados = false;
        System.out.printf("%-6s %-25s %-15s %-12s %-20s %-20s\n",
                         "ID", "Nom", "Orientació", "Estat", "Sector", "Escola");
        System.out.println("--------------------------------------------------------------------------");

        while (rs.next()) {
            hayResultados = true;
            System.out.printf("%-6d %-25s %-15s %-12s %-20s %-20s\n",
                             rs.getInt("id_via"),
                             rs.getString("nom"),
                             rs.getString("orientacio"),
                             rs.getString("estat"),
                             rs.getString("nom_sector"),
                             rs.getString("nom_escola"));
        }

        if (!hayResultados) {
            System.out.println("No s'han trobat vies.");
        }
    }

    private void mostrarResultatViesDificultat(ResultSet rs) throws SQLException {
        boolean hayResultados = false;
        System.out.printf("%-10s %-6s %-25s %-12s %-15s %-20s\n",
                         "Tipus", "ID", "Nom", "Dificultat", "Sector", "Escola");
        System.out.println("--------------------------------------------------------------------------");

        while (rs.next()) {
            hayResultados = true;
            System.out.printf("%-10s %-6d %-25s %-12s %-15s %-20s\n",
                             rs.getString("tipus"),
                             rs.getInt("id_via"),
                             rs.getString("nom"),
                             rs.getString("grau_dificultat"),
                             rs.getString("nom_sector"),
                             rs.getString("nom_escola"));
        }

        if (!hayResultados) {
            System.out.println("No s'han trobat vies amb aquest grau de dificultat.");
        }
    }

    private void mostrarResultatViesEstat(ResultSet rs) throws SQLException {
        boolean hayResultados = false;
        System.out.printf("%-10s %-6s %-25s %-15s %-15s %-20s\n",
                         "Tipus", "ID", "Nom", "Sector", "Escola", "Estat");
        System.out.println("--------------------------------------------------------------------------");

        while (rs.next()) {
            hayResultados = true;
            System.out.printf("%-10s %-6d %-25s %-15s %-15s %-20s\n",
                             rs.getString("tipus"),
                             rs.getInt("id_via"),
                             rs.getString("nom"),
                             rs.getString("nom_sector"),
                             rs.getString("nom_escola"),
                             rs.getString("estat"));
        }

        if (!hayResultados) {
            System.out.println("No s'han trobat vies amb aquest estat.");
        }
    }

    private void mostrarResultatViesAptes(ResultSet rs) throws SQLException {
        boolean hayResultados = false;
        System.out.printf("%-10s %-6s %-25s %-15s %-15s %-12s\n",
                         "Tipus", "ID", "Nom", "Sector", "Escola", "Data Apte");
        System.out.println("--------------------------------------------------------------------------");

        while (rs.next()) {
            hayResultados = true;
            System.out.printf("%-10s %-6d %-25s %-15s %-15s %-12s\n",
                             rs.getString("tipus"),
                             rs.getInt("id_via"),
                             rs.getString("nom"),
                             rs.getString("nom_sector"),
                             rs.getString("nom_escola"),
                             rs.getDate("data_canvi"));
        }

        if (!hayResultados) {
            System.out.println("No s'han trobat vies aptes en aquest període.");
        }
    }

    private void mostrarResultatViesLlargada(ResultSet rs) throws SQLException {
        boolean hayResultados = false;
        System.out.printf("%-10s %-6s %-25s %-10s %-15s %-15s\n",
                         "Tipus", "ID", "Nom", "Llargada", "Sector", "Escola");
        System.out.println("--------------------------------------------------------------------------");

        while (rs.next()) {
            hayResultados = true;
            System.out.printf("%-10s %-6d %-25s %-10d %-15s %-15s\n",
                             rs.getString("tipus"),
                             rs.getInt("id_via"),
                             rs.getString("nom"),
                             rs.getInt("llargada_total"),
                             rs.getString("nom_sector"),
                             rs.getString("nom_escola"));
        }

        if (!hayResultados) {
            System.out.println("No s'han trobat vies per aquesta escola.");
        }
    }
}