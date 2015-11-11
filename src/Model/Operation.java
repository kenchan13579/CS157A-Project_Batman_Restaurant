package Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.*;

public class Operation {
	private Connection conn;
	public Operation(Connection c) {
		this.conn = c;
	}
	
	
	/**
	 * retrieve food items from database
	 * @param type beverage or food , or null for combination
	 * @return menu - list of items
	 */
	public ArrayList<Item> getMenu(String type) {
		String sql ="SELECT itemName, price, description FROM Menu";
		if ( type != null) {
			sql += " WHERE type=?";
		}
		try {
			PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
			if ( type != null) {
				statement.setString(0, type);
			}
			ResultSet rs = statement.executeQuery();
			ArrayList<Item> menu = new ArrayList<Item>();// maybe food menu if there is beverage menu
			while (rs.next()) {
				String itemName = rs.getString("itemName");
				double price = rs.getDouble("price");
				String desc = rs.getString("description");
				Item i = new Item(itemName, price, desc);
				menu.add(i);
				System.out.println(i);
			}
			rs.close();
			statement.close();
			return menu;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * get number of available tables
	 * @return number of available tables
	 */
	public int numOfAvailableTable() {
		String sql ="SELECT count(*) FROM aTable WHERE available = TRUE";
		int res = 0;
		try {
			PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if ( rs.next()) {
				res = rs.getInt(0);
			} 
			statement.close();
			rs.close();
			return res;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	public boolean addCustomer(String ln, String fn, String email) {
		String sql_addcustomer = "INSERT INTO CUSTOMER (email,lastname,firstname) VALUES (?,?,?,?)";
		try {
			PreparedStatement addCustomer = (PreparedStatement) conn.prepareStatement(sql_addcustomer);
			addCustomer.setString(0, email);
			addCustomer.setString(1, ln);
			addCustomer.setString(2, fn);
			addCustomer.execute();
			addCustomer.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	public int getCID ( Customer c) {
		String sql = "Select cID FROM Customer WHERE firstname=? AND lastname=? AND EMAIL=?";
		try {
			
			PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
			statement.setString(0, c.getFirstName());
			statement.setString(1, c.getLastName());
			statement.setString(2, c.getEmail());
			ResultSet rs = statement.executeQuery();
			if ( rs.next()) {
				return rs.getInt(0); //cid
			} else {
				return -1; // not exist
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed: "+e.getMessage());
			return -1;
		}
	}
	// need to check if customer in the database already, if not , add to database ( another method)
	// need another method to get id of available table with given number of seats
	public boolean reserveTable(int partySize,Date d,int tID , Customer c) {
		String sql_reversel ="INSERT INTO Reservation (date,partySize,cID,tID) values(?, ?, ("
				+ "Select cID FROM Customer WHERE firstname=? AND lastname=? AND EMAIL=?"
				+ "), ?)";
		try {
					
			PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql_reversel);
			statement.setDate(0, d);
			statement.setInt(1, partySize);
			statement.setString(2,c.getFirstName());
			statement.setString(3, c.getLastName());
			statement.setString(4, c.getEmail());
			statement.setInt(5, tID);
			ResultSet rs = statement.executeQuery();
			statement.close();
			rs.close();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed: "+e.getMessage());
			return false;
		}
	}
	/**
	 * 
	 * @param cID
	 * @param d
	 * @return
	 */
	public boolean cancelReservation(Customer c, Date d) {
		int cId = getCID(c);
		if (cId == -1) {
			System.out.println("No such customer");
			return false;
		}
		String sql ="DELETE FROM Reservation WHERE cID=? AND date=?";
		try {
					
			PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
			statement.setInt(0, cId);
			statement.setDate(1, d);
			ResultSet rs = statement.executeQuery();
			statement.close();
			rs.close();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed: "+e.getMessage());
			return false;
		}
		
	}
	/**
	 * UPDATE Reservation SET partySize=? WHERE cID=? AND date=?;
	 * @param cID
	 * @param d
	 * @param size
	 * @return
	 */
	public boolean changeReservation(int cID , Date d, int size) {
		return true;
	}
	
	/**SELECT *
		FROM Model.Receipt WHERE cID=? AND date=?
	 * @return
	 */
	public Receipt printReceipt() {
		return null;
	}
	//SELECT *	FROM Model.Receipt 	WHERE cID=? AND date=?
	public int numOfEmployees() {
		return 0;
	}
	//SELECT (SELECT count(*) FROM aTable) - count(*) 
	//FROM Reservation
	//WHERE date=?
	public int numOfAvailableTableOn(Date d) {
		return 0;
	}
	// SELECT mID, itemName, quantityAvailable
	//FROM MENU
	public ArrayList<Item> checkItemQuantity() {
		return null;
	}
	//SELECT sum(subtotal)
	//FROM Model.Receipt
	//WHERE date=?
	public double revenue() {
		return 0.0;
	}
	/* Track previous items ordered for a customer */
	/*SELECT itemName, sum(quantity)
	FROM Model.Receipt JOIN Receipt_Item JOIN Model.Menu
	WHERE cID=?
	GROUP BY itemName

	/* Get total price of a meal */
	/*SELECT subtotal
	FROM Model.Receipt
	WHERE receiptID=?*/

	/* Model.Customer give feedback and rating */
	/*INSERT INTO Rating values(null, cID, rating, feedback)*/


	/* Get feedback and ratings */
	/*SELECT stars, feedback
	//FROM Rating

	/* Get average rating */
	//SELECT avg(stars)
	//FROM Rating

	/* Get a list of spenders who spend more than 100  */
/*	SELECT first, last
	FROM CUSTOMER JOIN RECEIPT
	GROUP BY cID
	HAVING SUBTOTAL > 100;
*/
	/* List employees who are also customers */
	/*SELECT first, last
	FROM Model.Employee
	WHERE EXISTS (
		SELECT *
		FROM Model.Customer
		WHERE Model.Employee.first=Model.Customer.first AND Model.Employee.last=Model.Customer.last)

	/* List of customers who do not tip */
	/*SELECT *
	FROM Model.Customer
	WHERE EXISTS (
		SELECT *
		FROM Model.Receipt
		WHERE Model.Customer.cID=Model.Receipt.cID AND gratuity=0)
*/
}
