package model;

public abstract class Via {
    private int idVia;
    private String nom;
    private String orientacio;
    private String estat;
    private String ancoratges;
    private String tipusDeRoca;
    private int idCreador;
    private int idSector;

    public Via(int idVia, String nom, String orientacio, String estat, String ancoratges, String tipusDeRoca, int idCreador, int idSector) {
        this.idVia = idVia;
        this.nom = nom;
        this.orientacio = orientacio;
        this.estat = estat;
        this.ancoratges = ancoratges;
        this.tipusDeRoca = tipusDeRoca;
        this.idCreador = idCreador;
        this.idSector = idSector;
    }

    // Getters and Setters for Via class
    public int getIdVia() {
        return idVia;
    }

    public void setIdVia(int id) {
        this.idVia = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public int getIdCreador() {
        return idCreador;
    }

    public void setIdCreador(int idCreador) {
        this.idCreador = idCreador;
    }

    public int getIdSector() {
        return idSector;
    }

    public void setIdSector(int idSector) {
        this.idSector = idSector;
    }
}