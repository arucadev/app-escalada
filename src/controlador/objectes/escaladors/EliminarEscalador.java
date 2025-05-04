package controlador.objectes.escaladors;

import dao.SQLite.SQLiteEscaladorDAO;

import java.sql.Connection;
import java.sql.DriverManager;
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

            int idEliminar;
            while (true) {
                try {
                    System.out.print("ID del escalador a eliminar: ");
                    idEliminar = Integer.parseInt(scanner.nextLine());
                    if (!AuxEscalador.doesEscaladorExist(idEliminar, connection)) {
                        System.err.println("Error: El escalador no existe. Inténtalo de nuevo.");
                    } else {
                        break; // Salir del bucle si el ID es válido y el escalador existe
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error: ID inválido. Debe ser un número entero. Inténtalo de nuevo.");
                }
            }

            escaladorDAO.deleteTable(idEliminar);
            System.out.println("Escalador eliminado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Usuario\\IdeaProjects\\PillamLtd.Co\\db\\vies_db1.db");
             Scanner scanner = new Scanner(System.in)) {

            SQLiteEscaladorDAO escaladorDAO = new SQLiteEscaladorDAO();
            escaladorDAO.setConnection(connection);
            EliminarEscalador eliminarEscalador = new EliminarEscalador(escaladorDAO, scanner, connection);

            eliminarEscalador.eliminar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}