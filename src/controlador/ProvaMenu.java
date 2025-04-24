package controlador;

import dao.DBConnection;
import dao.mysql.SQLiteEscaladorDAO;
import vista.Vista;
import dao.mysql.*;

import java.sql.Connection;
import java.util.Scanner;

public class ProvaMenu {
    private static final String URL = "jdbc:sqlite:C:\\Users\\Usuario\\Desktop\\DAW 1\\PERSISTENCIA DE DADES\\practicaApEscalada\\vies_db2.db";
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
