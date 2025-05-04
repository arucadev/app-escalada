package controlador.objectes.escaladors;

import dao.SQLite.SQLiteEscaladorDAO;
import model.Escalador;
import personalExceptions.EstilPreferitException;

import java.sql.Connection;
import java.util.Scanner;

public class ModificarEscalador {
    private final SQLiteEscaladorDAO escaladorDAO;
    private final Scanner scanner;
    private final Connection connection;

    public ModificarEscalador(SQLiteEscaladorDAO escaladorDAO, Scanner scanner, Connection connection) {
        this.escaladorDAO = escaladorDAO;
        this.scanner = scanner;
        this.connection = connection;
    }

    public void modificar() {
        try {
            System.out.println("--- Actualizar Escalador ---");

            // Validate ID
            int idActualizar;
            while (true) {
                try {
                    System.out.print("ID del escalador a actualizar: ");
                    idActualizar = AuxEscalador.insertID(connection);
                    if (!AuxEscalador.doesEscaladorExist(idActualizar, connection)) {
                        System.err.println("Error: El escalador no existe. Inténtalo de nuevo.");
                    } else {
                        break; // Exit loop if ID is valid and exists
                    }
                } catch (Exception e) {
                    System.err.println("Error: ID inválido. Inténtalo de nuevo.");
                }
            }

            scanner.nextLine(); // Clear buffer

            // Validate Name
            String nuevoNombre;
            while (true) {
                try {
                    System.out.print("Nuevo Nombre: ");
                    nuevoNombre = AuxEscalador.insertName();
                    break; // Exit loop if valid
                } catch (Exception e) {
                    System.err.println("Error: Nombre inválido. Inténtalo de nuevo.");
                }
            }

            // Validate Alias
            String nuevoAlias;
            while (true) {
                try {
                    System.out.print("Nuevo Alias: ");
                    nuevoAlias = AuxEscalador.insertAlias();
                    break; // Exit loop if valid
                } catch (Exception e) {
                    System.err.println("Error: Alias inválido. Inténtalo de nuevo.");
                }
            }

            // Validate Age
            int nuevaEdad;
            while (true) {
                try {
                    System.out.print("Nueva Edad: ");
                    nuevaEdad = AuxEscalador.insertAge();
                    break; // Exit loop if valid
                } catch (Exception e) {
                    System.err.println("Error: Edad inválida. Inténtalo de nuevo.");
                }
            }

            // Validate Nivel Máximo
            String nuevoNivelMax;
            while (true) {
                try {
                    System.out.print("Nuevo Nivel Máximo: ");
                    nuevoNivelMax = AuxEscalador.insertNivellMaxim();
                    break; // Exit loop if valid
                } catch (Exception e) {
                    System.err.println("Error: Nivel Máximo inválido. Inténtalo de nuevo.");
                }
            }

            // Validate Estilo Preferido
            String nuevoEstilo;
            while (true) {
                try {
                    System.out.print("Nuevo Estilo Preferido: ");
                    nuevoEstilo = AuxEscalador.insertEstilPreferit();
                    break; // Exit loop if valid
                } catch (EstilPreferitException e) {
                    System.err.println("Error: " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("Error: Estilo Preferido inválido. Inténtalo de nuevo.");
                }
            }

            // Validate Fita
            System.out.print("Nueva Fita: ");
            String nuevaFita = scanner.nextLine();

            // Validate ID Via Máxima
            int nuevoIdViaMax;
            while (true) {
                try {
                    System.out.print("Nuevo ID Via Máxima: ");
                    nuevoIdViaMax = AuxEscalador.insertViaMax(connection);
                    break; // Exit loop if valid
                } catch (Exception e) {
                    System.err.println("Error: ID Via Máxima inválido. Inténtalo de nuevo.");
                }
            }

            // Update Escalador
            Escalador escaladorActualizado = new Escalador(idActualizar, nuevoNombre, nuevoAlias, nuevaEdad, nuevoNivelMax, nuevoEstilo, nuevaFita, nuevoIdViaMax);
            escaladorDAO.updateTable(escaladorActualizado);
            System.out.println("Escalador actualizado con éxito.");
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }
}