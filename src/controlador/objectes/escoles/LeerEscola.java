package controlador.objectes.escoles;

import dao.SQLite.SQLiteEscolaDAO;

import java.util.Scanner;

public class LeerEscola {
    private final SQLiteEscolaDAO escolaDAO;
    private final Scanner scanner;

    public LeerEscola(SQLiteEscolaDAO escolaDAO, Scanner scanner) {
        this.escolaDAO = escolaDAO;
        this.scanner = scanner;
    }

    public void leer() {
        try {
            System.out.println("--- Leer Escola ---");
            System.out.print("ID de l'escola a llegir: ");
            int idLeer = Integer.parseInt(scanner.nextLine());
            escolaDAO.readTable(idLeer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
