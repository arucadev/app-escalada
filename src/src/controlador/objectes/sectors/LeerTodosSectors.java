package controlador.objectes.sectors;

import dao.SQLite.SQLiteSectorDAO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;

public class LeerTodosSectors {
    private final SQLiteSectorDAO sectorDAO;
    private final Connection connection;

    public LeerTodosSectors(SQLiteSectorDAO sectorDAO, Connection connection) {
        this.sectorDAO = sectorDAO;
        this.connection = connection;
    }

    public void leerTodos() {
        Scanner scanner = new Scanner(System.in);
        String filePath;

        while (true) {
            try {
                System.out.print("Introduce el nombre del archivo para guardar los datos (deja vacío para usar 'sectors_output.txt'): ");
                filePath = scanner.nextLine().trim();

                if (filePath.isEmpty()) {
                    filePath = "sectors_output.txt";
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                    writer.write(String.format("%-5s %-20s %-15s %-15s %-10s %-20s %-15s %-10s%n",
                            "ID", "Nom", "Aproximacio", "Num Vies", "Popularitat", "Restriccions", "ID Escola", "ID Poblacio"));
                    writer.write("------------------------------------------------------------------------------------------------------------\n");
                    AuxSector.readAllSectors(writer, connection);
                }

                System.out.println("Datos guardados correctamente en: " + filePath);
                break;
            } catch (IOException e) {
                System.err.println("Error al guardar los datos: " + e.getMessage() + ". Inténtalo de nuevo.");
            }
        }
    }
}