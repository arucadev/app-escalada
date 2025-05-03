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
            System.out.print("ID de l'escola a eliminar: ");
            int idEliminar = Integer.parseInt(scanner.nextLine());
            if (!AuxEscola.doesEscolaExist(idEliminar, connection)) {
                System.err.println("La escola no existe.");
            } else {
                escolaDAO.deleteTable(idEliminar);
                System.out.println("Escola eliminado con éxito.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
