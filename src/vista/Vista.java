package vista;

public class Vista {
    public static void menuPrincipal() {
        System.out.println("1. Crear");
        System.out.println("2. Modificar");
        System.out.println("3. Llistar");
        System.out.println("4. Eliminar");
        System.out.println("5. Llistar tot");
        System.out.println("6. Funcions especials");
        System.out.println("0. Sortir");
    }

    public static void menuCrear() {
        System.out.println("Què vols crear?");
        System.out.println("1. Via");
        System.out.println("2. Escola");
        System.out.println("3. Sector");
        System.out.println("4. Escalador");
        System.out.println("0. Tornar enrere");
    }

    public static void menuTipusVia() {
        System.out.println("Quin tipus de via vols crear?");
        System.out.println("1. Via Esportiva");
        System.out.println("2. Via Clàssica");
        System.out.println("3. Via Gel");
        System.out.println("0. Tornar enrere");
    }

    public static void menuModificar() {
        System.out.println("Què vols modificar?");
        System.out.println("1. Via");
        System.out.println("2. Escola");
        System.out.println("3. Sector");
        System.out.println("4. Escalador");
        System.out.println("0. Tornar enrere");
    }

    public static void menuLlistar() {
        System.out.println("Què vols llistar?");
        System.out.println("1. Vies");
        System.out.println("2. Escoles");
        System.out.println("3. Sectors");
        System.out.println("4. Escaladors");
        System.out.println("0. Tornar enrere");
    }

    public static void subMenuLlistarVies() {
        System.out.println("Llistar Vies:");
        System.out.println("1. Totes les vies");
        System.out.println("2. Vies d'una Escola");
        System.out.println("3. Vies per dificultat en un rang específic");
        System.out.println("4. Vies segons estat (Apte, Construcció, Tancada)");
        System.out.println("5. Vies que han passat a 'Apte' recentment");
        System.out.println("6. Vies més llargues d’una escola determinada");
        System.out.println("0. Tornar enrere");
    }

    public static void subMenuLlistarEscoles() {
        System.out.println("Llistar Escoles:");
        System.out.println("1. Totes les escoles");
        System.out.println("2. Escoles amb restriccions actives");
        System.out.println("0. Tornar enrere");
    }

    public static void subMenuLlistarSectors() {
        System.out.println("Llistar Sectors:");
        System.out.println("1. Tots els sectors");
        System.out.println("2. Sectors amb més de X vies disponibles");
        System.out.println("0. Tornar enrere");
    }

    public static void subMenuLlistarEscaladors() {
        System.out.println("Llistar Escaladors:");
        System.out.println("1. Tots els escaladors");
        System.out.println("2. Escaladors amb el mateix nivell màxim assolit");
        System.out.println("0. Tornar enrere");
    }

    public static void menuEliminar() {
        System.out.println("Què vols eliminar?");
        System.out.println("1. Via");
        System.out.println("2. Escola");
        System.out.println("3. Sector");
        System.out.println("4. Escalador");
        System.out.println("0. Tornar enrere");
    }

    public static void menuLlistarTot() {
        System.out.println("Què vols llistar?");
        System.out.println("1. Vies");
        System.out.println("2. Escoles");
        System.out.println("3. Sectors");
        System.out.println("4. Escaladors");
        System.out.println("0. Tornar enrere");
    }

    public static void menuFuncionsEspecials() {
        System.out.println("Quina funció especial vols executar?");
        System.out.println("1. Mostra les vies d'una Escola que es trobaran disponibles");
        System.out.println("2. Cercar vies per dificultat en un rang específic (via, grau, sector, escola)");
        System.out.println("3. Cercar vies segons estat (Apte, Construcció, Tancada)");
        System.out.println("4. Consultar escoles amb restriccions actives actualment");
        System.out.println("5. Mostrar sectors amb més de X vies disponibles");
        System.out.println("6. Mostrar escaladors amb el mateix nivell màxim assolit");
        System.out.println("7. Mostrar les vies que han passat a Apte recentment");
        System.out.println("8. Mostrar les vies més llargues d’una escola determinada");
    }

    public static void intro() {
        System.out.println("-----------------------------");
        System.out.println("----------BENVINGUT----------");
        System.out.println("-----------------------------");
    }
}