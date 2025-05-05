package controlador.objectes.vies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CrearVia {
    private static Connection connection;
    private static Scanner scanner;
    private static AuxVia auxVia;

    public CrearVia(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
        this.auxVia = new AuxVia(connection, scanner);
    }

    // Método para crear una Via Esportiva
    public static void crearViaEsportiva() {
        try {
            System.out.println("\n=== CREAR VIA ESPORTIVA ===");

            // Insertar datos generales
            System.out.print("ID Via: ");
            int idVia = auxVia.insertId();
            if (idVia == -1 || auxVia.viaExisteix(idVia)) {
                System.out.println("Error: ID de via no válido o ya existe.");
                return;
            }

            System.out.print("ID Via Esportiva: ");
            int idViaEsportiva = auxVia.insertId();
            if (idViaEsportiva == -1 || auxVia.viaEsportivaExisteix(idViaEsportiva)) {
                System.out.println("Error: ID de via esportiva no válido o ya existe.");
                return;
            }

            // Datos de la vía general
            System.out.print("Nom: ");
            String nom = auxVia.insertNom();
            if (nom == null) return;

            System.out.print("Orientació: ");
            String orientacio = auxVia.insertOrientacio();
            if (orientacio == null) return;

            System.out.print("Estat (Equipant/En Revisió/Apte): ");
            String estat = auxVia.insertEstat();
            if (estat == null) return;

            System.out.print("Ancoratges: ");
            String ancoratges = auxVia.insertAncoratges();

            System.out.print("Tipus de Roca: ");
            String tipusDeRoca = auxVia.insertTipusDeRoca();
            if (tipusDeRoca == null) return;

            System.out.print("ID Creador (Escalador): ");
            int idCreador = auxVia.insertIDCreador();
            if (idCreador == -1) return;

            System.out.print("ID Sector: ");
            int idSector = auxVia.insertIDSector();
            if (idSector == -1) return;

            // Datos específicos de vía esportiva
            System.out.print("Ancoratges Permesos: ");
            String ancoratgesPermesos = auxVia.insertAncoratgesPermesos();

            System.out.print("Grau Dificultat: ");
            String grauDificultat = auxVia.insertGrauDificultat();
            if (grauDificultat == null) return;

            System.out.print("Llargada Total (en metres): ");
            int llargadaTotal = auxVia.insertLlargadaTotal();
            if (llargadaTotal == -1) return;

            // Insertar en la base de datos
            insertarVia(idVia, nom, orientacio, estat, ancoratges, tipusDeRoca, idCreador, idSector);
            insertarViaEsportiva(idViaEsportiva, idVia, ancoratgesPermesos, grauDificultat, llargadaTotal);

            System.out.println("Via Esportiva creada correctament!");

        } catch (Exception e) {
            System.err.println("Error al crear la via esportiva: " + e.getMessage());
        }
    }

    // Método para crear una Via Clàssica
    public static void crearViaClassica() {
        try {
            System.out.println("\n=== CREAR VIA CLÀSSICA ===");

            // Insertar datos generales
            System.out.print("ID Via: ");
            int idVia = auxVia.insertId();
            if (idVia == -1 || auxVia.viaExisteix(idVia)) {
                System.out.println("Error: ID de via no válido o ya existe.");
                return;
            }

            System.out.print("ID Via Clàssica: ");
            int idViaClassica = auxVia.insertId();
            if (idViaClassica == -1 || auxVia.viaClassicaExisteix(idViaClassica)) {
                System.out.println("Error: ID de via clàssica no válido o ya existe.");
                return;
            }

            // Datos de la vía general
            System.out.print("Nom: ");
            String nom = auxVia.insertNom();
            if (nom == null) return;

            System.out.print("Orientació: ");
            String orientacio = auxVia.insertOrientacio();
            if (orientacio == null) return;

            System.out.print("Estat (Equipant/En Revisió/Apte): ");
            String estat = auxVia.insertEstat();
            if (estat == null) return;

            System.out.print("Ancoratges: ");
            String ancoratges = auxVia.insertAncoratges();

            System.out.print("Tipus de Roca: ");
            String tipusDeRoca = auxVia.insertTipusDeRoca();
            if (tipusDeRoca == null) return;

            System.out.print("ID Creador (Escalador): ");
            int idCreador = auxVia.insertIDCreador();
            if (idCreador == -1) return;

            System.out.print("ID Sector: ");
            int idSector = auxVia.insertIDSector();
            if (idSector == -1) return;

            // Datos específicos de vía clàssica
            System.out.print("Ancoratges Permesos: ");
            String ancoratgesPermesos = auxVia.insertAncoratgesPermesos();

            System.out.print("Grau Dificultat: ");
            String grauDificultat = auxVia.insertGrauDificultat();
            if (grauDificultat == null) return;

            System.out.print("Llargada Total (en metres): ");
            int llargadaTotal = auxVia.insertLlargadaTotal();
            if (llargadaTotal == -1) return;

            // Insertar en la base de datos
            insertarVia(idVia, nom, orientacio, estat, ancoratges, tipusDeRoca, idCreador, idSector);
            insertarViaClassica(idViaClassica, idVia, ancoratgesPermesos, grauDificultat, llargadaTotal);

            System.out.println("Via Clàssica creada correctament!");

            // Crear tramos
            crearTramsClassica(idViaClassica);

        } catch (Exception e) {
            System.err.println("Error al crear la via clàssica: " + e.getMessage());
        }
    }

    // Método para crear una Via Gel
    public static void crearViaGel() {
        try {
            System.out.println("\n=== CREAR VIA GEL ===");

            // Insertar datos generales
            System.out.print("ID Via: ");
            int idVia = auxVia.insertId();
            if (idVia == -1 || auxVia.viaExisteix(idVia)) {
                System.out.println("Error: ID de via no válido o ya existe.");
                return;
            }

            System.out.print("ID Via Gel: ");
            int idViaGel = auxVia.insertId();
            if (idViaGel == -1 || auxVia.viaGelExisteix(idViaGel)) {
                System.out.println("Error: ID de via gel no válido o ya existe.");
                return;
            }

            // Datos de la vía general
            System.out.print("Nom: ");
            String nom = auxVia.insertNom();
            if (nom == null) return;

            System.out.print("Orientació: ");
            String orientacio = auxVia.insertOrientacio();
            if (orientacio == null) return;

            System.out.print("Estat (Equipant/En Revisió/Apte): ");
            String estat = auxVia.insertEstat();
            if (estat == null) return;

            System.out.print("Ancoratges: ");
            String ancoratges = auxVia.insertAncoratges();

            System.out.print("Tipus de Roca: ");
            String tipusDeRoca = auxVia.insertTipusDeRoca();
            if (tipusDeRoca == null) return;

            System.out.print("ID Creador (Escalador): ");
            int idCreador = auxVia.insertIDCreador();
            if (idCreador == -1) return;

            System.out.print("ID Sector: ");
            int idSector = auxVia.insertIDSector();
            if (idSector == -1) return;

            // Datos específicos de vía gel
            System.out.print("Ancoratges Permesos: ");
            String ancoratgesPermesos = auxVia.insertAncoratgesPermesos();

            System.out.print("Grau Dificultat: ");
            String grauDificultat = auxVia.insertGrauDificultat();
            if (grauDificultat == null) return;

            System.out.print("Llargada Total (en metres): ");
            int llargadaTotal = auxVia.insertLlargadaTotal();
            if (llargadaTotal == -1) return;

            // Insertar en la base de datos
            insertarVia(idVia, nom, orientacio, estat, ancoratges, tipusDeRoca, idCreador, idSector);
            insertarViaGel(idViaGel, idVia, ancoratgesPermesos, grauDificultat, llargadaTotal);

            System.out.println("Via Gel creada correctament!");

            // Crear tramos
            crearTramsGel(idViaGel);

        } catch (Exception e) {
            System.err.println("Error al crear la via gel: " + e.getMessage());
        }
    }

    // Método para insertar una vía general
    private static void insertarVia(int idVia, String nom, String orientacio, String estat, String ancoratges,
                                    String tipusDeRoca, int idCreador, int idSector) throws SQLException {
        String sql = "INSERT INTO vies (id_via, nom, orientacio, estat, ancoratges, tipus_de_roca, id_creador, id_sector) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idVia);
            stmt.setString(2, nom);
            stmt.setString(3, orientacio);
            stmt.setString(4, estat);
            stmt.setString(5, ancoratges);
            stmt.setString(6, tipusDeRoca);
            stmt.setInt(7, idCreador);
            stmt.setInt(8, idSector);
            stmt.executeUpdate();
        }
    }

    // Método para insertar una vía esportiva
    private static void insertarViaEsportiva(int idViaEsportiva, int idVia, String ancoratgesPermesos,
                                             String grauDificultat, int llargadaTotal) throws SQLException {
        String sql = "INSERT INTO vies_esportiva (id_via_esportiva, id_via, ancoratges_permesos, grau_dificultat, llargada_total) " +
                    "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idViaEsportiva);
            stmt.setInt(2, idVia);
            stmt.setString(3, ancoratgesPermesos);
            stmt.setString(4, grauDificultat);
            stmt.setInt(5, llargadaTotal);
            stmt.executeUpdate();
        }
    }

    // Método para insertar una vía clàssica
    private static void insertarViaClassica(int idViaClassica, int idVia, String ancoratgesPermesos,
                                            String grauDificultat, int llargadaTotal) throws SQLException {
        String sql = "INSERT INTO vies_classica (id_via_classica, id_via, ancoratges_permesos, grau_dificultat, llargada_total) " +
                    "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idViaClassica);
            stmt.setInt(2, idVia);
            stmt.setString(3, ancoratgesPermesos);
            stmt.setString(4, grauDificultat);
            stmt.setInt(5, llargadaTotal);
            stmt.executeUpdate();
        }
    }

    // Método para insertar una vía gel
    private static void insertarViaGel(int idViaGel, int idVia, String ancoratgesPermesos,
                                       String grauDificultat, int llargadaTotal) throws SQLException {
        String sql = "INSERT INTO vies_gel (id_via_gel, id_via, ancoratges_permesos, grau_dificultat, llargada_total) " +
                    "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idViaGel);
            stmt.setInt(2, idVia);
            stmt.setString(3, ancoratgesPermesos);
            stmt.setString(4, grauDificultat);
            stmt.setInt(5, llargadaTotal);
            stmt.executeUpdate();
        }
    }

    // Método para crear tramos de una vía clàssica
    public static void crearTramsClassica(int idViaClassica) {
        try {
            System.out.println("\n=== CREAR TRAMS VIA CLÀSSICA ===");
            System.out.print("Quants trams té la via? ");
            int numTrams = Integer.parseInt(scanner.nextLine());

            if (numTrams <= 0) {
                throw new IllegalArgumentException("El número de trams ha de ser positiu.");
            }

            for (int i = 1; i <= numTrams; i++) {
                System.out.println("\nTram " + i + ":");

                System.out.print("ID Tram: ");
                int idTram = auxVia.insertId();
                if (idTram == -1) continue;

                System.out.print("Llargada (en metres): ");
                double llargada = 0;
                try {
                    llargada = Double.parseDouble(scanner.nextLine());
                    if (llargada <= 0) {
                        System.out.println("La llargada ha de ser positiva.");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Format de número incorrecte.");
                    continue;
                }

                System.out.print("Grau Dificultat: ");
                String grauDificultat = auxVia.insertGrauDificultat();
                if (grauDificultat == null) continue;

                insertarTramClassica(idTram, idViaClassica, llargada, grauDificultat, i);
                System.out.println("Tram " + i + " afegit correctament.");
            }

            System.out.println("Tots els trams s'han creat correctament!");

        } catch (NumberFormatException e) {
            System.err.println("Error de format: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al crear els trams: " + e.getMessage());
        }
    }

    // Método para crear tramos de una vía gel
    public static void crearTramsGel(int idViaGel) {
        try {
            System.out.println("\n=== CREAR TRAMS VIA GEL ===");
            System.out.print("Quants trams té la via? ");
            int numTrams = Integer.parseInt(scanner.nextLine());

            if (numTrams <= 0) {
                throw new IllegalArgumentException("El número de trams ha de ser positiu.");
            }

            for (int i = 1; i <= numTrams; i++) {
                System.out.println("\nTram " + i + ":");

                System.out.print("ID Tram: ");
                int idTram = auxVia.insertId();
                if (idTram == -1) continue;

                System.out.print("Llargada (en metres): ");
                double llargada = 0;
                try {
                    llargada = Double.parseDouble(scanner.nextLine());
                    if (llargada <= 0) {
                        System.out.println("La llargada ha de ser positiva.");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Format de número incorrecte.");
                    continue;
                }

                System.out.print("Grau Dificultat: ");
                String grauDificultat = auxVia.insertGrauDificultat();
                if (grauDificultat == null) continue;

                insertarTramGel(idTram, idViaGel, llargada, grauDificultat, i);
                System.out.println("Tram " + i + " afegit correctament.");
            }

            System.out.println("Tots els trams s'han creat correctament!");

        } catch (NumberFormatException e) {
            System.err.println("Error de format: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al crear els trams: " + e.getMessage());
        }
    }

    // Método para insertar un tram clàssica
    private static void insertarTramClassica(int idTram, int idViaClassica, double llargada,
                                             String grauDificultat, int numTram) throws SQLException {
        String sql = "INSERT INTO trams_classica (id_tram_classica, id_via_classica, llargada, grau_dificultat, num_tram) " +
                    "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idTram);
            stmt.setInt(2, idViaClassica);
            stmt.setDouble(3, llargada);
            stmt.setString(4, grauDificultat);
            stmt.setInt(5, numTram);
            stmt.executeUpdate();
        }
    }

    // Método para insertar un tram gel
    private static void insertarTramGel(int idTram, int idViaGel, double llargada,
                                        String grauDificultat, int numTram) throws SQLException {
        String sql = "INSERT INTO trams_gel (id_tram_gel, id_via_gel, llargada, grau_dificultat, num_tram) " +
                    "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idTram);
            stmt.setInt(2, idViaGel);
            stmt.setDouble(3, llargada);
            stmt.setString(4, grauDificultat);
            stmt.setInt(5, numTram);
            stmt.executeUpdate();
        }
    }
}