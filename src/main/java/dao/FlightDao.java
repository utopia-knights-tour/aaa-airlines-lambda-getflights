package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Airport;
import entity.Flight;

public class FlightDao {

	private Connection connection;

	public FlightDao(Connection connection) {
		this.connection = connection;
	}

	public List<Flight> get() throws SQLException {
		List<Flight> flights = new ArrayList<Flight>();
		PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Flight");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Flight flight = new Flight();
			flight.setId(rs.getLong("flightId"));
			AirportDao airportDao = new AirportDao(connection);
			Airport sourceAirport = airportDao.get(rs.getString("sourceAirport"));
			flight.setSourceAirport(sourceAirport);
			Airport destinationAirport = airportDao.get(rs.getString("destinationAirport"));
			flight.setDestinationAirport(destinationAirport);
			flight.setDepartureDate(rs.getDate("departureDate").toLocalDate());
			flight.setDepartureTime(rs.getTime("departureTime").toLocalTime());
			flight.setArrivalDate(rs.getDate("arrivalDate").toLocalDate());
			flight.setArrivalTime(rs.getTime("arrivalTime").toLocalTime());
			flight.setSeatsAvailable(rs.getInt("seatsAvailable"));
			flight.setCost(rs.getBigDecimal("cost"));
			flights.add(flight);
		}
		return flights;
	}
}
