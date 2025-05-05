package model;

public class TramsGel {
    private int idTramGel;
    private int llarg;
    private String grauDificultat;
    private int idViaGel;

    // Constructor
    public TramsGel(int idTramGel, int llarg, String grauDificultat, int idViaGel) {
        this.idTramGel = idTramGel;
        this.llarg = llarg;
        this.grauDificultat = grauDificultat;
        this.idViaGel = idViaGel;
    }

    // Getters
    public int getIdTramGel() {
        return idTramGel;
    }

    public int getLlarg() {
        return llarg;
    }

    public String getGrauDificultat() {
        return grauDificultat;
    }

    public int getIdViaGel() {
        return idViaGel;
    }

    // Setters
    public void setIdTramGel(int idTramGel) {
        this.idTramGel = idTramGel;
    }

    public void setLlarg(int llarg) {
        this.llarg = llarg;
    }

    public void setGrauDificultat(String grauDificultat) {
        this.grauDificultat = grauDificultat;
    }

    public void setIdViaGel(int idViaGel) {
        this.idViaGel = idViaGel;
    }
}