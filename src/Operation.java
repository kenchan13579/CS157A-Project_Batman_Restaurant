import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.*;

public class Operation {
	private Connection conn;
	public Operation(Connection c) {
		this.conn = c;
	}
	public void printMenu() {
		String sql ="SELECT itemName, price, description FROM Menu";
		try {
			Statement statement = (Statement) conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String itemName = rs.getString("itemName");
				double price = rs.getDouble("price");
				String desc = rs.getString("description");
				Item i = new Item(itemName, price, desc);
				System.out.println(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
