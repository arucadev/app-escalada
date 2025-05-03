package controlador.objectes.sectors;

import controlador.objectes.sectors.AuxSector;
import controlador.objectes.sectors.AuxSector;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

public class LeerTodosSectors {
    private final AuxSector sectorDAO;
    private final Connection connection;

    public LeerTodosSectors(AuxSector sectorDAO, Connection connection) {
        this.sectorDAO = sectorDAO;
        this.connection = connection;
    }

    public void leerTodos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("escoles.txt"))) {
            writer.write(String.format("%-5s %-20s %-15s %-5s %-10s %-15s %-10s %-10s%n",
                    "ID", "Nom", "Alias", "Edat", "Nivell Max", "Estil Preferit", "Fita", "ID Via Max"));
            writer.write("--------------------------------------------------------------------------------------------\n");
            AuxSector.readAllSectors(writer, connection);
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }
}
