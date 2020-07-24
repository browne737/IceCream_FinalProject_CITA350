package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public  static Connection createConnection() {
        try {
            String DATABASE_URL = "jdbc:mysql://localhost:3306/morrisville_icecream";
            String user ="root";
            String pWord = "";
            Connection conn = DriverManager.getConnection(DATABASE_URL, user, pWord);
            return conn;
        } catch (Exception except) {
            except.printStackTrace();
            return null;
        }
    }
}
