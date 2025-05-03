package controlador.objectes.escoles;

import dao.SQLite.SQLiteEscolaDAO;
import personalExceptions.NegativeNumberException;
import personalExceptions.PopularitatException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AuxEscola {
    private static final Scanner scanner = new Scanner(System.in);

    public static int insertID(Connection connection) {
        int id = 0;
        try {
            System.out.print("ID: ");
            id = Integer.parseInt(scanner.nextLine().trim());
            if (id < 0) {
                throw new NegativeNumberException("El ID no pot ser negatiu.");
            }
            if (doesEscolaExist(id, connection)) {
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
            System.out.print("Nom: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                throw new IllegalArgumentException("El nom no pot estar buit.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return name;
    }

    public static int insertPositiveNumber(String prompt, String errorMessage) {
        int number = 0;
        try {
            System.out.print(prompt);
            number = Integer.parseInt(scanner.nextLine().trim());
            if (number < 0) {
                throw new NegativeNumberException(errorMessage);
            }
        } catch (NegativeNumberException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return number;
    }

    public static String insertPopularitatEscola() {
        String popularitat = null;
        try {
            System.out.print("Popularitat (baixa, mitjana, alta): ");
            popularitat = scanner.nextLine().trim().toLowerCase();
            if (!popularitat.equals("baixa") && !popularitat.equals("mitjana") && !popularitat.equals("alta")) {
                throw new PopularitatException("Popularitat no vàlida. Ha de ser: baixa, mitjana o alta.");
            }
        } catch (PopularitatException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return popularitat;
    }

    public static boolean doesEscolaExist(int idEscola, Connection connection) {
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

    public static void readAllEscoles(BufferedWriter writer, Connection connection) {
        String sql = "SELECT id_escola, nom, aproximacio, num_vies, popularitat, restriccions, id_poblacio FROM escoles";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                writer.write(String.format("%-5d %-20s %-15s %-5d %-10s %-15s %-10s %-10d%n",
                        rs.getInt("id_escola"),
                        rs.getString("nom"),
                        rs.getString("aproximacio"),
                        rs.getInt("num_vies"),
                        rs.getString("popularitat"),
                        rs.getString("restriccions") == null ? "null" : rs.getString("restriccions"),
                        rs.getInt("id_poblacio")));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}