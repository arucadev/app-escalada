package model;

public class ViaGel extends Via {
    private int idViaGel;
    private String grauDificultat;
    private int llargadaTotal;
    private String ancoratgesPermesos;

    public ViaGel(int idVia, String nom, String orientacio, String estat, String ancoratges, String tipusDeRoca, int idCreador, int idSector, int idViaGel, String grauDificultat, int llargadaTotal, String ancoratgesPermesos) {
        super(idVia, nom, orientacio, estat, ancoratges, tipusDeRoca, idCreador, idSector);
        this.idViaGel = idViaGel;
        this.grauDificultat = grauDificultat;
        this.llargadaTotal = llargadaTotal;
        this.ancoratgesPermesos = ancoratgesPermesos;
    }

    public int getIdViaGel() {
        return idViaGel;
    }

    public void setIdViaGel(int idViaGel) {
        this.idViaGel = idViaGel;
    }

    public String getGrauDificultat() {
        return grauDificultat;
    }

    public void setGrauDificultat(String grauDificultat) {
        this.grauDificultat = grauDificultat;
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
}