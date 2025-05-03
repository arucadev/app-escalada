package controlador.objectes.escaladors;

import dao.SQLite.SQLiteEscaladorDAO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

public class LeerTodosEscaladores {
    private final SQLiteEscaladorDAO escaladorDAO;
    private final Connection connection;

    public LeerTodosEscaladores(SQLiteEscaladorDAO escaladorDAO, Connection connection) {
        this.escaladorDAO = escaladorDAO;
        this.connection = connection;
    }

    public void leerTodos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("escaladors.txt"))) {
            writer.write(String.format("%-5s %-20s %-15s %-5s %-10s %-15s %-10s %-10s%n",
                    "ID", "Nom", "Alias", "Edat", "Nivell Max", "Estil Preferit", "Fita", "ID Via Max"));
            writer.write("--------------------------------------------------------------------------------------------\n");
            AuxEscalador.readAllEscaladors(writer, connection);
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }
}