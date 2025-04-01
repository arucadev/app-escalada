package model;

public class ViaEsportiva extends Via {
    private int idViaEsportiva;
    private int llargadaTotal;
    private String ancoratgesPermesos;
    private String grauDificultat;

    public ViaEsportiva(int idVia, String nom, String orientacio, String estat, String ancoratges, String tipusDeRoca, int idCreador, int idSector, int idViaEsportiva, String ancoratgesPermesos, String grauDificultat, int llargadaTotal) {
        super(idVia, nom, orientacio, estat, ancoratges, tipusDeRoca, idCreador, idSector);
        this.idViaEsportiva = idViaEsportiva;
        this.ancoratgesPermesos = ancoratgesPermesos;
        this.grauDificultat = grauDificultat;
        this.llargadaTotal = llargadaTotal;
    }

    public int getIdViaEsportiva() {
        return idViaEsportiva;
    }

    public void setIdViaEsportiva(int idViaEsportiva) {
        this.idViaEsportiva = idViaEsportiva;
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

    public int getLlargadaTotal() {
        return llargadaTotal;
    }

    public void setLlargadaTotal(int llargadaTotal) {
        this.llargadaTotal = llargadaTotal;
    }
}