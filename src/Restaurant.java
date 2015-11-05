import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.jdbc.Connection;

public class Restaurant {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataSource ds = (DataSource) DataSourceFactory.getMySQLDataSource();     
	       
        Connection connection =  null; 
        try {
    		connection = (Connection) ds.getConnection(); 
    	} catch (SQLException e) {
    		System.out.println("Connection Failed! Check output console");
    		e.printStackTrace();
    		return;
    	}
       	if (connection != null) {
    		System.out.println("Connected.");
    	} else {
    		System.out.println("Failed to make connection!");
    	}
       	
	}
	
}
