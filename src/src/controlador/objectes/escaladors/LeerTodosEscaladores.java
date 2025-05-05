package controlador.objectes.escaladors;

import dao.SQLite.SQLiteEscaladorDAO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;

public class LeerTodosEscaladores {
    private final SQLiteEscaladorDAO escaladorDAO;
    private final Connection connection;

    public LeerTodosEscaladores(SQLiteEscaladorDAO escaladorDAO, Connection connection) {
        this.escaladorDAO = escaladorDAO;
        this.connection = connection;
    }

    public void leerTodos() {
        Scanner scanner = new Scanner(System.in);
        String filePath;
        try {
            System.out.print("Introduce el nombre del archivo para guardar los datos (deja vacío para usar 'escaladors_output.txt'): ");
            filePath = scanner.nextLine().trim();

            if (filePath.isEmpty()) {
                filePath = "escaladors_output.txt";
            }
            while (true) {
                try {
                    System.out.println("Guardando los datos en: " + filePath);
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                        writer.write(String.format("%-5d %-20s %-15s %-5s %-10s %-15s %-10s %-10s%n",
                                "ID", "Nom", "Alias", "Edat", "Nivell Max", "Estil Preferit", "Fita", "ID Via Max"));
                        writer.write("--------------------------------------------------------------------------------------------\n");
                        AuxEscalador.readAllEscaladors(writer, connection);
                    }
                    System.out.println("Datos guardados correctamente en: " + filePath);
                    break;
                } catch (IOException e) {
                    System.err.println("Error al guardar los datos: " + e.getMessage() + ". Inténtalo de nuevo.");
                } catch (Exception e) {
                    System.err.println("Error inesperado: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}