import java.io.*;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSourceFactory {
	public static DataSource getMySQLDataSource() {
	        
	    	Properties props = new Properties();
	        FileInputStream fis = null;
	        MysqlDataSource mysqlDS = null;
	        try {
	            fis = new FileInputStream("db.properties");
	            props.load(fis);
	            mysqlDS = new MysqlDataSource();
	            mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
	            mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
	            mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
	        } catch (IOException e) {
	        	System.out.println("db.properties is not found");
	            e.printStackTrace();
	        }
	        return mysqlDS;
	      
	    }
}
