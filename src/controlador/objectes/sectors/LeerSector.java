package controlador.objectes.sectors;

import dao.SQLite.SQLiteSectorDAO;

import java.util.Scanner;

public class LeerSector {
    private final SQLiteSectorDAO sectorDAO;
    private final Scanner scanner;

    public LeerSector(SQLiteSectorDAO sectorDAO, Scanner scanner) {
        this.sectorDAO = sectorDAO;
        this.scanner = scanner;
    }

    public void leer() {
        try {
            System.out.println("--- Leer Sector ---");
            System.out.print("ID de l'sector a llegir: ");
            int idLeer = Integer.parseInt(scanner.nextLine());
            sectorDAO.readTable(idLeer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
