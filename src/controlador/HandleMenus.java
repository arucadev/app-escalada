package controlador;

import controlador.objectes.escaladors.*;
import controlador.objectes.escoles.*;
import controlador.objectes.sectors.*;
import dao.SQLite.SQLiteEscaladorDAO;
import dao.SQLite.SQLiteEscolaDAO;
import dao.SQLite.SQLiteSectorDAO;
import dao.SQLite.SQLiteViaDAO;
import vista.Vista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class HandleMenus {

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Usuario\\IdeaProjects\\PillamLtd.Co\\db\\vies_db1.db"); Scanner scan = new Scanner(System.in)) {

            Vista vista = new Vista();
            SQLiteEscolaDAO escolaDAO = new SQLiteEscolaDAO();
            SQLiteSectorDAO sectorDAO = new SQLiteSectorDAO();
            SQLiteEscaladorDAO escaladorDAO = new SQLiteEscaladorDAO();
            SQLiteViaDAO viaDAO = new SQLiteViaDAO();

            escolaDAO.setConnection(connection);
            sectorDAO.setConnection(connection);
            escaladorDAO.setConnection(connection);
            viaDAO.setConnection(connection);

            handleMenuPrincipal(vista, scan, escolaDAO, sectorDAO, escaladorDAO, viaDAO, connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleMenuPrincipal(Vista vista, Scanner scan, SQLiteEscolaDAO escolaDAO, SQLiteSectorDAO sectorDAO, SQLiteEscaladorDAO escaladorDAO, SQLiteViaDAO viaDAO, Connection connection) {
        int option;
        boolean exit = false;
        do {
            vista.menuPrincipal();
            System.out.print("Selecciona una opció: ");
            option = parseInt(scan.nextLine());
            switch (option) {
                case 1:
                    handleMenuCrear(vista, scan, escolaDAO, sectorDAO, escaladorDAO, viaDAO, connection);
                    break;
                case 2:
                    handleMenuModificar(vista, scan, escolaDAO, sectorDAO, escaladorDAO, viaDAO, connection);
                    break;
                case 3:
                    handleMenuLlistar(vista, scan, escolaDAO, sectorDAO, escaladorDAO, viaDAO, connection);
                    break;
                case 4:
                    handleMenuEliminar(vista, scan, escolaDAO, sectorDAO, escaladorDAO, viaDAO, connection);
                    break;
                case 5:
                    handleMenuLlistarTot(vista, scan, escolaDAO, sectorDAO, escaladorDAO, viaDAO, connection);
                    break;
                case 6:
                    handleMenuOpcionsEspecials(vista, scan, escolaDAO, sectorDAO, escaladorDAO, viaDAO, connection);
                    break;
                case 0:
                    System.out.println("Sortir...");
                    exit = true;
                    break;
                default:
                    System.out.println("Opció incorrecte. Introdueix una opció vàlida.");
            }
        } while (!exit);
    }

    private static void handleMenuCrear(Vista vista, Scanner scan, SQLiteEscolaDAO escolaDAO, SQLiteSectorDAO sectorDAO, SQLiteEscaladorDAO escaladorDAO, SQLiteViaDAO viaDAO, Connection connection) {
        int option;
        do {
            vista.menuCrear();
            System.out.print("Selecciona una opció: ");
            option = parseInt(scan.nextLine());
            switch (option) {
                case 1:
                    // handleMenuTipusVia(vista, scan);
                    break;
                case 2:
                    System.out.println("Crear Escola...");
                    new CrearEscola(escolaDAO, scan, connection).crear();
                    break;
                case 3:
                    System.out.println("Crear Sector...");
                    new CrearSector(sectorDAO, connection).crear();
                    break;
                case 4:
                    System.out.println("Crear Escalador...");
                    new CrearEscalador(escaladorDAO, scan, connection).crear();
                    break;
                case 0:
                    System.out.println("Tornar enrere...");
                    break;
                default:
                    System.out.println("Opció incorrecte. Introdueix una opció vàlida.");
            }
        } while (option != 0);
    }

    private static void handleMenuModificar(Vista vista, Scanner scan, SQLiteEscolaDAO escolaDAO, SQLiteSectorDAO sectorDAO, SQLiteEscaladorDAO escaladorDAO, SQLiteViaDAO viaDAO, Connection connection) {
        int option;
        do {
            vista.menuModificar();
            System.out.print("Selecciona una opció: ");
            option = parseInt(scan.nextLine());
            switch (option) {
                case 1:
                    System.out.println("Modificar Via...");
                    break;
                case 2:
                    System.out.println("Modificar Escola...");
                    new ModificarEscola(escolaDAO, scan, connection).modificar();
                    break;
                case 3:
                    System.out.println("Modificar Sector...");
                    new ModificarSector(sectorDAO, connection).modificar();
                    break;
                case 4:
                    System.out.println("Modificar Escalador...");
                    new ModificarEscalador(escaladorDAO, scan, connection).modificar();
                    break;
                case 0:
                    System.out.println("Tornar enrere...");
                    break;
                default:
                    System.out.println("Opció incorrecte. Introdueix una opció vàlida.");
            }
        } while (option != 0);
    }

    private static void handleMenuLlistar(Vista vista, Scanner scan, SQLiteEscolaDAO escolaDAO, SQLiteSectorDAO sectorDAO, SQLiteEscaladorDAO escaladorDAO, SQLiteViaDAO viaDAO, Connection connection) {
        int option;
        do {
            vista.menuLlistar();
            System.out.print("Selecciona una opció: ");
            option = parseInt(scan.nextLine());
            switch (option) {
                case 1:
                    // handleSubMenuLlistarVies(vista, scan);
                    break;
                case 2:
                    System.out.println("Llistar Escola...");
                    new LeerEscola(escolaDAO, scan, connection).leer();
                    break;
                case 3:
                    System.out.println("Llistar Sector...");
                    new LeerSector(sectorDAO, scan, connection).leer();
                    break;
                case 4:
                    System.out.println("Llistar Escaladors...");
                    new LeerEscalador(escaladorDAO, scan, connection).leer();
                    break;
                case 0:
                    System.out.println("Tornar enrere...");
                    break;
                default:
                    System.out.println("Opció incorrecte. Introdueix una opció vàlida.");
            }
        } while (option != 0);
    }

    private static void handleMenuEliminar(Vista vista, Scanner scan, SQLiteEscolaDAO escolaDAO, SQLiteSectorDAO sectorDAO, SQLiteEscaladorDAO escaladorDAO, SQLiteViaDAO viaDAO, Connection connection) {
        int option;
        do {
            vista.menuEliminar();
            System.out.print("Selecciona una opció: ");
            option = parseInt(scan.nextLine());
            switch (option) {
                case 1:
                    System.out.println("Eliminar Via...");
                    break;
                case 2:
                    System.out.println("Eliminar Escola...");
                    new EliminarEscola(escolaDAO, scan, connection).eliminar();
                    break;
                case 3:
                    System.out.println("Eliminar Sector...");
                    new EliminarSector(sectorDAO, connection).eliminar();
                    break;
                case 4:
                    System.out.println("Eliminar Escalador...");
                    new EliminarEscalador(escaladorDAO, scan, connection).eliminar();
                    break;
                case 0:
                    System.out.println("Tornar enrere...");
                    break;
                default:
                    System.out.println("Opció incorrecte. Introdueix una opció vàlida.");
            }
        } while (option != 0);
    }

    public static void handleMenuLlistarTot(Vista vista, Scanner scan, SQLiteEscolaDAO escolaDAO, SQLiteSectorDAO sectorDAO, SQLiteEscaladorDAO escaladorDAO, SQLiteViaDAO viaDAO, Connection connection) {
        int option;
        do {
            vista.menuLlistarTot();
            System.out.print("Selecciona una opció: ");
            option = parseInt(scan.nextLine());
            switch (option) {
                case 1:
                    System.out.println("Llistar Vies...");
                    break;
                case 2:
                    System.out.println("Llistar Escoles...");
                    new LeerTodasEscoles(escolaDAO, connection).leerTodos();
                    break;
                case 3:
                    System.out.println("Llistar Sectors...");
                    new LeerTodosSectors(sectorDAO, connection).leerTodos();
                    break;
                case 4:
                    System.out.println("Llistar Escaladors...");
                    new LeerTodosEscaladores(escaladorDAO, connection).leerTodos();
                    break;
                case 0:
                    System.out.println("Tornar enrere...");
                    break;
                default:
                    System.out.println("Opció incorrecte. Introdueix una opció vàlida.");
            }
        } while (option != 0);
    }

    public static void handleMenuOpcionsEspecials(Vista vista, Scanner scan, SQLiteEscolaDAO escolaDAO, SQLiteSectorDAO sectorDAO, SQLiteEscaladorDAO escaladorDAO, SQLiteViaDAO viaDAO, Connection connection) {
        int option;
        do {
            vista.menuFuncionsEspecials();
            System.out.print("Selecciona una opció: ");
            option = parseInt(scan.nextLine());
            switch (option) {
                case 1:
                    System.out.println("Mostrant les vies disponibles d'una Escola");
                    System.out.print("Introdueix l'ID de l'Escola: ");
                    int idEscola = parseInt(scan.nextLine());
                    escolaDAO.mostrarViesDisponibles(idEscola);
                    break;
                case 2:
                    System.out.println("Cercar vies per dificultat en un rang específic:");
                    System.out.print("Introdueix el grau mínim: ");
                    String minGrau = scan.nextLine();
                    System.out.print("Introdueix el grau màxim: ");
                    String maxGrau = scan.nextLine();
                    viaDAO.cercarViesPerDificultat(minGrau, maxGrau);
                    break;
                case 3:
                    System.out.println("Cercar vies segons estat (Apte, Construcció, Tancada)");
                    System.out.print("Introdueix l'estat: ");
                    String estat = scan.nextLine();
                    viaDAO.cercarViesPerEstat(estat);
                    break;
                case 4:
                    System.out.println("Consultar escoles amb restriccions actives actualment");
                    escolaDAO.consultarEscolesAmbRestriccionsActives();
                    break;
                case 5:
                    System.out.println("Mostrar sectors amb més de X vies disponibles");
                    break;
                case 6:
                    System.out.println("Mostrar escaladors amb el mateix nivell màxim assolit");
                    break;
                case 7:
                    System.out.println("Mostrar les vies que han passat a \"Apte\" recentment");
                    break;
                case 8:
                    System.out.println("Mostrar les vies més llargues d’una escola determinada");
                    break;
                case 0:
                    System.out.println("Tornar enrere...");
                    break;
                default:
                    System.out.println("Opció incorrecte. Introdueix una opció vàlida.");
            }
        } while (option != 0);
    }
}
