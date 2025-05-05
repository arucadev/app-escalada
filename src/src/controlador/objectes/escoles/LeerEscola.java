package controlador.objectes.escoles;

import dao.SQLite.SQLiteEscolaDAO;

import java.sql.Connection;
import java.util.Scanner;

public class LeerEscola {
    private final SQLiteEscolaDAO escolaDAO;
    private final Scanner scanner;
    private final Connection connection;

    public LeerEscola(SQLiteEscolaDAO escolaDAO, Scanner scanner, Connection connection) {
        this.escolaDAO = escolaDAO;
        this.scanner = scanner;
        this.connection = connection;
    }

    public void leer() {
    try {
        System.out.println("--- Leer Escola ---");

        int idLeer;
        while (true) {
            try {
                System.out.print("ID de l'escola a llegir: ");
                idLeer = Integer.parseInt(scanner.nextLine().trim());
                if (!AuxEscola.doesEscolaExist(idLeer, connection)) {
                    System.err.println("Error: La escola no existe. Inténtalo de nuevo.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Error: ID inválido. Debe ser un número entero. Inténtalo de nuevo.");
            } catch (Exception e) {
                System.err.println("Error inesperado: " + e.getMessage());
            }
        }

        escolaDAO.readTable(idLeer);
    } catch (Exception e) {
        System.err.println("Error inesperado: " + e.getMessage());
    }
}
}