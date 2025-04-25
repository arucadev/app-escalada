package controlador;

import dao.DBConnection;
import dao.SQLite.SQLiteEscaladorDAO;
import vista.Vista;
import dao.SQLite.*;

import java.sql.Connection;
import java.util.Scanner;

public class ProvaMenu {
    private static final String URL = "jdbc:sqlite:..\\..\\db\\vies_db2.db";
    public static void main(String[] args) {
        Connection connection = null;

        try {
            connection = DBConnection.openCon(URL);
            SQLiteEscaladorDAO escaladorDAO = new SQLiteEscaladorDAO();
            escaladorDAO.readTable(1); // Replace 1 with the ID you want to read
        } finally {
            DBConnection.closeCon(connection);
        }

    }
}
