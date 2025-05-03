package controlador.objectes.sectors;

import personalExceptions.NegativeNumberException;
import personalExceptions.PopularitatException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static dao.SQLite.SQLiteViaDAO.connection;

public class AuxSector {
    private static final Scanner scanner = new Scanner(System.in);

    public static int insertID(Connection connection) {
        int id = 0;
        try {
            System.out.print("ID del sector: ");
            id = Integer.parseInt(scanner.nextLine().trim());
            if (id < 0) {
                throw new NegativeNumberException("El ID no pot ser negatiu.");
            }
            if (doesSectorExist(id, connection)) {
                throw new IllegalArgumentException("El ID ja existeix a la base de dades.");
            }
        } catch (NegativeNumberException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return id;
    }

    public static String insertName() {
        String name = null;
        try {
            System.out.print("Nom del sector: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                throw new IllegalArgumentException("El nom no pot estar buit.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return name;
    }

    public static double insertCoordinate(String prompt) {
        double coordinate = 0.0;
        try {
            System.out.print(prompt);
            coordinate = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.err.println("Error: La coordenada ha de ser un número vàlid.");
        }
        return coordinate;
    }

    public static String insertAproximacio() {
        System.out.print("Aproximació: ");
        return scanner.nextLine().trim();
    }

    public static String insertPopularitat() {
        String popularitat = null;
        try {
            System.out.print("Popularitat (baixa, mitja, alta): ");
            popularitat = scanner.nextLine().trim().toLowerCase();
            if (!popularitat.equals("baixa") && !popularitat.equals("mitja") && !popularitat.equals("alta")) {
                throw new PopularitatException("Popularitat no vàlida. Ha de ser: baixa, mitja o alta.");
            }
        } catch (PopularitatException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return popularitat;
    }

    public static String insertRestriccions() {
        System.out.print("Restriccions: ");
        return scanner.nextLine().trim();
    }

    public static int insertForeignKey(Connection connection) {
        int idEscola = 0;
        try {
            System.out.print("ID de l'escola: ");
            idEscola = Integer.parseInt(scanner.nextLine().trim());
            if (!doesEscolaExist(idEscola, connection)) {
                throw new IllegalArgumentException("El ID de l'escola no existeix a la base de dades.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return idEscola;
    }

    private static boolean doesSectorExist(int idSector, Connection connection) {
        String sql = "SELECT COUNT(*) FROM sectors WHERE id_sector = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idSector);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean doesEscolaExist(int idEscola, Connection connection) {
        String sql = "SELECT COUNT(*) FROM escoles WHERE id_escola = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEscola);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void readAllSectors(BufferedWriter writer, Connection connection) {
        String sql = "SELECT id_sector, nom, coordenades_lat, coordenades_long, aproximacio, popularitat, restriccions, id_escola FROM sectors";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                writer.write(String.format("%-5d %-20s %-15.6f %-15.6f %-15s %-10s %-15s %-10d%n",
                        rs.getInt("id_sector"),
                        rs.getString("nom"),
                        rs.getDouble("coordenades_lat"),
                        rs.getDouble("coordenades_long"),
                        rs.getString("aproximacio"),
                        rs.getString("popularitat"),
                        rs.getString("restriccions") == null ? "null" : rs.getString("restriccions"),
                        rs.getInt("id_escola")));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}