package controlador;

import dao.DBConnection;
import dao.mysql.SQLiteEscaladorDAO;
import vista.Vista;
import dao.mysql.*;

import java.sql.Connection;
import java.util.Scanner;

public class Prova1 {
    private static final String url = "jdbc:sqlite:..\\..\\db\\vies_db2.db";

    public static void main(String[] args) {
        String url = "jdbc:sqlite:..\\..\\db\\vies_db2.db";
        Connection connection = null;

        try {
            // Open connection
            connection = DBConnection.openCon(url);
            SQLiteEscaladorDAO escaladorDAO = new SQLiteEscaladorDAO();
            escaladorDAO.setConnection(connection);

            int option;
            do {
                // Display menu
                vista.menuPrincipal();
}