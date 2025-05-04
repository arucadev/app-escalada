package controlador.objectes.sectors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import dao.SQLite.SQLiteSectorDAO;

public class EliminarSector {
    private final SQLiteSectorDAO sectorDAO;
    private final Connection connection;

    public EliminarSector(SQLiteSectorDAO sectorDAO, Connection connection) {
        this.sectorDAO = sectorDAO;
        this.connection = connection;
    }

    public void eliminar() {
        try {
            System.out.println("--- Eliminar Sector ---");

            int idSector;
            while (true) {
                try {
                    System.out.print("ID del sector a eliminar: ");
                    idSector = Integer.parseInt(new java.util.Scanner(System.in).nextLine().trim());
                    if (!AuxSector.doesSectorExist(idSector, connection)) {
                        System.err.println("Error: El sector no existe. Inténtalo de nuevo.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error: ID inválido. Debe ser un número entero. Inténtalo de nuevo.");
                } catch (Exception e) {
                    System.err.println("Error inesperado: " + e.getMessage());
                }
            }
            sectorDAO.deleteTable(idSector);
            System.out.println("Sector eliminado con éxito.");
        } catch (Exception e) {
            System.err.println("Error al eliminar el sector: " + e.getMessage());
        }
    }

    private boolean doesSectorExist(int idSector) {
        String sql = "SELECT COUNT(*) FROM sectors WHERE id_sector = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idSector);
            return stmt.executeQuery().next() && stmt.getResultSet().getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}