package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectUtil {
	private static ConnectUtil instance;

	private static final String driver = "com.mysql.cj.jdbc.Driver";

	public static ConnectUtil getInstance() throws ClassNotFoundException {
		if (instance == null) {
			synchronized (ConnectUtil.class) {
				if (instance == null) {
					instance = new ConnectUtil();
					Class.forName(driver);
				}
			}
		}
		return instance;
	}

	public Connection getConnection() throws SQLException {
		synchronized (this) {
			Connection connection = null;
			connection = DriverManager.getConnection(System.getenv("AAA_DATASOURCE_URL"),
					System.getenv("AAA_DATASOURCE_USERNAME"), System.getenv("AAA_DATASOURCE_PASSWORD"));
			connection.setAutoCommit(false);
			return connection;
		}
	}
}
