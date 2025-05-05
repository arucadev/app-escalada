package model;

public class TramsClassica {
    private int idTramClassica;
    private int llarg;
    private String grauDificultat;
    private int idViaClassica;

    // Constructor
    public TramsClassica(int idTramClassica, int llarg, String grauDificultat, int idViaClassica) {
        this.idTramClassica = idTramClassica;
        this.llarg = llarg;
        this.grauDificultat = grauDificultat;
        this.idViaClassica = idViaClassica;
    }

    // Getters
    public int getIdTramClassica() {
        return idTramClassica;
    }

    public int getLlarg() {
        return llarg;
    }

    public String getGrauDificultat() {
        return grauDificultat;
    }

    public int getIdViaClassica() {
        return idViaClassica;
    }

    // Setters
    public void setIdTramClassica(int idTramClassica) {
        this.idTramClassica = idTramClassica;
    }

    public void setLlarg(int llarg) {
        this.llarg = llarg;
    }

    public void setGrauDificultat(String grauDificultat) {
        this.grauDificultat = grauDificultat;
    }

    public void setIdViaClassica(int idViaClassica) {
        this.idViaClassica = idViaClassica;
    }
}