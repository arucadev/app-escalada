package controlador;

import vista.Vista;

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
                    break;
                case 2:
                    System.out.println("Crear Via Clàssica...");
                    handleCrearTrams(scan);
                    break;
                case 3:
                    System.out.println("Crear Via Gel...");
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
                    break;
                case 2:
                    System.out.println("Llistar vies d'una escola...");
                    break;
                case 3:
                    System.out.println("Llistar vies per dificultat...");
                    break;
                case 4:
                    System.out.println("Llistar vies segons estat...");
                    break;
                case 5:
                    System.out.println("Llistar vies que han passat a 'Apte' recentment...");
                    break;
                case 6:
                    System.out.println("Llistar vies més llargues d’una escola...");
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
}
