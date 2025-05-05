package model;

public class ViaClassica extends Via {
    private int idViaClassica;
    private int llargadaTotal;
    private String ancoratgesPermesos;
    private String grauDificultat;

    public ViaClassica(int idVia, String nom, String orientacio, String estat, String ancoratges, String tipusDeRoca, int idCreador, int idSector, int idViaClassica, int llargadaTotal, String ancoratgesPermesos, String grauDificultat) {
        super(idVia, nom, orientacio, estat, ancoratges, tipusDeRoca, idCreador, idSector);
        this.idViaClassica = idViaClassica;
        this.llargadaTotal = llargadaTotal;
        this.ancoratgesPermesos = ancoratgesPermesos;
        this.grauDificultat = grauDificultat;
    }

    public int getIdViaClassica() {
        return idViaClassica;
    }

    public void setIdViaClassica(int idViaClassica) {
        this.idViaClassica = idViaClassica;
    }

    public int getLlargadaTotal() {
        return llargadaTotal;
    }

    public void setLlargadaTotal(int llargadaTotal) {
        this.llargadaTotal = llargadaTotal;
    }

    public String getAncoratgesPermesos() {
        return ancoratgesPermesos;
    }

    public void setAncoratgesPermesos(String ancoratgesPermesos) {
        this.ancoratgesPermesos = ancoratgesPermesos;
    }

    public String getGrauDificultat() {
        return grauDificultat;
    }

    public void setGrauDificultat(String grauDificultat) {
        this.grauDificultat = grauDificultat;
    }
}