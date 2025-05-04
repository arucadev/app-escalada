package controlador.objects.vies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EliminarVia {
    private Connection connection;
    private Scanner scanner;
    private AuxVia auxVia;

    public EliminarVia(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
        this.auxVia = new AuxVia(connection, scanner);
    }

    // Método para eliminar una via esportiva
    public void eliminarViaEsportiva() {
        try {
            System.out.println("\n=== ELIMINAR VIA ESPORTIVA ===");

            // Solicitar y validar ID
            System.out.print("Introdueix l'ID de la Via Esportiva: ");
            int idViaEsportiva = auxVia.insertId();

            if (idViaEsportiva == -1 || !auxVia.viaEsportivaExisteix(idViaEsportiva)) {
                System.out.println("Error: ID de via esportiva no vàlid o no existeix.");
                return;
            }

            // Obtener datos para confirmar
            int idVia = obtenerIdVia(idViaEsportiva, "vies_esportiva", "id_via_esportiva");
            String nombreVia = obtenerNombreVia(idVia);

            // Confirmación
            System.out.println("Estàs segur que vols eliminar la via esportiva '" + nombreVia + "' (ID: " + idViaEsportiva + ")? (s/n)");
            String confirmacion = scanner.nextLine().trim().toLowerCase();

            if (!confirmacion.equals("s")) {
                System.out.println("Operació cancel·lada.");
                return;
            }

            // Eliminar
            eliminarViaEspecifica(idViaEsportiva, "vies_esportiva", "id_via_esportiva");
            eliminarViaGeneral(idVia);

            System.out.println("Via Esportiva eliminada correctament!");

        } catch (SQLException e) {
            System.err.println("Error al eliminar la via esportiva: " + e.getMessage());
        }
    }

    // Método para eliminar una via clàssica
    public void eliminarViaClassica() {
        try {
            System.out.println("\n=== ELIMINAR VIA CLÀSSICA ===");

            // Solicitar y validar ID
            System.out.print("Introdueix l'ID de la Via Clàssica: ");
            int idViaClassica = auxVia.insertId();

            if (idViaClassica == -1 || !auxVia.viaClassicaExisteix(idViaClassica)) {
                System.out.println("Error: ID de via clàssica no vàlid o no existeix.");
                return;
            }

            // Obtener datos para confirmar
            int idVia = obtenerIdVia(idViaClassica, "vies_classica", "id_via_classica");
            String nombreVia = obtenerNombreVia(idVia);

            // Confirmación
            System.out.println("Estàs segur que vols eliminar la via clàssica '" + nombreVia + "' (ID: " + idViaClassica + ")? (s/n)");
            System.out.println("ATENCIÓ: S'eliminaran també tots els trams associats a aquesta via.");
            String confirmacion = scanner.nextLine().trim().toLowerCase();

            if (!confirmacion.equals("s")) {
                System.out.println("Operació cancel·lada.");
                return;
            }

            // Eliminar trams primero
            eliminarTramsClassica(idViaClassica);
            // Eliminar via específica y via general
            eliminarViaEspecifica(idViaClassica, "vies_classica", "id_via_classica");
            eliminarViaGeneral(idVia);

            System.out.println("Via Clàssica eliminada correctament!");

        } catch (SQLException e) {
            System.err.println("Error al eliminar la via clàssica: " + e.getMessage());
        }
    }

    // Método para eliminar una via gel
    public void eliminarViaGel() {
        try {
            System.out.println("\n=== ELIMINAR VIA GEL ===");

            // Solicitar y validar ID
            System.out.print("Introdueix l'ID de la Via Gel: ");
            int idViaGel = auxVia.insertId();

            if (idViaGel == -1 || !auxVia.viaGelExisteix(idViaGel)) {
                System.out.println("Error: ID de via gel no vàlid o no existeix.");
                return;
            }

            // Obtener datos para confirmar
            int idVia = obtenerIdVia(idViaGel, "vies_gel", "id_via_gel");
            String nombreVia = obtenerNombreVia(idVia);

            // Confirmación
            System.out.println("Estàs segur que vols eliminar la via gel '" + nombreVia + "' (ID: " + idViaGel + ")? (s/n)");
            System.out.println("ATENCIÓ: S'eliminaran també tots els trams associats a aquesta via.");
            String confirmacion = scanner.nextLine().trim().toLowerCase();

            if (!confirmacion.equals("s")) {
                System.out.println("Operació cancel·lada.");
                return;
            }

            // Eliminar trams primero
            eliminarTramsGel(idViaGel);
            // Eliminar via específica y via general
            eliminarViaEspecifica(idViaGel, "vies_gel", "id_via_gel");
            eliminarViaGeneral(idVia);

            System.out.println("Via Gel eliminada correctament!");

        } catch (SQLException e) {
            System.err.println("Error al eliminar la via gel: " + e.getMessage());
        }
    }

    // Método para obtener el ID de vía general a partir del ID específico
    private int obtenerIdVia(int idViaEspecifica, String tabla, String columna) throws SQLException {
        String sql = "SELECT id_via FROM " + tabla + " WHERE " + columna + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idViaEspecifica);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_via");
            }
        }
        return -1;
    }

    // Método para obtener el nombre de una vía para la confirmación
    private String obtenerNombreVia(int idVia) throws SQLException {
        String sql = "SELECT nom FROM vies WHERE id_via = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idVia);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("nom");
            }
        }
        return "Desconegut";
    }

    // Método para eliminar los trams de una via clàssica
    private void eliminarTramsClassica(int idViaClassica) throws SQLException {
        String sql = "DELETE FROM trams_classica WHERE id_via_classica = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idViaClassica);
            int filasEliminadas = stmt.executeUpdate();
            System.out.println(filasEliminadas + " trams de via clàssica eliminats.");
        }
    }

    // Método para eliminar los trams de una via gel
    private void eliminarTramsGel(int idViaGel) throws SQLException {
        String sql = "DELETE FROM trams_gel WHERE id_via_gel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idViaGel);
            int filasEliminadas = stmt.executeUpdate();
            System.out.println(filasEliminadas + " trams de via gel eliminats.");
        }
    }

    // Método para eliminar una via específica (esportiva, clàssica o gel)
    private void eliminarViaEspecifica(int idViaEspecifica, String tabla, String columna) throws SQLException {
        String sql = "DELETE FROM " + tabla + " WHERE " + columna + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idViaEspecifica);
            stmt.executeUpdate();
        }
    }

    // Método para eliminar una via general
    private void eliminarViaGeneral(int idVia) throws SQLException {
        String sql = "DELETE FROM vies WHERE id_via = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idVia);
            stmt.executeUpdate();
        }
    }
}