package controlador;

import dao.DBConnection;
import dao.mysql.SQLiteEscaladorDAO;
import model.Escalador;
import personalExceptions.*;

import java.sql.Connection;
import java.util.Scanner;

public class ProvaEscalador {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        String url = "jdbc:sqlite:..\\..\\db\\vies_db2.db";
        Connection connection = null;

        try {
            // Open connection
            connection = DBConnection.openCon(url);
            SQLiteEscaladorDAO escaladorDAO = new SQLiteEscaladorDAO();
            escaladorDAO.setConnection(connection);

            int option;
            do {
                // Display menu
                System.out.println("\n--- Menu Escalador ---");
                System.out.println("1. Crear Escalador");
                System.out.println("2. Leer Escalador");
                System.out.println("3. Actualizar Escalador");
                System.out.println("4. Eliminar Escalador");
                System.out.println("5. Salir");
                System.out.print("Elige una opción: ");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        try {
                            System.out.println("--- Crear Escalador ---");
                            // Create Escalador
                            System.out.print("ID: ");
                            int id = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            System.out.print("Nombre: ");
                            String nombre = scanner.nextLine();
                            System.out.print("Alias: ");
                            String alias = scanner.nextLine();
                            System.out.print("Edad: ");
                            int edad = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            System.out.print("Nivel Máximo: ");
                            String nivelMax = scanner.nextLine();
                            String estilo = null;
                            System.out.print("Estilo Preferido: ");
                            estilo = scanner.nextLine();
                            if (!estilo.equalsIgnoreCase("esportiva") && !estilo.equalsIgnoreCase("clàssica") && !estilo.equalsIgnoreCase("gel")) {
                                throw new EstilPreferitException("Estil preferit no vàlid, ha de ser; esportiva | clàssica | gel");
                            }
                            System.out.print("Fita: ");
                            String fita = scanner.nextLine();
                            System.out.print("ID Via Máxima: ");
                            int idViaMax = scanner.nextInt();
                            Escalador nuevoEscalador = new Escalador(id, nombre, alias, edad, nivelMax, estilo, fita, idViaMax);
                            escaladorDAO.createTable(nuevoEscalador);
                            System.out.println("Escalador creado con éxito.");
                        } catch (EstilPreferitException e) {
                            System.err.println(e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.println("--- Leer Escalador ---");
                        // Read Escalador
                        System.out.print("ID del escalador a leer | ");
                        int idLeer = scanner.nextInt();
                        escaladorDAO.readTable(idLeer);
                        break;

                    case 3:
                        try {
                            System.out.println("--- Actualizar Escalador ---");
                            // Update Escalador
                            System.out.print("ID del escalador a actualizar: ");
                            int idActualizar = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            System.out.print("Nuevo Nombre: ");
                            String nuevoNombre = scanner.nextLine();
                            System.out.print("Nuevo Alias: ");
                            String nuevoAlias = scanner.nextLine();
                            System.out.print("Nueva Edad: ");
                            int nuevaEdad = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            System.out.print("Nuevo Nivel Máximo: ");
                            String nuevoNivelMax = scanner.nextLine();
                            // S'ha de fer en format funcions personals com aquestes
                            String nuevoEstilo = UptadeEstilPreferit();
                            // D'aquesta forma el try/catch final no es tan llarg
                            System.out.print("Nueva Fita: ");
                            String nuevaFita = scanner.nextLine();
                            System.out.print("Nuevo ID Via Máxima: ");
                            int nuevoIdViaMax = scanner.nextInt();
                            Escalador escaladorActualizado = new Escalador(idActualizar, nuevoNombre, nuevoAlias, nuevaEdad, nuevoNivelMax, nuevoEstilo, nuevaFita, nuevoIdViaMax);
                            escaladorDAO.updateTable(escaladorActualizado);
                            System.out.println("Escalador actualizado con éxito.");
                        } catch (personalExceptions.EstilPreferitException e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.println("--- Eliminar Escalador ---");
                        // Delete Escalador
                        System.out.print("ID del escalador a eliminar | ID: ");
                        int idEliminar = scanner.nextInt();
                        escaladorDAO.deleteTable(idEliminar);
                        System.out.println("Escalador eliminado con éxito.");
                        break;

                    case 5:
                        System.out.println("--- Salir ---");
                        // Exit
                        System.out.println("Saliendo del menú...");
                        break;

                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.");
                }
            } while (option != 5);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close connection
            DBConnection.closeCon(connection);
            scanner.close();
        }
    }

    public static String UptadeEstilPreferit() {
        String nuevoEstilo = null;
        try {
            nuevoEstilo = scanner.nextLine();
            if (!nuevoEstilo.equalsIgnoreCase("esportiva") && !nuevoEstilo.equalsIgnoreCase("clàssica") && !nuevoEstilo.equalsIgnoreCase("gel")) {
                throw new personalExceptions.EstilPreferitException("Estil preferit no vàlid, ha de ser; esportiva | clàssica | gel");
            }
        } catch (personalExceptions.EstilPreferitException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return nuevoEstilo;
    }
}