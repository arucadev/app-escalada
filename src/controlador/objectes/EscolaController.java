package controlador.objectes;

import dao.SQLite.SQLiteEscolaDAO;
import model.Escola;

import java.sql.Connection;

public class EscolaController {
    private SQLiteEscolaDAO escolaDAO;

    public EscolaController(Connection connection) {
        escolaDAO = new SQLiteEscolaDAO();
        escolaDAO.setConnection(connection);
    }

    public void crearEscola(int idEscola, String nom, String aproximacio, int numVies,
                            String popularitat, String restriccions, int idPoblacio) {
        Escola escola = new Escola(idEscola, nom, aproximacio, numVies, popularitat, restriccions, idPoblacio);
        escolaDAO.insertTable(escola);
    }

    public void mostrarEscola(int idEscola) {
        escolaDAO.readTable(idEscola);
    }

    public void actualitzarEscola(Escola escola) {
        escolaDAO.updateTable(escola);
    }

    public void eliminarEscola(int idEscola) {
        escolaDAO.deleteTable(idEscola);
    }

    public void mostrarViesDisponibles(int idEscola) {
        escolaDAO.mostrarViesDisponibles(idEscola);
    }

    public void mostrarTotesEscoles() {
        escolaDAO.readAll();
    }
}