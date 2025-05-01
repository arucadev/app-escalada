package controlador.objectes;

import dao.SQLite.SQLiteSectorDAO;
import model.Sector;

import java.sql.Connection;

public class SectorController {
    private SQLiteSectorDAO sectorDAO;
    
    public SectorController(Connection connection) {
        sectorDAO = new SQLiteSectorDAO();
        sectorDAO.setConnection(connection);
    }
    
    public void crearSector(int idSector, String nom, int coordenadesLat, int coordenadesLong, 
                          String aproximacio, int popularitat, String restriccions, int idEscola) {
        Sector sector = new Sector(idSector, nom, coordenadesLat, coordenadesLong, 
                                  aproximacio, popularitat, restriccions, idEscola);
        sectorDAO.insertTable(sector);
    }
    
    public void mostrarSector(int idSector) {
        sectorDAO.readTable(idSector);
    }
    
    public void actualitzarSector(Sector sector) {
        sectorDAO.updateTable(sector);
    }
    
    public void eliminarSector(int idSector) {
        sectorDAO.deleteTable(idSector);
    }
    
    public void mostrarTotsSectors() {
        sectorDAO.readAll();
    }
}