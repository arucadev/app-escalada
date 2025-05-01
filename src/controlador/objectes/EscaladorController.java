package controlador.objectes;

import dao.SQLite.SQLiteEscaladorDAO;
import model.Escalador;

import java.sql.Connection;

public class EscaladorController {
    private SQLiteEscaladorDAO escaladorDAO;

    public EscaladorController(Connection connection) {
        escaladorDAO = new SQLiteEscaladorDAO();
        escaladorDAO.setConnection(connection);
    }

    public void crearEscalador(int idEscalador, String nom, String alies, int edat,
                              String nivellMax, String estilPreferit, String fita, int idViaMax) {
        Escalador escalador = new Escalador(idEscalador, nom, alies, edat,
                                          nivellMax, estilPreferit, fita, idViaMax);
        escaladorDAO.insertTable(escalador);
    }

    public void mostrarEscalador(int idEscalador) {
        escaladorDAO.readTable(idEscalador);
    }

    public void actualitzarEscalador(Escalador escalador) {
        escaladorDAO.updateTable(escalador);
    }

    public void eliminarEscalador(int idEscalador) {
        escaladorDAO.deleteTable(idEscalador);
    }

    public void mostrarTotsEscaladors() {
        escaladorDAO.readAll();
    }
}