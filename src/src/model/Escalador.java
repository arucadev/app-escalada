package model;

public class Escalador {
    private int idEscalador;
    private String nom;
    private String alies;
    private int edat;
    private String nivellMax;
    private String estilPreferit;
    private String fita;
    private int idViaMax;

    public Escalador(int idEscalador, String nom, String alies, int edat, String nivellMax, String estilPreferit, String fita, int idViaMax) {
        this.idEscalador = idEscalador;
        this.nom = nom;
        this.alies = alies;
        this.edat = edat;
        this.nivellMax = nivellMax;
        this.estilPreferit = estilPreferit;
        this.fita = fita;
        this.idViaMax = idViaMax;
    }

    public int getIdEscalador() {
        return idEscalador;
    }

    public void setIdEscalador(int idEscalador) {
        this.idEscalador = idEscalador;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAlies() {
        return alies;
    }

    public void setAlies(String alies) {
        this.alies = alies;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    public String getNivellMax() {
        return nivellMax;
    }

    public void setNivellMax(String nivellMax) {
        this.nivellMax = nivellMax;
    }

    public String getEstilPreferit() {
        return estilPreferit;
    }

    public void setEstilPreferit(String estilPreferit) {
        this.estilPreferit = estilPreferit;
    }

    public String getFita() {
        return fita;
    }

    public void setFita(String fita) {
        this.fita = fita;
    }

    public int getIdViaMax() {
        return idViaMax;
    }

    public void setIdViaMax(int idViaMax) {
        this.idViaMax = idViaMax;
    }
}