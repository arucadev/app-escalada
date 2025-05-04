package controlador.objects.vies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ModificarVia {
    private Connection connection;
    private Scanner scanner;
    private AuxVia auxVia;

    public ModificarVia(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
        this.auxVia = new AuxVia(connection, scanner);
    }

    // Método principal para modificar una via esportiva
    public void modificarViaEsportiva() {
        try {
            System.out.println("\n=== MODIFICAR VIA ESPORTIVA ===");

            // Solicitar ID de la vía a modificar
            System.out.print("Introdueix l'ID de la Via Esportiva: ");
            int idViaEsportiva = auxVia.insertId();

            if (idViaEsportiva == -1 || !auxVia.viaEsportivaExisteix(idViaEsportiva)) {
                System.out.println("Error: ID de via esportiva no vàlid o no existeix.");
                return;
            }

            // Obtener la información actual
            int idVia = obtenerIdVia(idViaEsportiva, "vies_esportiva", "id_via_esportiva");
            if (idVia == -1) {
                System.out.println("Error: No s'ha pogut obtenir la informació de la via.");
                return;
            }

            // Obtener datos actuales de la vía
            String[] datosActuales = obtenerDatosVia(idVia);
            String[] datosEsportiva = obtenerDatosViaEsportiva(idViaEsportiva);

            if (datosActuales == null || datosEsportiva == null) {
                System.out.println("Error: No s'ha pogut obtenir la informació completa de la via.");
                return;
            }

            // Mostrar datos actuales y solicitar cambios
            System.out.println("\nDades actuals de la via:");
            System.out.println("Nom: " + datosActuales[0]);
            System.out.println("Orientació: " + datosActuales[1]);
            System.out.println("Estat: " + datosActuales[2]);
            System.out.println("Ancoratges: " + datosActuales[3]);
            System.out.println("Tipus de Roca: " + datosActuales[4]);
            System.out.println("ID Creador: " + datosActuales[5]);
            System.out.println("ID Sector: " + datosActuales[6]);
            System.out.println("Ancoratges Permesos: " + datosEsportiva[0]);
            System.out.println("Grau Dificultat: " + datosEsportiva[1]);
            System.out.println("Llargada Total: " + datosEsportiva[2]);

            System.out.println("\nPer a mantenir el valor actual, deixa en blanc el camp.");

            // Solicitar nuevos valores
            String nom = auxVia.obtenerDatoModificable("Nom", datosActuales[0]);
            String orientacio = auxVia.obtenerDatoModificable("Orientació", datosActuales[1]);
            String estat = auxVia.obtenerDatoModificableEstat("Estat", datosActuales[2]);
            String ancoratges = auxVia.obtenerDatoModificable("Ancoratges", datosActuales[3]);
            String tipusDeRoca = auxVia.obtenerDatoModificable("Tipus de Roca", datosActuales[4]);
            int idCreador = auxVia.obtenerIDCreadorModificable(Integer.parseInt(datosActuales[5]));
            int idSector = auxVia.obtenerIDSectorModificable(Integer.parseInt(datosActuales[6]));

            String ancoratgesPermesos = auxVia.obtenerDatoModificable("Ancoratges Permesos", datosEsportiva[0]);
            String grauDificultat = auxVia.obtenerGrauDificultatModificable(datosEsportiva[1]);
            int llargadaTotal = auxVia.obtenerLlargadaTotalModificable(Integer.parseInt(datosEsportiva[2]));

            // Actualizar datos en la base de datos
            actualizarVia(idVia, nom, orientacio, estat, ancoratges, tipusDeRoca, idCreador, idSector);
            actualizarViaEsportiva(idViaEsportiva, ancoratgesPermesos, grauDificultat, llargadaTotal);

            System.out.println("Via Esportiva modificada correctament!");

        } catch (Exception e) {
            System.err.println("Error al modificar la via esportiva: " + e.getMessage());
        }
    }

    // Método principal para modificar una via clàssica
    public void modificarViaClassica() {
        try {
            System.out.println("\n=== MODIFICAR VIA CLÀSSICA ===");

            // Solicitar ID de la vía a modificar
            System.out.print("Introdueix l'ID de la Via Clàssica: ");
            int idViaClassica = auxVia.insertId();

            if (idViaClassica == -1 || !auxVia.viaClassicaExisteix(idViaClassica)) {
                System.out.println("Error: ID de via clàssica no vàlid o no existeix.");
                return;
            }

            // Obtener la información actual
            int idVia = obtenerIdVia(idViaClassica, "vies_classica", "id_via_classica");
            if (idVia == -1) {
                System.out.println("Error: No s'ha pogut obtenir la informació de la via.");
                return;
            }

            // Obtener datos actuales de la vía
            String[] datosActuales = obtenerDatosVia(idVia);
            String[] datosClassica = obtenerDatosViaClassica(idViaClassica);

            if (datosActuales == null || datosClassica == null) {
                System.out.println("Error: No s'ha pogut obtenir la informació completa de la via.");
                return;
            }

            // Mostrar datos actuales y solicitar cambios
            System.out.println("\nDades actuals de la via:");
            System.out.println("Nom: " + datosActuales[0]);
            System.out.println("Orientació: " + datosActuales[1]);
            System.out.println("Estat: " + datosActuales[2]);
            System.out.println("Ancoratges: " + datosActuales[3]);
            System.out.println("Tipus de Roca: " + datosActuales[4]);
            System.out.println("ID Creador: " + datosActuales[5]);
            System.out.println("ID Sector: " + datosActuales[6]);
            System.out.println("Ancoratges Permesos: " + datosClassica[0]);
            System.out.println("Grau Dificultat: " + datosClassica[1]);
            System.out.println("Llargada Total: " + datosClassica[2]);

            System.out.println("\nPer a mantenir el valor actual, deixa en blanc el camp.");

            // Solicitar nuevos valores
            String nom = auxVia.obtenerDatoModificable("Nom", datosActuales[0]);
            String orientacio = auxVia.obtenerDatoModificable("Orientació", datosActuales[1]);
            String estat = auxVia.obtenerDatoModificableEstat("Estat", datosActuales[2]);
            String ancoratges = auxVia.obtenerDatoModificable("Ancoratges", datosActuales[3]);
            String tipusDeRoca = auxVia.obtenerDatoModificable("Tipus de Roca", datosActuales[4]);
            int idCreador = auxVia.obtenerIDCreadorModificable(Integer.parseInt(datosActuales[5]));
            int idSector = auxVia.obtenerIDSectorModificable(Integer.parseInt(datosActuales[6]));

            String ancoratgesPermesos = auxVia.obtenerDatoModificable("Ancoratges Permesos", datosClassica[0]);
            String grauDificultat = auxVia.obtenerGrauDificultatModificable(datosClassica[1]);
            int llargadaTotal = auxVia.obtenerLlargadaTotalModificable(Integer.parseInt(datosClassica[2]));

            // Actualizar datos en la base de datos
            actualizarVia(idVia, nom, orientacio, estat, ancoratges, tipusDeRoca, idCreador, idSector);
            actualizarViaClassica(idViaClassica, ancoratgesPermesos, grauDificultat, llargadaTotal);

            System.out.println("Via Clàssica modificada correctament!");

        } catch (Exception e) {
            System.err.println("Error al modificar la via clàssica: " + e.getMessage());
        }
    }

    // Método principal para modificar una via gel
    public void modificarViaGel() {
        try {
            System.out.println("\n=== MODIFICAR VIA GEL ===");

            // Solicitar ID de la vía a modificar
            System.out.print("Introdueix l'ID de la Via Gel: ");
            int idViaGel = auxVia.insertId();

            if (idViaGel == -1 || !auxVia.viaGelExisteix(idViaGel)) {
                System.out.println("Error: ID de via gel no vàlid o no existeix.");
                return;
            }

            // Obtener la información actual
            int idVia = obtenerIdVia(idViaGel, "vies_gel", "id_via_gel");
            if (idVia == -1) {
                System.out.println("Error: No s'ha pogut obtenir la informació de la via.");
                return;
            }

            // Obtener datos actuales de la vía
            String[] datosActuales = obtenerDatosVia(idVia);
            String[] datosGel = obtenerDatosViaGel(idViaGel);

            if (datosActuales == null || datosGel == null) {
                System.out.println("Error: No s'ha pogut obtenir la informació completa de la via.");
                return;
            }

            // Mostrar datos actuales y solicitar cambios
            System.out.println("\nDades actuals de la via:");
            System.out.println("Nom: " + datosActuales[0]);
            System.out.println("Orientació: " + datosActuales[1]);
            System.out.println("Estat: " + datosActuales[2]);
            System.out.println("Ancoratges: " + datosActuales[3]);
            System.out.println("Tipus de Roca: " + datosActuales[4]);
            System.out.println("ID Creador: " + datosActuales[5]);
            System.out.println("ID Sector: " + datosActuales[6]);
            System.out.println("Ancoratges Permesos: " + datosGel[0]);
            System.out.println("Grau Dificultat: " + datosGel[1]);
            System.out.println("Llargada Total: " + datosGel[2]);

            System.out.println("\nPer a mantenir el valor actual, deixa en blanc el camp.");

            // Solicitar nuevos valores
            String nom = auxVia.obtenerDatoModificable("Nom", datosActuales[0]);
            String orientacio = auxVia.obtenerDatoModificable("Orientació", datosActuales[1]);
            String estat = auxVia.obtenerDatoModificableEstat("Estat", datosActuales[2]);
            String ancoratges = auxVia.obtenerDatoModificable("Ancoratges", datosActuales[3]);
            String tipusDeRoca = auxVia.obtenerDatoModificable("Tipus de Roca", datosActuales[4]);
            int idCreador = auxVia.obtenerIDCreadorModificable(Integer.parseInt(datosActuales[5]));
            int idSector = auxVia.obtenerIDSectorModificable(Integer.parseInt(datosActuales[6]));

            String ancoratgesPermesos = auxVia.obtenerDatoModificable("Ancoratges Permesos", datosGel[0]);
            String grauDificultat = auxVia.obtenerGrauDificultatModificable(datosGel[1]);
            int llargadaTotal = auxVia.obtenerLlargadaTotalModificable(Integer.parseInt(datosGel[2]));

            // Actualizar datos en la base de datos
            actualizarVia(idVia, nom, orientacio, estat, ancoratges, tipusDeRoca, idCreador, idSector);
            actualizarViaGel(idViaGel, ancoratgesPermesos, grauDificultat, llargadaTotal);

            System.out.println("Via Gel modificada correctament!");

        } catch (Exception e) {
            System.err.println("Error al modificar la via gel: " + e.getMessage());
        }
    }

    // Método para obtener el ID de vía general a partir del ID específico
    private int obtenerIdVia(int idViaEspecifica, String tabla, String columna) {
        String sql = "SELECT id_via FROM " + tabla + " WHERE " + columna + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idViaEspecifica);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_via");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtenir l'ID de la via: " + e.getMessage());
        }
        return -1;
    }

    // Método para obtener datos actuales de una vía
    private String[] obtenerDatosVia(int idVia) {
        String sql = "SELECT nom, orientacio, estat, ancoratges, tipus_de_roca, id_creador, id_sector " +
                     "FROM vies WHERE id_via = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idVia);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String[] datos = new String[7];
                datos[0] = rs.getString("nom");
                datos[1] = rs.getString("orientacio");
                datos[2] = rs.getString("estat");
                datos[3] = rs.getString("ancoratges");
                datos[4] = rs.getString("tipus_de_roca");
                datos[5] = String.valueOf(rs.getInt("id_creador"));
                datos[6] = String.valueOf(rs.getInt("id_sector"));
                return datos;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtenir les dades de la via: " + e.getMessage());
        }
        return null;
    }

    // Método para obtener datos de una vía esportiva
    private String[] obtenerDatosViaEsportiva(int idViaEsportiva) {
        String sql = "SELECT ancoratges_permesos, grau_dificultat, llargada_total " +
                     "FROM vies_esportiva WHERE id_via_esportiva = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idViaEsportiva);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String[] datos = new String[3];
                datos[0] = rs.getString("ancoratges_permesos");
                datos[1] = rs.getString("grau_dificultat");
                datos[2] = String.valueOf(rs.getInt("llargada_total"));
                return datos;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtenir les dades de la via esportiva: " + e.getMessage());
        }
        return null;
    }

    // Método para obtener datos de una vía clàssica
    private String[] obtenerDatosViaClassica(int idViaClassica) {
        String sql = "SELECT ancoratges_permesos, grau_dificultat, llargada_total " +
                     "FROM vies_classica WHERE id_via_classica = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idViaClassica);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String[] datos = new String[3];
                datos[0] = rs.getString("ancoratges_permesos");
                datos[1] = rs.getString("grau_dificultat");
                datos[2] = String.valueOf(rs.getInt("llargada_total"));
                return datos;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtenir les dades de la via clàssica: " + e.getMessage());
        }
        return null;
    }

    // Método para obtener datos de una vía gel
    private String[] obtenerDatosViaGel(int idViaGel) {
        String sql = "SELECT ancoratges_permesos, grau_dificultat, llargada_total " +
                     "FROM vies_gel WHERE id_via_gel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idViaGel);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String[] datos = new String[3];
                datos[0] = rs.getString("ancoratges_permesos");
                datos[1] = rs.getString("grau_dificultat");
                datos[2] = String.valueOf(rs.getInt("llargada_total"));
                return datos;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtenir les dades de la via gel: " + e.getMessage());
        }
        return null;
    }

    // Método para actualizar los datos generales de una vía
    private void actualizarVia(int idVia, String nom, String orientacio, String estat,
                              String ancoratges, String tipusDeRoca, int idCreador, int idSector) throws SQLException {
        String sql = "UPDATE vies SET nom = ?, orientacio = ?, estat = ?, ancoratges = ?, " +
                     "tipus_de_roca = ?, id_creador = ?, id_sector = ? WHERE id_via = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nom);
            stmt.setString(2, orientacio);
            stmt.setString(3, estat);
            stmt.setString(4, ancoratges);
            stmt.setString(5, tipusDeRoca);
            stmt.setInt(6, idCreador);
            stmt.setInt(7, idSector);
            stmt.setInt(8, idVia);
            stmt.executeUpdate();
        }
    }

    // Método para actualizar los datos de una vía esportiva
    private void actualizarViaEsportiva(int idViaEsportiva, String ancoratgesPermesos,
                                        String grauDificultat, int llargadaTotal) throws SQLException {
        String sql = "UPDATE vies_esportiva SET ancoratges_permesos = ?, grau_dificultat = ?, " +
                     "llargada_total = ? WHERE id_via_esportiva = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ancoratgesPermesos);
            stmt.setString(2, grauDificultat);
            stmt.setInt(3, llargadaTotal);
            stmt.setInt(4, idViaEsportiva);
            stmt.executeUpdate();
        }
    }

    // Método para actualizar los datos de una vía clàssica
    private void actualizarViaClassica(int idViaClassica, String ancoratgesPermesos,
                                      String grauDificultat, int llargadaTotal) throws SQLException {
        String sql = "UPDATE vies_classica SET ancoratges_permesos = ?, grau_dificultat = ?, " +
                     "llargada_total = ? WHERE id_via_classica = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ancoratgesPermesos);
            stmt.setString(2, grauDificultat);
            stmt.setInt(3, llargadaTotal);
            stmt.setInt(4, idViaClassica);
            stmt.executeUpdate();
        }
    }

    // Método para actualizar los datos de una vía gel
    private void actualizarViaGel(int idViaGel, String ancoratgesPermesos,
                                 String grauDificultat, int llargadaTotal) throws SQLException {
        String sql = "UPDATE vies_gel SET ancoratges_permesos = ?, grau_dificultat = ?, " +
                     "llargada_total = ? WHERE id_via_gel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ancoratgesPermesos);
            stmt.setString(2, grauDificultat);
            stmt.setInt(3, llargadaTotal);
            stmt.setInt(4, idViaGel);
            stmt.executeUpdate();
        }
    }
}