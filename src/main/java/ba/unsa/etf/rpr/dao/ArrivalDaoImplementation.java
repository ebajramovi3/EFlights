package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Arrival;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ArrivalDaoImplementation extends SQLConnection implements ArrivalDao{
    /**
     * @param cityOfDeparture
     * @return
     */
    @Override
    public List<Arrival> getByCity(String cityOfDeparture) {
        return null;
    }

    /**
     * @param countryOfDeparture
     * @return
     */
    @Override
    public List<Arrival> getByCountry(String countryOfDeparture) {
        return null;
    }

    /**
     * @param dateOfDeparture
     * @return
     */
    @Override
    public List<Arrival> getByDate(Date dateOfDeparture) {
        return null;
    }

    /**
     * @param item
     * @return
     */
    @Override
    public Arrival update(Arrival item) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Arrival getById(Integer id) {
        String query = "SELECT * FROM Arrival WHERE id = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                Arrival arrival = new Arrival();
                arrival.setArrivalId(rs.getInt("id"));
                arrival.setCountry(rs.getString("country"));
                arrival.setCity(rs.getString("city"));
                arrival.setDateOfArrival(rs.getDate("date"));

                rs.close();
                return arrival;
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
    public Arrival add(Arrival item) {
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
    public List<Arrival> getAll() {
        return null;
    }
}
