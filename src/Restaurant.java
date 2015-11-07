import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Restaurant
{

	public static void main(String[] argv) {
		Connection conn = ConnectionFactory.getMYSQLConnection();
		
	}
}