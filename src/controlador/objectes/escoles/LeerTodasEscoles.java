package controlador.objectes.escoles;

import dao.SQLite.SQLiteEscolaDAO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

public class LeerTodasEscoles {
    private final SQLiteEscolaDAO escolaDAO;
    private final Connection connection;

    public LeerTodasEscoles(SQLiteEscolaDAO escolaDAO, Connection connection) {
        this.escolaDAO = escolaDAO;
        this.connection = connection;
    }

    public void leerTodos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("escoles.txt"))) {
            writer.write(String.format("%-5s %-20s %-15s %-5s %-10s %-15s %-10s %-10s%n",
                    "ID", "Nom", "Alias", "Edat", "Nivell Max", "Estil Preferit", "Fita", "ID Via Max"));
            writer.write("--------------------------------------------------------------------------------------------\n");
            AuxEscola.readAllEscoles(writer, connection);
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }
}
