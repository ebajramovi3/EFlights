package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Destinations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DestinationsDaoImplementation extends SQLConnection implements DestinationsDAO{

    /**
     * @param item
     * @return
     */
    @Override
    public Destinations update(Destinations item) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Destinations searchById(Integer id) {
        String query = "SELECT * FROM destinations WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Destinations destination = new Destinations();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * @param item
     * @return
     */
    @Override
    public Destinations add(Destinations item) {
        return null;
    }

    /**
     * @param id
     */
    @Override
    public void delete(Integer id) {

    }

    /**
     * @return
     */
    @Override
    public List<Destinations> getAll() {
        return null;
    }

    /**
     * @param nameOfCity
     * @return
     */
    @Override
    public List<Destinations> searchByCity(String nameOfCity) {
        return null;
    }

    /**
     * @param nameOfCountry
     * @return
     */
    @Override
    public List<Destinations> searchByCountry(String nameOfCountry) {
        return null;
    }
}
