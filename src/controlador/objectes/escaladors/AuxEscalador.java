package controlador.objectes.escaladors;

import dao.SQLite.SQLiteEscaladorDAO;
import personalExceptions.EdatException;
import personalExceptions.EstilPreferitException;
import personalExceptions.GrauDificultatException;
import personalExceptions.NegativeNumberException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AuxEscalador {
    private static Scanner scanner = new Scanner(System.in);

    // CONTROLADOR DE EXCEPTIONS
    public static int insertID (Connection connection) {
        int id = 0;
        try {
            id = scanner.nextInt();
            scanner.nextLine().trim();
            if (id < 0) {
                throw new NegativeNumberException("El ID no pot ser negatiu.");
            }
            if (doesEscaladorExist(id, connection)) {
                throw new IllegalArgumentException("El ID ya existe en la base de datos.");
            }
        } catch (NegativeNumberException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return id;
    }

    public static String insertName () {
        String name = null;
        try {
            name = scanner.nextLine().trim();
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
            alias = scanner.nextLine().trim();
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
            scanner.nextLine().trim();
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
            nivellMaxim = scanner.nextLine().trim();
            if (!nivellMaxim.matches(regEx)) {
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
            nuevoEstilo = scanner.nextLine().trim();
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

    public static int insertViaMax (Connection connection) {
        int idViaMax = 0;
        try {
            idViaMax = scanner.nextInt();
            scanner.nextLine().trim();
            if (!doesViaExist(idViaMax, connection)) {
                throw new IllegalArgumentException("La via no existeix.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return idViaMax;
    }

    public static boolean doesEscaladorExist (int idEscalador, Connection connection) {
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

    public static void readAllEscaladors(BufferedWriter writer, Connection connection) {
        String sql = "SELECT * FROM escaladors";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                writer.write(String.format("%-5d %-20s %-15s %-5d %-10s %-15s %-10s %-10d%n",
                        rs.getInt("id_escalador"),
                        rs.getString("nom"),
                        rs.getString("alias"),
                        rs.getInt("edat"),
                        rs.getString("nivell_max"),
                        rs.getString("estil_preferit"),
                        rs.getString("fita") == null ? "null" : rs.getString("fita"),
                        rs.getInt("id_via_max")));
            }
            System.out.println("Datos de todos los escaladores guardados en sus respectivos archivos.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
