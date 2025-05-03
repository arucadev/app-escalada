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
            System.out.print("ID del escalador a actualizar: ");
            int idActualizar = AuxEscalador.insertID(connection);
            scanner.nextLine();
            System.out.print("Nuevo Nombre: ");
            String nuevoNombre = AuxEscalador.insertName();
            System.out.print("Nuevo Alias: ");
            String nuevoAlias = AuxEscalador.insertAlias();
            System.out.print("Nueva Edad: ");
            int nuevaEdad = AuxEscalador.insertAge();
            System.out.print("Nuevo Nivel Máximo: ");
            String nuevoNivelMax = AuxEscalador.insertNivellMaxim();
            System.out.print("Nuevo Estilo Preferido: ");
            String nuevoEstilo = AuxEscalador.insertEstilPreferit();
            System.out.print("Nueva Fita: ");
            String nuevaFita = scanner.nextLine();
            System.out.print("Nuevo ID Via Máxima: ");
            int nuevoIdViaMax = AuxEscalador.insertViaMax(connection);

            Escalador escaladorActualizado = new Escalador(idActualizar, nuevoNombre, nuevoAlias, nuevaEdad, nuevoNivelMax, nuevoEstilo, nuevaFita, nuevoIdViaMax);
            escaladorDAO.updateTable(escaladorActualizado);
            System.out.println("Escalador actualizado con éxito.");
        } catch (EstilPreferitException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}