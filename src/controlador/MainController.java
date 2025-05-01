package controlador;

import dao.DBConnection;
import dao.SQLite.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class MainController implements Controller {
    private static final String DB_URL = "jdbc:sqlite:..\\..\\db\\vies_db2.db";
    private Connection connection;
    private Map<String, Controller> controllers;

    // DAO instances
    private SQLiteViaDAO viaDAO;
    private SQLiteEscolaDAO escolaDAO;
    private SQLiteSectorDAO sectorDAO;
    private SQLiteEscaladorDAO escaladorDAO;
    private SQLiteViaEsportivaDAO viaEsportivaDAO;
    private SQLiteViaClassicaDAO viaClassicaDAO;
    private SQLiteViaGelDAO viaGelDAO;
    private SQLiteTramsClassicaDAO tramsClassicaDAO;
    private SQLiteTramsGelDAO tramsGelDAO;

    public MainController() {
        controllers = new HashMap<>();
        init();
    }

    @Override
    public void init() {
        // Open database connection
        connection = DBConnection.openCon(DB_URL);

        // Initialize DAOs
        initializeDAOs();

        // Initialize controllers
        controllers.put("via", new ViaController(this));
        controllers.put("escola", new EscolaController(this));
        controllers.put("sector", new SectorController(this));
        controllers.put("escalador", new EscaladorController(this));
    }

    private void initializeDAOs() {
        // Initialize all DAOs and set connections
        viaDAO = new SQLiteViaDAO();
        viaDAO.setConnection(connection);

        escolaDAO = new SQLiteEscolaDAO();
        escolaDAO.setConnection(connection);

        sectorDAO = new SQLiteSectorDAO();
        sectorDAO.setConnection(connection);

        escaladorDAO = new SQLiteEscaladorDAO();
        escaladorDAO.setConnection(connection);

        viaEsportivaDAO = new SQLiteViaEsportivaDAO();
        viaEsportivaDAO.setConnection(connection);

        viaClassicaDAO = new SQLiteViaClassicaDAO();
        viaClassicaDAO.setConnection(connection);

        viaGelDAO = new SQLiteViaGelDAO();
        viaGelDAO.setConnection(connection);

        tramsClassicaDAO = new SQLiteTramsClassicaDAO();
        tramsClassicaDAO.setConnection(connection);

        tramsGelDAO = new SQLiteTramsGelDAO();
        tramsGelDAO.setConnection(connection);
    }

    @Override
    public void close() {
        // Close database connection
        DBConnection.closeCon(connection);
    }

    public Controller getController(String name) {
        return controllers.get(name);
    }

    // Getters for DAOs
    public SQLiteViaDAO getViaDAO() { return viaDAO; }
    public SQLiteEscolaDAO getEscolaDAO() { return escolaDAO; }
    public SQLiteSectorDAO getSectorDAO() { return sectorDAO; }
    public SQLiteEscaladorDAO getEscaladorDAO() { return escaladorDAO; }
    public SQLiteViaEsportivaDAO getViaEsportivaDAO() { return viaEsportivaDAO; }
    public SQLiteViaClassicaDAO getViaClassicaDAO() { return viaClassicaDAO; }
    public SQLiteViaGelDAO getViaGelDAO() { return viaGelDAO; }
    public SQLiteTramsClassicaDAO getTramsClassicaDAO() { return tramsClassicaDAO; }
    public SQLiteTramsGelDAO getTramsGelDAO() { return tramsGelDAO; }
}