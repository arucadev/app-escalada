package controlador.objectes.sectors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class EliminarSector {
    private final Connection connection;

    public EliminarSector(Connection connection) {
        this.connection = connection;
    }

    public void eliminar() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("--- Eliminar Sector ---");
            System.out.print("ID del sector a eliminar: ");
            int idSector = Integer.parseInt(scanner.nextLine().trim());

            if (!doesSectorExist(idSector)) {
                System.err.println("Error: El sector amb aquest ID no existeix.");
                return;
            }

            String sql = "DELETE FROM sectors WHERE id_sector = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, idSector);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Sector eliminat amb èxit.");
                } else {
                    System.err.println("Error: No s'ha pogut eliminar el sector.");
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: L'ID ha de ser un número vàlid.");
        } catch (SQLException e) {
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