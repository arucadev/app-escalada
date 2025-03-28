package model.objectes;

public class Escola {
    private String nom;
    private String lloc;
    private int coordenades;
    private String aproximacio;
    private int numVies;
    private String popularitat;
    private String restriccions;

    public Escola(String nom, String lloc, int coordenades, String aproximacio, int numVies, String popularitat, String restriccions) {
        this.nom = nom;
        this.lloc = lloc;
        this.coordenades = coordenades;
        this.aproximacio = aproximacio;
        this.numVies = numVies;
        this.popularitat = popularitat;
        this.restriccions = restriccions;
    }

    // Constructor sin 'lloc' para Sector
    public Escola(String nom, int coordenades, String aproximacio, int numVies, String popularitat, String restriccions) {
        this.nom = nom;
        this.coordenades = coordenades;
        this.aproximacio = aproximacio;
        this.numVies = numVies;
        this.popularitat = popularitat;
        this.restriccions = restriccions;
    }

    // Getters i Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLloc() {
        return lloc;
    }

    public void setLloc(String lloc) {
        this.lloc = lloc;
    }

    public int getcoordenades() {
        return coordenades;
    }

    public void setcoordenades(int coordenades) {
        this.coordenades = coordenades;
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
}
