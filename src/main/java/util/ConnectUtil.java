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

	private final String url = "jdbc:mysql://uthopia.c6cfods61mpr.us-east-1.rds.amazonaws.com/uthopia";
	private final String username = "admin";
	private final String password = "admin123";

	public Connection getConnection() throws SQLException {
		Connection connection = null;
		connection = DriverManager.getConnection(url, username, password);
		connection.setAutoCommit(false);
		return connection;
	}
}
