package controlador;

import dao.DBConnection;
import dao.SQLite.SQLiteEscaladorDAO;
import model.Escalador;
import personalExceptions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static dao.SQLite.SQLiteViaDAO.connection;


public class ProvaEscalador {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        String url = "jdbc:sqlite:..\\..\\db\\vies_db1.db";
        Connection connection = null;

        try {
            // Open connection
            connection = DBConnection.openCon(url);
            SQLiteEscaladorDAO escaladorDAO = new SQLiteEscaladorDAO();
            escaladorDAO.setConnection(connection);

            int option;
            do {
                // Display menu
                System.out.println("\n--- Menu Escalador ---");
                System.out.println("1. Insertar Escalador");
                System.out.println("2. Leer Escalador");
                System.out.println("3. Actualizar Escalador");
                System.out.println("4. Eliminar Escalador");
                System.out.println("5. Mostrar todos los Escaladores");
                System.out.println("5. Salir");
                System.out.print("Elige una opción: ");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        try {
                            System.out.println("--- Insertar Escalador ---");
                            // Comprovem que el ID no sigui negatiu
                            System.out.print("ID: ");
                            int id = insertID();
                            // Comprovem que el nom no sigui buit
                            System.out.print("Nombre: ");
                            String nombre = insertName();
                            // Comprovem que l'alias no sigui buit
                            System.out.print("Alias: ");
                            String alias = insertAlias();
                            // Comprovem que l'edat no sigui negativa i no sigui superior a 120
                            System.out.print("Edad: ");
                            int edad = insertAge();
                            // Comprovem que el nivell sigui correcte
                            System.out.print("Nivel Máximo: ");
                            String nivelMax = insertNivellMaxim();
                            // Comprovem si el nivell es correcte
                            System.out.print("Estilo Preferido: ");
                            String estilo = insertEstilPreferit();
                            // No comprovem res de la fita
                            System.out.print("Fita: ");
                            String fita = scanner.nextLine();
                            // Comprovem si la via existeix
                            System.out.print("ID Via Máxima: ");
                            int idViaMax = insertViaMax();
                            // Una vegada comprovat totes les dades es pot inserir l'Escalador
                            Escalador nuevoEscalador = new Escalador(id, nombre, alias, edad, nivelMax, estilo, fita, idViaMax);
                            escaladorDAO.insertTable(nuevoEscalador);
                            System.out.println("Escalador creado con éxito.");
                        } catch (EstilPreferitException e) {
                            System.err.println(e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.println("--- Leer Escalador ---");
                        // Comprovem si l'escalador existeix abans d'intentar llegir
                        System.out.print("ID del escalador a leer | ");
                        int idLeer = scanner.nextInt();
                        if (!doesEscaladorExist(idLeer)) {
                            System.err.println("El escalador no existe.");
                            break;
                        } else {
                        escaladorDAO.readTable(idLeer);
                        }
                        break;
                    case 3:
                        try {
                            System.out.println("--- Actualizar Escalador ---");
                            // Comprovem que el ID no sigui negatiu
                            System.out.print("ID del escalador a actualizar: ");
                            int idActualizar = insertID();
                            // Comprovem que el nom no sigui buit
                            System.out.print("Nuevo Nombre: ");
                            String nuevoNombre = insertName();
                            // Comprovem que l'alias no sigui buit
                            System.out.print("Nuevo Alias: ");
                            String nuevoAlias = insertAlias();
                            // Comprovem que l'edat no sigui negativa i no sigui superior a 120
                            System.out.print("Nueva Edad: ");
                            int nuevaEdad = insertAge();
                            // Comprovem que el nivell sigui correcte
                            System.out.print("Nuevo Nivel Máximo: ");
                            String nuevoNivelMax = insertNivellMaxim();
                            // Comprovem si el nivell es correcte
                            System.out.print("Nuevo Estilo Preferido: ");
                            String nuevoEstilo = insertEstilPreferit();
                            // No comprovem res de la fita
                            System.out.print("Nueva Fita: ");
                            String nuevaFita = scanner.nextLine();
                            // Comprovem si la via existeix
                            System.out.print("Nuevo ID Via Máxima: ");
                            int nuevoIdViaMax = insertViaMax();
                            // Una vegada tot esta comporvat es pot actualitzar l'escalador
                            Escalador escaladorActualizado = new Escalador(idActualizar, nuevoNombre, nuevoAlias, nuevaEdad, nuevoNivelMax, nuevoEstilo, nuevaFita, nuevoIdViaMax);
                            escaladorDAO.updateTable(escaladorActualizado);
                            System.out.println("Escalador actualizado con éxito.");
                        } catch (EstilPreferitException e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.println("--- Eliminar Escalador ---");
                        // Comprovem que l'escalador existeix abans d'eliminarlo
                        System.out.print("ID del escalador a eliminar | ID: ");
                        int idEliminar = scanner.nextInt();
                        if (!doesEscaladorExist(idEliminar)) {
                            System.err.println("El escalador no existe.");
                            break;
                        } else {
                            escaladorDAO.deleteTable(idEliminar);
                            System.out.println("Escalador eliminado con éxito.");
                        }
                        break;
                    case 5:
                        System.out.println("--- Mostrar todos los Escaladores ---");
                        // Mostrar tots els escaladors
                        escaladorDAO.readAll();
                        break;
                    case 6:
                        System.out.println("--- Salir ---");
                        // Exit
                        System.out.println("Saliendo del menú...");
                        break;

                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.");
                }
            } while (option != 5);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close connection
            DBConnection.closeCon(connection);
            scanner.close();
        }
    }

    // CONTROLADOR DE EXCEPTIONS
    public static int insertID () {
        int id = 0;
        try {
            id = scanner.nextInt();
            if (id < 0) {
                throw new NegativeNumberException("El ID no pot ser negatiu.");
            }
        } catch (NegativeNumberException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return id;
    }

    public static String insertName () {
        String name = null;
        try {
            name = scanner.nextLine();
            if (name.isEmpty()) {
                throw new IllegalArgumentException("El nom no pot estar buit.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return name;
    }

    public static String insertAlias () {
        String alias = null;
        try {
            alias = scanner.nextLine();
            if (alias.isEmpty()) {
                throw new IllegalArgumentException("L'alias no pot estar buit.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return alias;
    }

    public static int insertAge () {
        int age = 0;
        try {
            age = scanner.nextInt();
            if (age < 0) {
                throw new EdatException("L'edat no pot ser negativa.");
            } else if (age > 120) {
                throw new EdatException("L'edat no pot ser superior a 120 anys.");
            }
        } catch (EdatException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return age;
    }

    public static String insertNivellMaxim () {
        String nivellMaxim = null;
        String regEx = "^(4\\+?|5\\+?|6[abc]\\+?|7[abc]\\+?|8[abc]\\+?|9[abc]\\+?)$";
        try {
            nivellMaxim = scanner.nextLine();
            if (nivellMaxim.matches(regEx)) {
                throw new GrauDificultatException("El nivell no està dins del rang vàlid.");
            }
        } catch (GrauDificultatException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return nivellMaxim;
    }

    public static String insertEstilPreferit() {
        String nuevoEstilo = null;
        try {
            nuevoEstilo = scanner.nextLine();
            if (!nuevoEstilo.equalsIgnoreCase("esportiva") && !nuevoEstilo.equalsIgnoreCase("clàssica") && !nuevoEstilo.equalsIgnoreCase("gel")) {
                throw new EstilPreferitException("Estil preferit no vàlid, ha de ser; esportiva | clàssica | gel");
            }
        } catch (EstilPreferitException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return nuevoEstilo;
    }

    public static boolean doesViaExist(int idVia, Connection connection) {
    String sql = "SELECT COUNT(*) FROM vies WHERE id_via = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, idVia);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
    }

    public static int insertViaMax () {
        int idViaMax = 0;
        try {
            idViaMax = scanner.nextInt();
            if (!doesViaExist(idViaMax, connection)) {
                throw new IllegalArgumentException("La via no existeix.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return idViaMax;
    }

    public static boolean doesEscaladorExist (int idEscalador) {
        String sql = "SELECT COUNT(*) FROM escaladors WHERE id_escalador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEscalador);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}