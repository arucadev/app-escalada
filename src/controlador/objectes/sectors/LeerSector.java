package controlador.objectes.sectors;

import dao.SQLite.SQLiteSectorDAO;

import java.sql.Connection;
import java.util.Scanner;

public class LeerSector {
    private final SQLiteSectorDAO sectorDAO;
    private final Scanner scanner;
    private final Connection connection;

    public LeerSector(SQLiteSectorDAO sectorDAO, Scanner scanner, Connection connection) {
        this.sectorDAO = sectorDAO;
        this.scanner = scanner;
        this.connection = connection;
    }

    public void leer() {
        try {
            System.out.println("--- Leer Sector ---");

            int idLeer;
            while (true) {
                try {
                    System.out.print("ID del sector a leer: ");
                    idLeer = Integer.parseInt(scanner.nextLine().trim());
                    if (!AuxSector.doesSectorExist(idLeer, connection)) {
                        System.err.println("Error: El sector no existe. Inténtalo de nuevo.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error: ID inválido. Debe ser un número entero. Inténtalo de nuevo.");
                } catch (Exception e) {
                    System.err.println("Error inesperado: " + e.getMessage());
                }
            }

            sectorDAO.readTable(idLeer);
        } catch (Exception e) {
            System.err.println("Error al leer el sector: " + e.getMessage());
        }
    }
}
