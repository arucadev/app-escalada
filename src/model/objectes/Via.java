package model.objectes;

public class Via {
    private int id;
    private String nom;
    private int Llargada;
    private String grauDificultat;
    private String orientacio;
    private String estat;
    private String lloc;
    private int coordendes;
    private String ancoratges;
    private String tipusDeRoca;
    private String creadaPer;

    public Via(int id, String nom, int Llargada, String grauDificultat, String orientacio, String estat, String lloc, int coordendes, String ancoratges, String tipusDeRoca, String creadaPer) {
        this.id = id;
        this.nom = nom;
        this.Llargada = Llargada;
        this.grauDificultat = grauDificultat;
        this.orientacio = orientacio;
        this.estat = estat;
        this.lloc = lloc;
        this.coordendes = coordendes;
        this.ancoratges = ancoratges;
        this.tipusDeRoca = tipusDeRoca;
        this.creadaPer = creadaPer;
    }

    // Getters and Setters for Via class
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getLlargada() {
        return Llargada;
    }

    public void setLlargada(int Llargada) {
        this.Llargada = Llargada;
    }

    public String getGrauDificultat() {
        return grauDificultat;
    }

    public void setGrauDificultat(String grauDificultat) {
        this.grauDificultat = grauDificultat;
    }

    public String getOrientacio() {
        return orientacio;
    }

    public void setOrientacio(String orientacio) {
        this.orientacio = orientacio;
    }

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public String getLloc() {
        return lloc;
    }

    public void setLloc(String lloc) {
        this.lloc = lloc;
    }

    public int getCoordendes() {
        return coordendes;
    }

    public void setCoordendes(int coordendes) {
        this.coordendes = coordendes;
    }

    public String getAncoratges() {
        return ancoratges;
    }

    public void setAncoratges(String ancoratges) {
        this.ancoratges = ancoratges;
    }

    public String getTipusDeRoca() {
        return tipusDeRoca;
    }

    public void setTipusDeRoca(String tipusDeRoca) {
        this.tipusDeRoca = tipusDeRoca;
    }

    public String getCreadaPer() {
        return creadaPer;
    }

    public void setCreadaPer(String creadaPer) {
        this.creadaPer = creadaPer;
    }
}
