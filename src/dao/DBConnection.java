package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    /**
     * Connectar amb una BD indicada en el paràmetre 'url'.
     * @param url Protocol i ruta cap a la BD<br>Exemple: "jdbc:sqlite:..\\..\\db\\vies_db2.db"
     * @return Connexió amb la BD o 'null' si no s'ha pogut connectar
     */
    public static Connection openCon(String url) {
        Connection con = null;

        try {
            con = DriverManager.getConnection(url);
            System.out.println("Connexió establerta");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return con;
    }

    /**
     * Desconnectar d'una BD.
     * @param con Connexió amb la BD
     */
    public static void closeCon(Connection con) {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
