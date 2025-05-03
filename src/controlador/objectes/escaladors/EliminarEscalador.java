package controlador.objectes.escaladors;

import dao.SQLite.SQLiteEscaladorDAO;

import java.sql.Connection;
import java.util.Scanner;

public class EliminarEscalador {
    private final SQLiteEscaladorDAO escaladorDAO;
    private final Scanner scanner;
    private final Connection connection;

    public EliminarEscalador(SQLiteEscaladorDAO escaladorDAO, Scanner scanner, Connection connection) {
        this.escaladorDAO = escaladorDAO;
        this.scanner = scanner;
        this.connection = connection;
    }

    public void eliminar() {
        try {
            System.out.println("--- Eliminar Escalador ---");
            System.out.print("ID del escalador a eliminar: ");
            int idEliminar = Integer.parseInt(scanner.nextLine());
            if (!AuxEscalador.doesEscaladorExist(idEliminar, connection)) {
                System.err.println("El escalador no existe.");
            } else {
                escaladorDAO.deleteTable(idEliminar);
                System.out.println("Escalador eliminado con éxito.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}