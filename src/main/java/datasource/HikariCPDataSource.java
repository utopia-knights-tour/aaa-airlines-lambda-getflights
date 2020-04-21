package datasource;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPDataSource {

	private static HikariConfig config = new HikariConfig("datasource.properties");
	private static HikariDataSource ds;

	static {
		ds = new HikariDataSource(config);

	}

	public static Connection getConnection() throws SQLException {
		Connection connection = ds.getConnection();
		connection.setAutoCommit(false);
		return connection;
	}
}