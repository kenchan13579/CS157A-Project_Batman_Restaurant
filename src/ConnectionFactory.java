import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ConnectionFactory {
	public static Connection getMYSQLConnection() {

		Connection connection = null;

		try {
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "root", "");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		return connection;

	}
}
