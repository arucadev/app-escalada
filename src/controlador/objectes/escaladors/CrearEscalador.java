package controlador.objectes.escaladors;

import dao.SQLite.SQLiteEscaladorDAO;
import model.Escalador;
import personalExceptions.EstilPreferitException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class CrearEscalador {
    private final SQLiteEscaladorDAO escaladorDAO;
    private final Scanner scanner;
    private final Connection connection;

    public CrearEscalador(SQLiteEscaladorDAO escaladorDAO, Scanner scanner, Connection connection) {
        this.escaladorDAO = escaladorDAO;
        this.scanner = scanner;
        this.connection = connection;
    }

    public void crear() {
        try {
            System.out.println("--- Insertar Escalador ---");

            int id;
            while (true) {
                try {
                    System.out.print("ID: ");
                    id = AuxEscalador.insertID(connection);
                    break; // Salir del bucle si la entrada es válida
                } catch (Exception e) {
                    System.err.println("Error: ID inválido. Inténtalo de nuevo.");
                }
            }

            String nombre;
            while (true) {
                try {
                    System.out.print("Nombre: ");
                    nombre = AuxEscalador.insertName();
                    break;
                } catch (Exception e) {
                    System.err.println("Error: Nombre inválido. Inténtalo de nuevo.");
                }
            }

            String alias;
            while (true) {
                try {
                    System.out.print("Alias: ");
                    alias = AuxEscalador.insertAlias();
                    break;
                } catch (Exception e) {
                    System.err.println("Error: Alias inválido. Inténtalo de nuevo.");
                }
            }

            int edad;
            while (true) {
                try {
                    System.out.print("Edad: ");
                    edad = AuxEscalador.insertAge();
                    break;
                } catch (Exception e) {
                    System.err.println("Error: Edad inválida. Inténtalo de nuevo.");
                }
            }

            String nivelMax;
            while (true) {
                try {
                    System.out.print("Nivel Máximo: ");
                    nivelMax = AuxEscalador.insertNivellMaxim();
                    break;
                } catch (Exception e) {
                    System.err.println("Error: Nivel máximo inválido. Inténtalo de nuevo.");
                }
            }

            String estilo;
            while (true) {
                try {
                    System.out.print("Estilo Preferido: ");
                    estilo = AuxEscalador.insertEstilPreferit();
                    break;
                } catch (EstilPreferitException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }

            System.out.print("Fita: ");
            String fita = scanner.nextLine();

            int idViaMax;
            while (true) {
                try {
                    System.out.print("ID Via Máxima: ");
                    idViaMax = AuxEscalador.insertViaMax(connection);
                    break;
                } catch (Exception e) {
                    System.err.println("Error: ID de la vía máxima inválido. Inténtalo de nuevo.");
                }
            }

            Escalador nuevoEscalador = new Escalador(id, nombre, alias, edad, nivelMax, estilo, fita, idViaMax);
            escaladorDAO.insertTable(nuevoEscalador);
            System.out.println("Escalador creado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Usuario\\IdeaProjects\\PillamLtd.Co\\db\\vies_db1.db");
             Scanner scanner = new Scanner(System.in)) {

            SQLiteEscaladorDAO escaladorDAO = new SQLiteEscaladorDAO();
            escaladorDAO.setConnection(connection);
            CrearEscalador crearEscalador = new CrearEscalador(escaladorDAO, scanner, connection);

            crearEscalador.crear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}