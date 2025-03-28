package controlador;

import vista.Vista;

import java.util.Scanner;

public class ProvaMenu {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int opcio;
        do {
            Vista.intro();
            Vista.menuTaules();
            opcio = scan.nextInt();
        } while (opcio != 0);
    }
}
