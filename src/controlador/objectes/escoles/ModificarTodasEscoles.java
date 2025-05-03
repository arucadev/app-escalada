package controlador.objectes.escoles;

import dao.SQLite.SQLiteEscolaDAO;
import model.Escola;
import java.sql.Connection;
import java.util.Scanner;

public class ModificarTodasEscoles {
    private final SQLiteEscolaDAO escolaDAO;
    private final Scanner scanner;
    private final Connection connection;

    public ModificarTodasEscoles(SQLiteEscolaDAO escolaDAO, Scanner scanner, Connection connection) {
        this.escolaDAO = escolaDAO;
        this.scanner = scanner;
        this.connection = connection;
    }

    public void modificar() {
        try {
            System.out.println("--- Actualizar Escola ---");
            System.out.print("ID de la escola a actualizar: ");
            int idActualizar = AuxEscola.insertID(connection);
            scanner.nextLine(); // Clear buffer
            String nuevoNombre = AuxEscola.insertName();
            System.out.print("Nueva Aproximación: ");
            String nuevaAproximacion = scanner.nextLine().trim();
            int nuevoNumVies = AuxEscola.insertPositiveNumber("Nuevo Número de Vías: ", "El número de vías no puede ser negativo.");
            String nuevaPopularitat = AuxEscola.insertPopularitatEscola();
            System.out.print("Nuevas Restricciones: ");
            String nuevasRestricciones = scanner.nextLine().trim();
            int nuevoIdPoblacion = AuxEscola.insertPositiveNumber("Nuevo ID Población: ", "El ID de la población no puede ser negativo.");

            Escola escolaActualizada = new Escola(idActualizar, nuevoNombre, nuevaAproximacion, nuevoNumVies, nuevaPopularitat, nuevasRestricciones, nuevoIdPoblacion);
            escolaDAO.updateTable(escolaActualizada);
            System.out.println("Escola actualizada con éxito.");
        } catch (Exception e) {
            System.err.println("Error al actualizar la escola: " + e.getMessage());
        }
    }
}