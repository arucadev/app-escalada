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

            int idModificar;
            while (true) {
                try {
                    idModificar = AuxSector.insertID(connection);
                    if (!AuxSector.doesSectorExist(idModificar, connection)) {
                        System.err.println("Error: El sector no existe. Inténtalo de nuevo.");
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    System.err.println("Error: ID no válido. Inténtalo de nuevo.");
                }
            }

            String nom;
            while (true) {
                try {
                    nom = AuxSector.insertName();
                    if (!nom.isEmpty()) {
                        break;
                    } else {
                        System.err.println("El nombre no puede estar vacío. Inténtalo de nuevo.");
                    }
                } catch (Exception e) {
                    System.err.println("Error al introducir el nombre. Inténtalo de nuevo.");
                }
            }

            double coordenadesLat;
            while (true) {
                try {
                    coordenadesLat = AuxSector.insertCoordinate("Coordenades Latitud: ");
                    break;
                } catch (Exception e) {
                    System.err.println("Error: Coordenada de latitud no válida. Inténtalo de nuevo.");
                }
            }

            double coordenadesLong;
            while (true) {
                try {
                    coordenadesLong = AuxSector.insertCoordinate("Coordenades Longitud: ");
                    break;
                } catch (Exception e) {
                    System.err.println("Error: Coordenada de longitud no válida. Inténtalo de nuevo.");
                }
            }

            String aproximacio;
            while (true) {
                try {
                    aproximacio = AuxSector.insertAproximacio();
                    if (!aproximacio.isEmpty()) {
                        break;
                    } else {
                        System.err.println("La aproximación no puede estar vacía. Inténtalo de nuevo.");
                    }
                } catch (Exception e) {
                    System.err.println("Error al introducir la aproximación. Inténtalo de nuevo.");
                }
            }

            String popularitat;
            while (true) {
                try {
                    popularitat = AuxSector.insertPopularitat();
                    if (!popularitat.isEmpty()) {
                        break;
                    } else {
                        System.err.println("La popularidad no puede estar vacía. Inténtalo de nuevo.");
                    }
                } catch (Exception e) {
                    System.err.println("Error al introducir la popularidad. Inténtalo de nuevo.");
                }
            }

            String restriccions;
            while (true) {
                try {
                    restriccions = AuxSector.insertRestriccions();
                    if (!restriccions.isEmpty()) {
                        break;
                    } else {
                        System.err.println("Las restricciones no pueden estar vacías. Inténtalo de nuevo.");
                    }
                } catch (Exception e) {
                    System.err.println("Error al introducir las restricciones. Inténtalo de nuevo.");
                }
            }

            int idEscola;
            while (true) {
                try {
                    idEscola = AuxSector.insertForeignKey(connection);
                    break;
                } catch (Exception e) {
                    System.err.println("Error: ID de la escuela no válido. Inténtalo de nuevo.");
                }
            }

            Sector sectorModificat = new Sector(idModificar, nom, coordenadesLat, coordenadesLong, aproximacio, popularitat, restriccions, idEscola);
            sectorDAO.updateTable(sectorModificat);
            System.out.println("Sector modificat amb èxit.");
        } catch (Exception e) {
            System.err.println("Error al modificar el sector: " + e.getMessage());
        }
    }
}
