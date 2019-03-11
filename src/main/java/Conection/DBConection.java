package Conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {


    private static final String URL="jdbc:postgresql://localhost:5432/wg_forge_db";
    private static final String NAME="wg_forge";
    private static final String PASSWORD="a42";

    private Connection connection;

    public DBConection() {

        try {
            connection= DriverManager.getConnection(URL,NAME,PASSWORD);
            System.out.println("связь с БД установленна ");
        } catch (SQLException e) {
            System.out.println("Связь не установленна");
            e.printStackTrace();
        }

    }


    public Connection getConnection() {
        return connection;
    }
}
