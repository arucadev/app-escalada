package controlador;

import vista.Vista;
import controladors.objectes.vies.CrearVia;
import controladors.objectes.vies.EliminarVia;
import controladors.objectes.vies.LlistarVia;
import controladors.objectes.vies.ModificarVia;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class handleMenus {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Vista vista = new Vista();
        handleMenuPrincipal(vista, scan);
    }

    private static void handleMenuPrincipal(Vista vista, Scanner scan) {
        int option;
        boolean exit = false;
        do {
            vista.menuPrincipal();
            System.out.print("Selecciona una opció: ");
            option = parseInt(scan.nextLine());
            switch (option) {
                case 1:
                    handleMenuCrear(vista, scan);
                    break;
                case 2:
                    handleMenuModificar(vista, scan);
                    break;
                case 3:
                    handleMenuLlistar(vista, scan);
                    break;
                case 4:
                    handleMenuEliminar(vista, scan);
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

    private static void handleMenuCrear(Vista vista, Scanner scan) {
        int option;
        do {
            vista.menuCrear();
            System.out.print("Selecciona una opció: ");
            option = parseInt(scan.nextLine());
            switch (option) {
                case 1:
                    handleMenuTipusVia(vista, scan);
                    break;
                case 2:
                    System.out.println("Crear Escola...");
                    break;
                case 3:
                    System.out.println("Crear Sector...");
                    break;
                case 4:
                    System.out.println("Crear Escalador...");
                    break;
                case 0:
                    System.out.println("Tornar enrere...");
                    break;
                default:
                    System.out.println("Opció incorrecte. Introdueix una opció vàlida.");
            }
        } while (option != 0);
    }

    private static void handleMenuTipusVia(Vista vista, Scanner scan) {
        int option;
        do {
            vista.menuTipusVia();
            System.out.print("Selecciona una opció: ");
            option = parseInt(scan.nextLine());
            switch (option) {
                case 1:
                    System.out.println("Crear Via Esportiva...");
                    CrearVia.crearViaEsportiva();
                    break;
                case 2:
                    System.out.println("Crear Via Clàssica...");
                    CrearVia.crearViaClassica();
                    handleCrearTrams(scan);
                    break;
                case 3:
                    System.out.println("Crear Via Gel...");
                    CrearVia.crearViaGel();
                    handleCrearTrams(scan);
                    break;
                case 0:
                    System.out.println("Tornar enrere...");
                    break;
                default:
                    System.out.println("Opció incorrecte. Introdueix una opció vàlida.");
            }
        } while (option != 0);
    }

    private static void handleCrearTrams(Scanner scan) {
        System.out.println("Introdueix els Trams per a la Via:");
        boolean addMoreTrams = true;
        while (addMoreTrams) {
            System.out.print("Introdueix el llarg del tram: ");
            double llarg = Double.parseDouble(scan.nextLine());
            System.out.print("Introdueix el grau de dificultat del tram: ");
            String grauDificultat = scan.nextLine();

            System.out.println("Tram creat: Llarg = " + llarg + ", Grau de Dificultat = " + grauDificultat);

            System.out.print("Vols afegir un altre tram? (s/n): ");
            String response = scan.nextLine().toLowerCase();
            addMoreTrams = response.equals("s");
        }
        System.out.println("Tots els trams han estat afegits.");
    }

    private static void handleMenuModificar(Vista vista, Scanner scan) {
        int option;
        do {
            vista.menuModificar();
            System.out.print("Selecciona una opció: ");
            option = parseInt(scan.nextLine());
            switch (option) {
                case 1:
                    System.out.println("Modificar Via...");
                    handleModificarVia(vista, scan);
                    break;
                case 2:
                    System.out.println("Modificar Escola...");
                    break;
                case 3:
                    System.out.println("Modificar Sector...");
                    break;
                case 4:
                    System.out.println("Modificar Escalador...");
                    break;
                case 0:
                    System.out.println("Tornar enrere...");
                    break;
                default:
                    System.out.println("Opció incorrecte. Introdueix una opció vàlida.");
            }
        } while (option != 0);
    }

    private static void handleModificarVia(Vista vista, Scanner scan) {
        int option;
        do {
            vista.menuTipusVia(); // Reutilizando el menú de tipos de vía
            System.out.print("Selecciona el tipus de via a modificar: ");
            option = parseInt(scan.nextLine());
            switch (option) {
                case 1:
                    System.out.println("Modificar Via Esportiva...");
                    ModificarVia.modificarViaEsportiva();
                    break;
                case 2:
                    System.out.println("Modificar Via Clàssica...");
                    ModificarVia.modificarViaClassica();
                    break;
                case 3:
                    System.out.println("Modificar Via Gel...");
                    ModificarVia.modificarViaGel();
                    break;
                case 0:
                    System.out.println("Tornar enrere...");
                    break;
                default:
                    System.out.println("Opció incorrecte. Introdueix una opció vàlida.");
            }
        } while (option != 0);
    }

    private static void handleMenuLlistar(Vista vista, Scanner scan) {
        int option;
        do {
            vista.menuLlistar();
            System.out.print("Selecciona una opció: ");
            option = parseInt(scan.nextLine());
            switch (option) {
                case 1:
                    handleSubMenuLlistarVies(vista, scan);
                    break;
                case 2:
                    vista.subMenuLlistarEscoles();
                    break;
                case 3:
                    vista.subMenuLlistarSectors();
                    break;
                case 4:
                    vista.subMenuLlistarEscaladors();
                    break;
                case 0:
                    System.out.println("Tornar enrere...");
                    break;
                default:
                    System.out.println("Opció incorrecte. Introdueix una opció vàlida.");
            }
        } while (option != 0);
    }

    private static void handleSubMenuLlistarVies(Vista vista, Scanner scan) {
        int option;
        do {
            vista.subMenuLlistarVies();
            System.out.print("Selecciona una opció: ");
            option = parseInt(scan.nextLine());
            switch (option) {
                case 1:
                    System.out.println("Llistar totes les vies...");
                    LlistarVia.llistarTotesLesVies();
                    break;
                case 2:
                    System.out.println("Llistar vies d'una escola...");
                    LlistarVia.llistarViesEscola();
                    break;
                case 3:
                    System.out.println("Llistar vies per dificultat...");
                    LlistarVia.llistarViesPerDificultat();
                    break;
                case 4:
                    System.out.println("Llistar vies segons estat...");
                    LlistarVia.llistarViesPerEstat();
                    break;
                case 5:
                    System.out.println("Llistar vies que han passat a 'Apte' recentment...");
                    LlistarVia.llistarViesAptesRecentment();
                    break;
                case 6:
                    System.out.println("Llistar vies més llargues d'una escola...");
                    LlistarVia.llistarViesMesLlargues();
                    break;
                case 0:
                    System.out.println("Tornar enrere...");
                    break;
                default:
                    System.out.println("Opció incorrecte. Introdueix una opció vàlida.");
            }
        } while (option != 0);
    }

    private static void handleMenuEliminar(Vista vista, Scanner scan) {
        int option;
        do {
            vista.menuEliminar();
            System.out.print("Selecciona una opció: ");
            option = parseInt(scan.nextLine());
            switch (option) {
                case 1:
                    System.out.println("Eliminar Via...");
                    handleEliminarVia(vista, scan);
                    break;
                case 2:
                    System.out.println("Eliminar Escola...");
                    break;
                case 3:
                    System.out.println("Eliminar Sector...");
                    break;
                case 4:
                    System.out.println("Eliminar Escalador...");
                    break;
                case 0:
                    System.out.println("Tornar enrere...");
                    break;
                default:
                    System.out.println("Opció incorrecte. Introdueix una opció vàlida.");
            }
        } while (option != 0);
    }

    private static void handleEliminarVia(Vista vista, Scanner scan) {
        int option;
        do {
            vista.menuTipusVia(); // Reutilizando el menú de tipos de vía
            System.out.print("Selecciona el tipus de via a eliminar: ");
            option = parseInt(scan.nextLine());
            switch (option) {
                case 1:
                    System.out.println("Eliminar Via Esportiva...");
                    EliminarVia.eliminarViaEsportiva();
                    break;
                case 2:
                    System.out.println("Eliminar Via Clàssica...");
                    EliminarVia.eliminarViaClassica();
                    break;
                case 3:
                    System.out.println("Eliminar Via Gel...");
                    EliminarVia.eliminarViaGel();
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