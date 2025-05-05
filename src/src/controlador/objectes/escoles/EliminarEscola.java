package controlador.objectes.escoles;

import dao.SQLite.SQLiteEscolaDAO;

import java.sql.Connection;
import java.util.Scanner;

public class EliminarEscola {
    private final SQLiteEscolaDAO escolaDAO;
    private final Scanner scanner;
    private final Connection connection;

    public EliminarEscola(SQLiteEscolaDAO escolaDAO, Scanner scanner, Connection connection) {
        this.escolaDAO = escolaDAO;
        this.scanner = scanner;
        this.connection = connection;
    }

    public void eliminar() {
        try {
            System.out.println("--- Eliminar Escola ---");

            int idEliminar;
            while (true) {
                try {
                    System.out.print("ID de l'escola a eliminar: ");
                    idEliminar = Integer.parseInt(scanner.nextLine().trim());
                    if (!AuxEscola.doesEscolaExist(idEliminar, connection)) {
                        System.err.println("Error: La escola no existe. Inténtalo de nuevo.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error: ID inválido. Debe ser un número entero. Inténtalo de nuevo.");
                }
            }

            escolaDAO.deleteTable(idEliminar);
            System.out.println("Escola eliminada con éxito.");
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }
}