package controlador.objectes;

import model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViaController implements Controller {
    private MainController mainController;

    public ViaController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void init() {
        // Initialization code if needed
    }

    @Override
    public void close() {
        // Cleanup code if needed
    }

    public void crearViaEsportiva(String nom, String orientacio, String estat,
                                String ancoratges, String tipusRoca, int idCreador,
                                int idSector, String grauDificultat, int llargada) {
        // Generate new IDs
        int idVia = generateNewViaId();
        int idViaEsportiva = generateNewViaEsportivaId();

        // Create Via and ViaEsportiva objects
        ViaEsportiva viaEsportiva = new ViaEsportiva(
            idVia, nom, orientacio, estat, ancoratges, tipusRoca,
            idCreador, idSector, idViaEsportiva, ancoratges, grauDificultat, llargada
        );

        // Insert into database
        mainController.getViaDAO().insertTable(viaEsportiva);
        mainController.getViaEsportivaDAO().insertTable(viaEsportiva);

        System.out.println("Via Esportiva creada amb èxit!");
    }

    public void crearViaClassica(String nom, String orientacio, String estat,
                               String ancoratges, String tipusRoca, int idCreador,
                               int idSector, List<TramsClassica> trams) {
        // Generate new IDs
        int idVia = generateNewViaId();
        int idViaClassica = generateNewViaClassicaId();

        // Calculate total length
        int llargadaTotal = 0;
        for (TramsClassica tram : trams) {
            llargadaTotal += tram.getLlarg();
            tram.setIdViaClassica(idViaClassica);
        }

        // Create Via and ViaClassica objects
        ViaClassica viaClassica = new ViaClassica(
            idVia, nom, orientacio, estat, ancoratges, tipusRoca,
            idCreador, idSector, idViaClassica, llargadaTotal, ancoratges, ""
        );

        // Insert into database
        mainController.getViaDAO().insertTable(viaClassica);
        mainController.getViaClassicaDAO().insertTable(viaClassica);

        // Insert trams
        for (TramsClassica tram : trams) {
            mainController.getTramsClassicaDAO().insertTable(tram);
        }

        System.out.println("Via Clàssica creada amb èxit!");
    }

    public void crearViaGel(String nom, String orientacio, String estat,
                          String ancoratges, String tipusRoca, int idCreador,
                          int idSector, List<TramsGel> trams) {
        // Generate new IDs
        int idVia = generateNewViaId();
        int idViaGel = generateNewViaGelId();

        // Calculate total length
        int llargadaTotal = 0;
        for (TramsGel tram : trams) {
            llargadaTotal += tram.getLlarg();
            tram.setIdViaGel(idViaGel);
        }

        // Create Via and ViaGel objects
        ViaGel viaGel = new ViaGel(
            idVia, nom, orientacio, estat, ancoratges, tipusRoca,
            idCreador, idSector, idViaGel, llargadaTotal, ancoratges
        );

        // Insert into database
        mainController.getViaDAO().insertTable(viaGel);
        mainController.getViaGelDAO().insertTable(viaGel);

        // Insert trams
        for (TramsGel tram : trams) {
            mainController.getTramsGelDAO().insertTable(tram);
        }

        System.out.println("Via Gel creada amb èxit!");
    }

    // Methods to handle all menu options
    public void llistarTotesLesVies() {
        mainController.getViaDAO().readAll();
    }

    public void llistarViesEscola(int idEscola) {
        mainController.getEscolaDAO().mostrarViesDisponibles(idEscola);
    }

    public void llistarViesPerDificultat(String difMin, String difMax) {
        mainController.getViaDAO().cercarViesPerDificultat(difMax, difMin);
    }

    public void llistarViesPerEstat(String estat) {
        mainController.getViaDAO().cercarViesPerEstat(estat);
    }

    // Helper methods to generate new IDs
    private int generateNewViaId() {
        // Logic to generate a unique ID
        return (int)(Math.random() * 1000) + 1;
    }

    private int generateNewViaEsportivaId() {
        return (int)(Math.random() * 1000) + 1;
    }

    private int generateNewViaClassicaId() {
        return (int)(Math.random() * 1000) + 1;
    }

    private int generateNewViaGelId() {
        return (int)(Math.random() * 1000) + 1;
    }

    // Create trams for a via from scanner input
    public List<TramsClassica> crearTramsClassica(Scanner scan) {
        List<TramsClassica> trams = new ArrayList<>();
        boolean addMoreTrams = true;
        int tramId = 1;

        while (addMoreTrams) {
            System.out.print("Introdueix el llarg del tram: ");
            int llarg = Integer.parseInt(scan.nextLine());

            System.out.print("Introdueix el grau de dificultat del tram: ");
            String grauDificultat = scan.nextLine();

            // Create new tram (ID will be set later)
            TramsClassica tram = new TramsClassica(tramId++, llarg, grauDificultat, 0);
            trams.add(tram);

            System.out.println("Tram creat: Llarg = " + llarg + ", Grau de Dificultat = " + grauDificultat);

            System.out.print("Vols afegir un altre tram? (s/n): ");
            String response = scan.nextLine().toLowerCase();
            addMoreTrams = response.equals("s");
        }

        return trams;
    }

    public List<TramsGel> crearTramsGel(Scanner scan) {
        List<TramsGel> trams = new ArrayList<>();
        boolean addMoreTrams = true;
        int tramId = 1;

        while (addMoreTrams) {
            System.out.print("Introdueix el llarg del tram: ");
            int llarg = Integer.parseInt(scan.nextLine());

            System.out.print("Introdueix el grau de dificultat del tram: ");
            String grauDificultat = scan.nextLine();

            // Create new tram (ID will be set later)
            TramsGel tram = new TramsGel(tramId++, llarg, grauDificultat, 0);
            trams.add(tram);

            System.out.println("Tram creat: Llarg = " + llarg + ", Grau de Dificultat = " + grauDificultat);

            System.out.print("Vols afegir un altre tram? (s/n): ");
            String response = scan.nextLine().toLowerCase();
            addMoreTrams = response.equals("s");
        }

        return trams;
    }
}