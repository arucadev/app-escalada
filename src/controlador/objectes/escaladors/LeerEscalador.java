package controlador.objectes.escaladors;

import dao.SQLite.SQLiteEscaladorDAO;

import java.sql.Connection;
import java.util.Scanner;

public class LeerEscalador {
    private final SQLiteEscaladorDAO escaladorDAO;
    private final Scanner scanner;
    private final Connection connection;

    public LeerEscalador(SQLiteEscaladorDAO escaladorDAO, Scanner scanner, Connection connection) {
        this.escaladorDAO = escaladorDAO;
        this.scanner = scanner;
        this.connection = connection;
    }

    public void leer() {
        try {
            System.out.println("--- Leer Escalador ---");

            int idLeer;
            while (true) {
                try {
                    System.out.print("ID del escalador a leer: ");
                    idLeer = Integer.parseInt(scanner.nextLine());
                    if (!AuxEscalador.doesEscaladorExist(idLeer, connection)) {
                        System.err.println("Error: El escalador no existe. Inténtalo de nuevo.");
                    } else {
                        break; // Exit the loop if the ID is valid and the escalador exists
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error: ID inválido. Debe ser un número entero. Inténtalo de nuevo.");
                }
            }

            escaladorDAO.readTable(idLeer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}