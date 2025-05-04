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

            int id;
            while (true) {
                try {
                    id = AuxEscola.insertID(connection);
                    break;
                } catch (Exception e) {
                    System.err.println("Error: ID inválido. Inténtalo de nuevo.");
                }
            }

            String nombre;
            while (true) {
                try {
                    nombre = AuxEscola.insertName();
                    break;
                } catch (Exception e) {
                    System.err.println("Error: Nombre inválido. Inténtalo de nuevo.");
                }
            }

            String aproximacion;
            while (true) {
                try {
                    System.out.print("Aproximación: ");
                    aproximacion = scanner.nextLine().trim();
                    if (aproximacion.isEmpty()) {
                        throw new IllegalArgumentException("La aproximación no puede estar vacía.");
                    }
                    break;
                } catch (Exception e) {
                    System.err.println("Error: Aproximación inválida. Inténtalo de nuevo.");
                }
            }

            int numVies;
            while (true) {
                try {
                    numVies = AuxEscola.insertPositiveNumber("Número de Vías: ", "El número de vías no puede ser negativo.");
                    break;
                } catch (Exception e) {
                    System.err.println("Error: Número de vías inválido. Inténtalo de nuevo.");
                }
            }

            String popularitat;
            while (true) {
                try {
                    popularitat = AuxEscola.insertPopularitatEscola();
                    break;
                } catch (Exception e) {
                    System.err.println("Error: Popularitat inválida. Inténtalo de nuevo.");
                }
            }

            String restricciones;
            while (true) {
                try {
                    System.out.print("Restricciones: ");
                    restricciones = scanner.nextLine().trim();
                    break;
                } catch (Exception e) {
                    System.err.println("Error: Restricciones inválidas. Inténtalo de nuevo.");
                }
            }

            int idPoblacion;
            while (true) {
                try {
                    idPoblacion = AuxEscola.insertPositiveNumber("ID Población: ", "El ID de la población no puede ser negativo.");
                    break;
                } catch (Exception e) {
                    System.err.println("Error: ID de población inválido. Inténtalo de nuevo.");
                }
            }

            Escola nuevaEscola = new Escola(id, nombre, aproximacion, numVies, popularitat, restricciones, idPoblacion);
            escolaDAO.insertTable(nuevaEscola);
            System.out.println("Escola creada con éxito.");
        } catch (Exception e) {
            System.err.println("Error al crear la escola: " + e.getMessage());
        }
    }
}