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
            System.out.print("ID: ");
            int id = AuxEscalador.insertID(connection);
            System.out.print("Nombre: ");
            String nombre = AuxEscalador.insertName();
            System.out.print("Alias: ");
            String alias = AuxEscalador.insertAlias();
            System.out.print("Edad: ");
            int edad = AuxEscalador.insertAge();
            System.out.print("Nivel Máximo: ");
            String nivelMax = AuxEscalador.insertNivellMaxim();
            System.out.print("Estilo Preferido: ");
            String estilo = AuxEscalador.insertEstilPreferit();
            System.out.print("Fita: ");
            String fita = scanner.nextLine();
            System.out.print("ID Via Máxima: ");
            int idViaMax = AuxEscalador.insertViaMax(connection);

            Escalador nuevoEscalador = new Escalador(id, nombre, alias, edad, nivelMax, estilo, fita, idViaMax);
            escaladorDAO.insertTable(nuevoEscalador);
            System.out.println("Escalador creado con éxito.");
        } catch (EstilPreferitException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Usuario\\IdeaProjects\\PillamLtd.Co\\db\\vies_db1.db");
             Scanner scanner = new Scanner(System.in)) {

            SQLiteEscaladorDAO escaladorDAO = new SQLiteEscaladorDAO();
            escaladorDAO.setConnection(connection);
            CrearEscalador crearEscalador = new CrearEscalador(escaladorDAO, scanner, connection);

            crearEscalador.crear(); // Llama al método para crear un nuevo escalador
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}