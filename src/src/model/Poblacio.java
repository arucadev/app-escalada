package model;

public class Poblacio {
    private int idPoblacio;
    private String nom;

    public Poblacio(int idPoblacio, String nom) {
        this.idPoblacio = idPoblacio;
        this.nom = nom;
    }

    public int getIdPoblacio() {
        return idPoblacio;
    }

    public void setIdPoblacio(int idPoblacio) {
        this.idPoblacio = idPoblacio;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}