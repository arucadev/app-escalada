package model;

public class Sector {
    private int idSector;
    private String nom;
    private double coordenadesLat;
    private double coordenadesLong;
    private String aproximacio;
    private String popularitat;
    private String restriccions;
    private int idEscola;

    public Sector(int idSector, String nom, double coordenadesLat, double coordenadesLong, String aproximacio, String popularitat, String restriccions, int idEscola) {
        this.idSector = idSector;
        this.nom = nom;
        this.coordenadesLat = coordenadesLat;
        this.coordenadesLong = coordenadesLong;
        this.aproximacio = aproximacio;
        this.popularitat = popularitat;
        this.restriccions = restriccions;
        this.idEscola = idEscola;
    }

    public int getIdSector() {
        return idSector;
    }

    public void setIdSector(int idSector) {
        this.idSector = idSector;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getCoordenadesLat() {
        return coordenadesLat;
    }

    public void setCoordenadesLat(int coordenadesLat) {
        this.coordenadesLat = coordenadesLat;
    }

    public double getCoordenadesLong() {
        return coordenadesLong;
    }

    public void setCoordenadesLong(int coordenadesLong) {
        this.coordenadesLong = coordenadesLong;
    }

    public String getAproximacio() {
        return aproximacio;
    }

    public void setAproximacio(String aproximacio) {
        this.aproximacio = aproximacio;
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

    public int getIdEscola() {
        return idEscola;
    }

    public void setIdEscola(int idEscola) {
        this.idEscola = idEscola;
    }
}