package ba.unsa.etf.rpr.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {
    protected Connection connection;

    SQLConnection(){
        try{
            this.connection = DriverManager.getConnection("url", "name", "password");
        }catch (Exception exception){
            System.out.println("Connection with SQL failed.");
        }
    }
}
