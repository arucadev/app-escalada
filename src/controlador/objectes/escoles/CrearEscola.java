package controlador.objectes.escoles;

import dao.SQLite.SQLiteEscolaDAO;
import model.Escola;
import java.sql.Connection;
import java.util.Scanner;

public class CrearEscola {
    private final SQLiteEscolaDAO escolaDAO;
    private final Scanner scanner;
    private final Connection connection;

    public CrearEscola(SQLiteEscolaDAO escolaDAO, Scanner scanner, Connection connection) {
        this.escolaDAO = escolaDAO;
        this.scanner = scanner;
        this.connection = connection;
    }

    public void crear() {
        try {
            System.out.println("--- Insertar Escola ---");
            int id = AuxEscola.insertID(connection);
            String nombre = AuxEscola.insertName();
            System.out.print("Aproximación: ");
            String aproximacion = scanner.nextLine().trim();
            int numVies = AuxEscola.insertPositiveNumber("Número de Vías: ", "El número de vías no puede ser negativo.");
            String popularitat = AuxEscola.insertPopularitatEscola();
            System.out.print("Restricciones: ");
            String restricciones = scanner.nextLine().trim();
            int idPoblacion = AuxEscola.insertPositiveNumber("ID Población: ", "El ID de la población no puede ser negativo.");

            Escola nuevaEscola = new Escola(id, nombre, aproximacion, numVies, popularitat, restricciones, idPoblacion);
            escolaDAO.insertTable(nuevaEscola);
            System.out.println("Escola creada con éxito.");
        } catch (Exception e) {
            System.err.println("Error al crear la escola: " + e.getMessage());
        }
    }
}