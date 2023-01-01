package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class AbstractDao<S extends Idable> {
    private static Connection connection = null;
    private String tableName;

    public AbstractDao(String tableName) {
        this.tableName = tableName;
        if(connection==null) createConnection();
    }

    private static void createConnection(){
        if(AbstractDao.connection==null){
            try{
                FileReader reader = new FileReader("conn.properties");
                Properties p = new Properties();

                p.load(reader);
                String url = p.getProperty("db.link");
                String username = p.getProperty("db.user");
                String password = p.getProperty("db.password");

                connection = DriverManager.getConnection(url, username, password);
            }catch (Exception exception){
                System.out.println("Connection with SQL failed.");
                System.exit(0);
            }
        }
    }

    public static Connection getConnection(){
        return AbstractDao.connection;
    }

    public void setConnection(Connection connection){
        if(AbstractDao.connection!=null) {
            try {
                AbstractDao.connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        AbstractDao.connection = connection;
    }

    public void removeConnection(){
        if(this.connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                //throw new RuntimeException(e);
                e.printStackTrace();
                System.out.println("REMOVE CONNECTION METHOD ERROR: Unable to close connection on database");
            }
        }
    }


}
