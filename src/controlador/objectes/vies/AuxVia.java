package controlador.objects.vies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import personalExceptions.NegativeNumberException;
import personalExceptions.GrauDificultatException;

public class AuxVia {
    private Connection connection;
    private Scanner scanner;

    public AuxVia(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    // Metode per insertar i validar un ID
    public int insertId() {
        try {
            int id = Integer.parseInt(scanner.nextLine());
            if (id < 0) {
                throw new NegativeNumberException("El ID no pot ser negatiu.");
            }
            return id;
        } catch (NumberFormatException e) {
            System.err.println("Error: Format de número incorrecte.");
        } catch (NegativeNumberException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return -1; // Retornamos un valor inválido para indicar error
    }

    // Metode per insertar i validar un nom
    public String insertNom() {
        String nom = scanner.nextLine().trim();
        if (nom.isEmpty()) {
            System.err.println("Error: El nom no pot estar buit.");
            return null;
        }
        return nom;
    }

    // Metode per insertar i validar una orientació
    public String insertOrientacio() {
        String orientacio = scanner.nextLine().trim();
        if (orientacio.isEmpty()) {
            System.err.println("Error: L'orientació no pot estar buida.");
            return null;
        }
        return orientacio;
    }

    // Metode per insertar i validar un estat
    public String insertEstat() {
        String estat = scanner.nextLine().trim();
        if (!estat.equals("Equipant") && !estat.equals("En Revisió") && !estat.equals("Apte")) {
            System.err.println("Error: L'estat ha de ser 'Equipant', 'En Revisió' o 'Apte'.");
            return null;
        }
        return estat;
    }

    // Metode per insertar i validar ancoratges
    public String insertAncoratges() {
        return scanner.nextLine().trim();
    }

    // Metode per insertar i validar tipus de roca
    public String insertTipusDeRoca() {
        String tipusDeRoca = scanner.nextLine().trim();
        if (tipusDeRoca.isEmpty()) {
            System.err.println("Error: El tipus de roca no pot estar buit.");
            return null;
        }
        return tipusDeRoca;
    }

    // Metode per insrrtar i validar ID creador
    public int insertIDCreador() {
        try {
            int idCreador = Integer.parseInt(scanner.nextLine());
            if (idCreador < 0) {
                throw new NegativeNumberException("El ID no pot ser negatiu.");
            }
            if (!escaladorExisteix(idCreador)) {
                System.err.println("Error: No existeix cap escalador amb aquest ID.");
                return -1;
            }
            return idCreador;
        } catch (NumberFormatException e) {
            System.err.println("Error: Format de número incorrecte.");
        } catch (NegativeNumberException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return -1;
    }

    // Metode per insertar i validar un ID sector
    public int insertIDSector() {
        try {
            int idSector = Integer.parseInt(scanner.nextLine());
            if (idSector < 0) {
                throw new NegativeNumberException("El ID no pot ser negatiu.");
            }
            if (!sectorExisteix(idSector)) {
                System.err.println("Error: No existeix cap sector amb aquest ID.");
                return -1;
            }
            return idSector;
        } catch (NumberFormatException e) {
            System.err.println("Error: Format de número incorrecte.");
        } catch (NegativeNumberException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return -1;
    }

    // Metode per insertar i validar un grau de dificultat
    public String insertGrauDificultat() {
        try {
            String grauDificultat = scanner.nextLine().trim();
            String regEx = "^(4\\+?|5\\+?|6[abc]\\+?|7[abc]\\+?|8[abc]\\+?|9[abc]\\+?)$";
            if (!grauDificultat.matches(regEx)) {
                throw new GrauDificultatException("El grau de dificultat no és vàlid. Format: 4, 4+, 5, 5+, 6a, 6a+, etc.");
            }
            return grauDificultat;
        } catch (GrauDificultatException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }

    // Metode per insertar i validar llargada total
    public int insertLlargadaTotal() {
        try {
            int llargadaTotal = Integer.parseInt(scanner.nextLine());
            if (llargadaTotal <= 0) {
                System.err.println("Error: La llargada total ha de ser positiva.");
                return -1;
            }
            return llargadaTotal;
        } catch (NumberFormatException e) {
            System.err.println("Error: Format de número incorrecte.");
        }
        return -1;
    }

    // Metode ancoratges permesos
    public String insertAncoratgesPermesos() {
        return scanner.nextLine().trim();
    }

    // Verificar si una via existe
    public boolean viaExisteix(int idVia) {
        String sql = "SELECT COUNT(*) FROM vies WHERE id_via = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idVia);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar l'existència de la via: " + e.getMessage());
        }
        return false;
    }

    // Verificar si una via esportiva existe
    public boolean viaEsportivaExisteix(int idViaEsportiva) {
        String sql = "SELECT COUNT(*) FROM vies_esportiva WHERE id_via_esportiva = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idViaEsportiva);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar l'existència de la via esportiva: " + e.getMessage());
        }
        return false;
    }

    // Verificar si una via clàssica existe
    public boolean viaClassicaExisteix(int idViaClassica) {
        String sql = "SELECT COUNT(*) FROM vies_classica WHERE id_via_classica = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idViaClassica);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar l'existència de la via clàssica: " + e.getMessage());
        }
        return false;
    }

    // Verificar si una via gel existe
    public boolean viaGelExisteix(int idViaGel) {
        String sql = "SELECT COUNT(*) FROM vies_gel WHERE id_via_gel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idViaGel);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar l'existència de la via gel: " + e.getMessage());
        }
        return false;
    }

    // Verificar si un escalador existe
    public boolean escaladorExisteix(int idEscalador) {
        String sql = "SELECT COUNT(*) FROM escaladors WHERE id_escalador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEscalador);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar l'existència de l'escalador: " + e.getMessage());
        }
        return false;
    }

    // Verificar si un sector existe
    public boolean sectorExisteix(int idSector) {
        String sql = "SELECT COUNT(*) FROM sectors WHERE id_sector = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idSector);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar l'existència del sector: " + e.getMessage());
        }
        return false;
    }

    // Métodos para modificación (entrada con valor por defecto)

    // Obtener dato modificable (permite dejar en blanco para conservar valor actual)
    public String obtenerDatoModificable(String nombreCampo, String valorActual) {
        System.out.print(nombreCampo + " [" + valorActual + "]: ");
        String nuevoValor = scanner.nextLine().trim();
        return nuevoValor.isEmpty() ? valorActual : nuevoValor;
    }

    // Obtener estado modificable (con validación)
    public String obtenerDatoModificableEstat(String nombreCampo, String valorActual) {
        while (true) {
            System.out.print(nombreCampo + " (Equipant/En Revisió/Apte) [" + valorActual + "]: ");
            String nuevoValor = scanner.nextLine().trim();

            if (nuevoValor.isEmpty()) {
                return valorActual;
            }

            if (nuevoValor.equals("Equipant") || nuevoValor.equals("En Revisió") || nuevoValor.equals("Apte")) {
                return nuevoValor;
            }

            System.out.println("Estat no vàlid. Ha de ser: Equipant, En Revisió o Apte.");
        }
    }

    // Obtener grado dificultad modificable
    public String obtenerGrauDificultatModificable(String valorActual) {
        while (true) {
            System.out.print("Grau Dificultat [" + valorActual + "]: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                return valorActual;
            }

            String regEx = "^(4\\+?|5\\+?|6[abc]\\+?|7[abc]\\+?|8[abc]\\+?|9[abc]\\+?)$";
            if (!input.matches(regEx)) {
                System.out.println("El grau de dificultat no és vàlid. Format: 4, 4+, 5, 5+, 6a, 6a+, etc.");
                continue;
            }

            return input;
        }
    }

    // Obtener ID creador modificable
    public int obtenerIDCreadorModificable(int valorActual) {
        while (true) {
            System.out.print("ID Creador (Escalador) [" + valorActual + "]: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                return valorActual;
            }

            try {
                int nuevoValor = Integer.parseInt(input);
                if (nuevoValor < 0) {
                    System.out.println("El ID no pot ser negatiu.");
                    continue;
                }

                if (!escaladorExisteix(nuevoValor)) {
                    System.out.println("No existeix cap escalador amb aquest ID.");
                    continue;
                }

                return nuevoValor;
            } catch (NumberFormatException e) {
                System.out.println("Format de número incorrecte.");
            }
        }
    }

    // Obtener ID sector modificable
    public int obtenerIDSectorModificable(int valorActual) {
        while (true) {
            System.out.print("ID Sector [" + valorActual + "]: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                return valorActual;
            }

            try {
                int nuevoValor = Integer.parseInt(input);
                if (nuevoValor < 0) {
                    System.out.println("El ID no pot ser negatiu.");
                    continue;
                }

                if (!sectorExisteix(nuevoValor)) {
                    System.out.println("No existeix cap sector amb aquest ID.");
                    continue;
                }

                return nuevoValor;
            } catch (NumberFormatException e) {
                System.out.println("Format de número incorrecte.");
            }
        }
    }

    // Obtener llargada total modificable
    public int obtenerLlargadaTotalModificable(int valorActual) {
        while (true) {
            System.out.print("Llargada Total (en metres) [" + valorActual + "]: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                return valorActual;
            }

            try {
                int nuevoValor = Integer.parseInt(input);
                if (nuevoValor <= 0) {
                    System.out.println("La llargada total ha de ser positiva.");
                    continue;
                }

                return nuevoValor;
            } catch (NumberFormatException e) {
                System.out.println("Format de número incorrecte.");
            }
        }
    }
}
