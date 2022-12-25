package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Arrival;
import ba.unsa.etf.rpr.domain.Departure;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class DepartureDaoImplementation extends SQLConnection implements DepartureDAO{

    /**
     * @param item
     * @return
     */
    @Override
    public Departure update(Departure item) {
        String insert = "UPDATE Departure SET id = ?, country, city, date, arrivalId";
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Departure getById(Integer id) {
        String query = "SELECT * FROM Departure WHERE id = ?";
        try{
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                Departure departure = new Departure();

                departure.setDepartureId(rs.getInt("id"));
                departure.setCountry(rs.getString("country"));
                departure.setCity("city");
                departure.setDateOfDeparture(rs.getDate("date"));
                departure.setArrival(new ArrivalDaoImplementation().getById());

                rs.close();
                return departure;
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
    public Departure add(Departure item) {
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
    public List<Departure> getAll() {
        return null;
    }

    /**
     * @param cityOfDeparture
     * @return
     */
    @Override
    public List<Departure> getByCity(String cityOfDeparture) {
        return null;
    }

    /**
     * @param countryOfDeparture
     * @return
     */
    @Override
    public List<Departure> getByCountry(String countryOfDeparture) {
        return null;
    }

    /**
     * @param dateOfDeparture
     * @return
     */
    @Override
    public List<Departure> getByDate(Date dateOfDeparture) {
        return null;
    }
}
