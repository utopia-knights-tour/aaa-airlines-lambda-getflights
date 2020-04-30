package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.FlightDao;
import datasource.HikariCPDataSource;
import entity.Flight;

public class AgentService {

	public List<Flight> getFlights() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		try {
			connection = HikariCPDataSource.getConnection();
			return new FlightDao(connection).get();
		} catch (SQLException e) {
			throw e;
		}
	}
}
