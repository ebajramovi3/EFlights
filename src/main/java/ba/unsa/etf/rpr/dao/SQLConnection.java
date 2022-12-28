package ba.unsa.etf.rpr.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class SQLConnection {
    protected Connection connection;

    SQLConnection(){

        try{
            FileReader reader = new FileReader("conn.properties");
            Properties p = new Properties();

            p.load(reader);
            String url = p.getProperty("db.link");
            String username = p.getProperty("db.user");
            String password = p.getProperty("db.password");

            this.connection = DriverManager.getConnection(url, username, password);
        }catch (Exception exception){
            System.out.println("Connection with SQL failed.");
        }
    }
}
