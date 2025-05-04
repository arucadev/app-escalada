package controlador.objectes.escoles;

import dao.SQLite.SQLiteEscolaDAO;
import model.Escola;

import java.sql.Connection;
import java.util.Scanner;

public class ModificarEscola {
    private final SQLiteEscolaDAO escolaDAO;
    private final Scanner scanner;
    private final Connection connection;

    public ModificarEscola(SQLiteEscolaDAO escolaDAO, Scanner scanner, Connection connection) {
        this.escolaDAO = escolaDAO;
        this.scanner = scanner;
        this.connection = connection;
    }

    public void modificar() {
        try {
            System.out.println("--- Actualizar Escola ---");

            int idActualizar = AuxEscola.insertID(connection);

            String nuevoNombre;
            while (true) {
                nuevoNombre = AuxEscola.insertName();
                if (!nuevoNombre.isEmpty()) {
                    break;
                } else {
                    System.err.println("El nombre no puede estar vacío. Inténtalo de nuevo.");
                }
            }

            String nuevaAproximacion;
            while (true) {
                System.out.print("Nueva Aproximación: ");
                nuevaAproximacion = scanner.nextLine().trim();
                if (!nuevaAproximacion.isEmpty()) {
                    break;
                } else {
                    System.err.println("La aproximación no puede estar vacía. Inténtalo de nuevo.");
                }
            }

            int nuevoNumVies = AuxEscola.insertPositiveNumber("Nuevo Número de Vías: ", "El número de vías no puede ser negativo.");

            String nuevaPopularitat;
            while (true) {
                nuevaPopularitat = AuxEscola.insertPopularitatEscola();
                if (!nuevaPopularitat.isEmpty()) {
                    break;
                } else {
                    System.err.println("La popularidad no puede estar vacía. Inténtalo de nuevo.");
                }
            }

            String nuevasRestricciones;
            while (true) {
                System.out.print("Nuevas Restricciones: ");
                nuevasRestricciones = scanner.nextLine().trim();
                if (!nuevasRestricciones.isEmpty()) {
                    break;
                } else {
                    System.err.println("Las restricciones no pueden estar vacías. Inténtalo de nuevo.");
                }
            }

            int nuevoIdPoblacion = AuxEscola.insertPositiveNumber("Nuevo ID Población: ", "El ID de la población no puede ser negativo.");

            Escola escolaActualizada = new Escola(idActualizar, nuevoNombre, nuevaAproximacion, nuevoNumVies, nuevaPopularitat, nuevasRestricciones, nuevoIdPoblacion);
            escolaDAO.updateTable(escolaActualizada);
            System.out.println("Escola actualizada con éxito.");
        } catch (Exception e) {
            System.err.println("Error al actualizar la escola: " + e.getMessage());
        }
    }
}