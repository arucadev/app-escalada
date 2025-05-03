package controlador.objectes.escaladors;

import dao.SQLite.SQLiteEscaladorDAO;

import java.util.Scanner;

public class LeerEscalador {
    private final SQLiteEscaladorDAO escaladorDAO;
    private final Scanner scanner;

    public LeerEscalador(SQLiteEscaladorDAO escaladorDAO, Scanner scanner) {
        this.escaladorDAO = escaladorDAO;
        this.scanner = scanner;
    }

    public void leer() {
        try {
            System.out.println("--- Leer Escalador ---");
            System.out.print("ID del escalador a leer: ");
            int idLeer = Integer.parseInt(scanner.nextLine());
            escaladorDAO.readTable(idLeer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}