package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.FlightDao;
import entity.Flight;
import util.ConnectUtil;

public class AgentService {

	public List<Flight> getFlights() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		try {
			connection = ConnectUtil.getInstance().getConnection();
			return new FlightDao(connection).get();
		} catch (SQLException e) {
			throw e;
		}
	}
}
