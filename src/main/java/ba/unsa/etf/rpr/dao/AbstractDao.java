package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

public abstract class AbstractDao<T extends Idable> {
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

    public abstract T row2object(ResultSet rs) throws FlightsException;

    public abstract Map<String, Object> object2row(T object);

    public void delete(int id) throws FlightsException {
        String sql = "DELETE FROM "+tableName+" WHERE id = ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException exception){
            throw new FlightsException(exception.getMessage(), exception);
        }
    }

    public T add(T item) throws FlightsException{
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try{
            PreparedStatement statement = getConnection().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                statement.setObject(counter, entry.getValue());
                counter++;
            }
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();

            return item;
        }catch (Exception exception){
            throw new FlightsException(exception.getMessage(), exception);
        }
    }

    public T update(T item) throws FlightsException {
        Map<String, Object> row = object2row(item);
        String updateColumns = prepareUpdateParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE id = ?");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.setObject(counter, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            throw new FlightsException(e.getMessage(), e);
        }
    }

    public List<T> executeQuery(String query, Object[] params) throws FlightsException{
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            if (params != null){
                for(int i = 1; i <= params.length; i++){
                    stmt.setObject(i, params[i-1]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            ArrayList<T> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(row2object(rs));
            }
            return resultList;
        } catch (SQLException e) {
            throw new FlightsException(e.getMessage(), e);
        }
    }

    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for(Map.Entry<String, Object> entry: row.entrySet()){
            counter++;
            columns.append(entry.getKey());
            questions.append("?");
            if(row.size() != counter){
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<>(columns.toString(), questions.toString());
    }

    private String prepareUpdateParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            columns.append(entry.getKey()).append("= ?");
            if (row.size() != counter) {
                columns.append(",");
            }
        }
        return columns.toString();
    }

    public T executeQueryUnique(String query, Object[] params) throws FlightsException{
        List<T> result = executeQuery(query, params);
        if (result != null && result.size() == 1){
            return result.get(0);
        }else{
            throw new FlightsException("Object not found");
        }
    }
}
