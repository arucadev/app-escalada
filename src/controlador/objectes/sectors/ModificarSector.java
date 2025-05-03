package controlador.objectes.sectors;

import dao.SQLite.SQLiteSectorDAO;
import model.Sector;

import java.sql.Connection;

public class ModificarSector {
    private final SQLiteSectorDAO sectorDAO;
    private final Connection connection;

    public ModificarSector(SQLiteSectorDAO sectorDAO, Connection connection) {
        this.sectorDAO = sectorDAO;
        this.connection = connection;
    }

    public void modificar() {
        try {
            System.out.println("--- Modificar Sector ---");
            int idModificar = AuxSector.insertID(connection);
            String nom = AuxSector.insertName();
            double coordenadesLat = AuxSector.insertCoordinate("Coordenades Latitud: ");
            double coordenadesLong = AuxSector.insertCoordinate("Coordenades Longitud: ");
            String aproximacio = AuxSector.insertAproximacio();
            String popularitat = AuxSector.insertPopularitat();
            String restriccions = AuxSector.insertRestriccions();
            int idEscola = AuxSector.insertForeignKey(connection);

            Sector sectorModificat = new Sector(idModificar, nom, coordenadesLat, coordenadesLong, aproximacio, popularitat, restriccions, idEscola);
            sectorDAO.updateTable(sectorModificat);
            System.out.println("Sector modificat amb èxit.");
        } catch (Exception e) {
            System.err.println("Error al modificar el sector: " + e.getMessage());
        }
    }
}
