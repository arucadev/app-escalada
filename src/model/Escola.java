package model;

public class Escola {
    private int idEscola;
    private String nom;
    private String aproximacio;
    private int numVies;
    private String popularitat;
    private String restriccions;
    private int idPoblacio;

    public Escola(int idEscola, String nom, String aproximacio, int numVies, String popularitat, String restriccions, int idPoblacio) {
        this.idEscola = idEscola;
        this.nom = nom;
        this.aproximacio = aproximacio;
        this.numVies = numVies;
        this.popularitat = popularitat;
        this.restriccions = restriccions;
        this.idPoblacio = idPoblacio;
    }

    public int getIdEscola() {
        return idEscola;
    }

    public void setIdEscola(int idEscola) {
        this.idEscola = idEscola;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAproximacio() {
        return aproximacio;
    }

    public void setAproximacio(String aproximacio) {
        this.aproximacio = aproximacio;
    }

    public int getNumVies() {
        return numVies;
    }

    public void setNumVies(int numVies) {
        this.numVies = numVies;
    }

    public String getPopularitat() {
        return popularitat;
    }

    public void setPopularitat(String popularitat) {
        this.popularitat = popularitat;
    }

    public String getRestriccions() {
        return restriccions;
    }

    public void setRestriccions(String restriccions) {
        this.restriccions = restriccions;
    }

    public int getIdPoblacio() {
        return idPoblacio;
    }

    public void setIdPoblacio(int idPoblacio) {
        this.idPoblacio = idPoblacio;
    }
}