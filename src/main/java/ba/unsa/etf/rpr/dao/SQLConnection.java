package ba.unsa.etf.rpr.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class SQLConnection {
    protected Connection connection;

    SQLConnection(){

        try{
            FileReader reader = new FileReader("db.properties");

            Properties p = new Properties();
            p.load(reader);
            this.connection = DriverManager.getConnection(p.getProperty("link"), p.getProperty("user"), p.getProperty("password"));
        }catch (Exception exception){
            System.out.println("Connection with SQL failed.");
        }
    }
}
