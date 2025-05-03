package controlador.objectes.sectors;

import dao.SQLite.SQLiteSectorDAO;
import model.Sector;
import java.sql.Connection;

public class CrearSector {
    private final SQLiteSectorDAO sectorDAO;
    private final Connection connection;

    public CrearSector(SQLiteSectorDAO sectorDAO, Connection connection) {
        this.sectorDAO = sectorDAO;
        this.connection = connection;
    }

    public void crear() {
        try {
            System.out.println("--- Crear Nou Sector ---");
            int idSector = AuxSector.insertID(connection);
            String nom = AuxSector.insertName();
            double coordenadesLat = AuxSector.insertCoordinate("Coordenades Latitud: ");
            double coordenadesLong = AuxSector.insertCoordinate("Coordenades Longitud: ");
            String aproximacio = AuxSector.insertAproximacio();
            String popularitat = AuxSector.insertPopularitat();
            String restriccions = AuxSector.insertRestriccions();
            int idEscola = AuxSector.insertForeignKey(connection);

            Sector nouSector = new Sector(idSector, nom, coordenadesLat, coordenadesLong, aproximacio, popularitat, restriccions, idEscola);
            sectorDAO.insertTable(nouSector);
            System.out.println("Sector creat amb èxit.");
        } catch (Exception e) {
            System.err.println("Error al crear el sector: " + e.getMessage());
        }
    }
}