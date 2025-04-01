package model;

public class Registre {
    private int idRegistre;
    private int idEscalador;
    private int idVia;
    private String dataAscensio;
    private String estil;

    public Registre(int idRegistre, int idEscalador, int idVia, String dataAscensio, String estil) {
        this.idRegistre = idRegistre;
        this.idEscalador = idEscalador;
        this.idVia = idVia;
        this.dataAscensio = dataAscensio;
        this.estil = estil;
    }

    public int getIdHistorial() {
        return idRegistre;
    }

    public void setIdHistorial(int idRegistre) {
        this.idRegistre = idRegistre;
    }

    public int getIdEscalador() {
        return idEscalador;
    }

    public void setIdEscalador(int idEscalador) {
        this.idEscalador = idEscalador;
    }

    public int getIdVia() {
        return idVia;
    }

    public void setIdVia(int idVia) {
        this.idVia = idVia;
    }

    public String getDataAscensio() {
        return dataAscensio;
    }

    public void setDataAscensio(String dataAscensio) {
        this.dataAscensio = dataAscensio;
    }

    public String getEstil() {
        return estil;
    }

    public void setEstil(String estil) {
        this.estil = estil;
    }
}