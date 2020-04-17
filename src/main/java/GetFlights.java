
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import entity.Airport;
import entity.Flight;
import util.ConnectUtil;

public class GetFlights implements RequestHandler<Map<String, String>, Object> {

	public Object handleRequest(Map<String, String> event, Context context) {
		List<Flight> flights = new ArrayList<Flight>();
		Connection connection = null;
		try {
			connection = ConnectUtil.getInstance().getConnection();
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Flight");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Flight flight = new Flight();
				flight.setId(rs.getLong("flightId"));
				pstmt = connection.prepareStatement("SELECT * FROM Airport WHERE Airport.airportCode = ?");
				pstmt.setString(1, rs.getString("sourceAirport"));
				ResultSet rs2 = pstmt.executeQuery();
				if (rs2.next()) {
					Airport sourceAirport = new Airport();
					sourceAirport.setCode(rs2.getString("airportCode"));
					sourceAirport.setName(rs2.getString("airportName"));
					sourceAirport.setLocation(rs2.getString("airportLocation"));
					flight.setSourceAirport(sourceAirport);
				}
				pstmt = connection.prepareStatement("SELECT * FROM Airport WHERE Airport.airportCode = ?");
				pstmt.setString(1, rs.getString("destinationAirport"));
				rs2 = pstmt.executeQuery();
				if (rs2.next()) {
					Airport destinationAirport = new Airport();
					destinationAirport.setCode(rs2.getString("airportCode"));
					destinationAirport.setName(rs2.getString("airportName"));
					destinationAirport.setLocation(rs2.getString("airportLocation"));
					flight.setDestinationAirport(destinationAirport);
				}
				flight.setDepartureDate(rs.getDate("departureDate").toLocalDate());
				flight.setDepartureTime(rs.getTime("departureTime").toLocalTime());
				flight.setArrivalDate(rs.getDate("arrivalDate").toLocalDate());
				flight.setArrivalTime(rs.getTime("arrivalTime").toLocalTime());
				flight.setSeatsAvailable(rs.getInt("seatsAvailable"));
				flight.setCost(rs.getDouble("cost"));
				flights.add(flight);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return flights;
	}
}