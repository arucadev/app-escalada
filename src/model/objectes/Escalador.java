package model.objectes;

public class Escalador {
    private int id;
    private String nom;
    private String alies;
    private int edat;
    private String nivell;
    private String nomDeLaVia;
    private String estilPreferit;
    private String historial; // ToDo
    private String fita;

    public Escalador(int id, String nom, String alies, int edat, String nivell, String nomDeLaVia, String estilPreferit, String historial, String fita) {
        this.id= id;
        this.nom = nom;
        this.alies = alies;
        this.edat = edat;
        this.nivell = nivell;
        this.nomDeLaVia = nomDeLaVia;
        this.estilPreferit = estilPreferit;
        this.historial = historial;
        this.fita = fita;
    }

    // Getters i Setters
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

    public String getNivell() {
        return nivell;
    }

    public void setNivell(String nivell) {
        this.nivell = nivell;
    }

    public String getNomDeLaVia() {
        return nomDeLaVia;
    }

    public void setNomDeLaVia(String nomDeLaVia) {
        this.nomDeLaVia = nomDeLaVia;
    }

    public String getEstilPreferit() {
        return estilPreferit;
    }

    public void setEstilPreferit(String estilPreferit) {
        this.estilPreferit = estilPreferit;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    public String getFita() {
        return fita;
    }

    public void setFita(String fita) {
        this.fita = fita;
    }
}