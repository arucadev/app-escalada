package controlador.objectes.escoles;

import dao.SQLite.SQLiteEscolaDAO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class LeerTodasEscoles {
    private final SQLiteEscolaDAO escolaDAO;
    private final Connection connection;

    public LeerTodasEscoles(SQLiteEscolaDAO escolaDAO, Connection connection) {
        this.escolaDAO = escolaDAO;
        this.connection = connection;
    }

    public void leerTodos() {
        Scanner scanner = new Scanner(System.in);
        String filePath;

        while (true) {
            try {
                System.out.print("Introduce el nombre del archivo para guardar los datos (deja vacío para usar 'escoles_output.txt'): ");
                filePath = scanner.nextLine().trim();

                if (filePath.isEmpty()) {
                    filePath = "escoles_output.txt";
                }

                System.out.println("Guardando las escoles en: " + filePath);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                    writer.write(String.format("%-5d %-20s %-40s %-10d %-15s %-30s %-10d%n",
                            "ID", "Nom", "Aproximació", "Num Vies", "Popularitat", "Restriccions", "ID Població"));
                    writer.write("------------------------------------------------------------------------------------------------------------\n");
                    AuxEscola.readAllEscoles(writer, connection);
                }
                System.out.println("Datos guardados con éxito en: " + filePath);
                break;
            } catch (IOException e) {
                System.err.println("Error al guardar los datos: " + e.getMessage() + ". Inténtalo de nuevo.");
            }
        }
    }
}
