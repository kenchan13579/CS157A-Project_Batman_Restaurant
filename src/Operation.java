import Model.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.sql.Array;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Operation performs SQL Functions to manipulate data of the database
 */
public class Operation {
	private Connection connection;
	public Operation(Connection c) {
		this.connection = c;
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
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
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
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
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


	/**
	 * Add a customer
	 * @param ln last name
	 * @param fn fist name
	 * @param email email
     * @return true if succeed, false otherwise
     */
	public boolean addCustomer(String ln, String fn, String email) {
		String sql_addcustomer = "INSERT INTO CUSTOMER (email,lastname,firstname) VALUES (?,?,?)";
		try {
			PreparedStatement addCustomer = (PreparedStatement) connection.prepareStatement(sql_addcustomer);
			addCustomer.setString(1, email);
			addCustomer.setString(2, ln);
			addCustomer.setString(3, fn);
			addCustomer.execute();
			addCustomer.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Get a customer by id
	 * @param id customer id
	 * @return a customer
     */
	public Customer getACustomer(int id) {
		String sql = "Select * From Customer where cid = ?";

		try {

			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				Customer customer = new Customer();
				customer.setEmail(rs.getString("email"));
				customer.setFirstName(rs.getString("firstName"));
				customer.setLastName(rs.getString("lastName"));
				customer.setId(rs.getInt("cid"));

				if (statement != null) statement.close();

				return customer;
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error at get a customer");
			return null;
		}
	}

    /**
	 * Get a customer id using email
	 * @param email customer email
	 * @return a customer id
     */
	public int getCID ( String email) {
		String sql = "Select cID FROM Customer WHERE EMAIL=?";
		try {

			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			if ( rs.next()) {
				return rs.getInt("cid"); //cid
			} else {
				return -1; // not exist
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed: "+e.getMessage());
			return -1;
		}
	}
	// / need to check if customer in the database already, if not , add to database ( another method)
	// need another method to get id of available table with given number of seats


	/**
	 * Reserve a table
	 * @param partySize number of people in a party
	 * @param d reservation date
	 * @param tID table id
	 * @param c a customer
     * @return true if succeed, false otherwise
     */
	public boolean reserveTable(int partySize,Date d,int tID , Customer c) {
		PreparedStatement statement = null;
		int customerid = getCID(c.getEmail());
		String sql_reserve ="INSERT INTO Restaurant.Reservation (reservationDate,partySize,cID,tID) values(?, ?, ?,?)";

		try {
			connection.setAutoCommit(false);
			statement = (PreparedStatement) connection.prepareStatement(sql_reserve);
			statement.setDate(1, d);
			statement.setInt(2, partySize);
			statement.setInt(3, customerid);
			statement.setInt(4, tID);
			statement.executeUpdate();
			connection.commit();

			if (statement != null) {
				statement.close();
			}

			connection.setAutoCommit(true);

			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed: "+e.getMessage());
			return false;
		}
	}


	/**
	 * Cancel a reservation
	 * @param email
	 * @param d
     * @return
     */
	public boolean cancelReservation(String email, Date d) {
		boolean success = false;

		int cid = getCID(email);

		if (cid != -1) {
			String queryCancelReservation ="DELETE FROM Reservation WHERE cID=? AND reservationDate=?";
			try {
				PreparedStatement statement = (PreparedStatement) connection.prepareStatement(queryCancelReservation);
				statement.setInt(1, cid);
				statement.setDate(2, d);
				statement.execute();
				statement.close();
				success = true;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				success = false;
				System.out.println("Failed: "+e.getMessage());
			} finally {

				return success;
			}
		} else {
			return false;
		}

	}

	/**
	 * Change a reservation
	 * @param email current user's email
	 * @param date date to change
	 * @param partySize number of guests to change
	 * @return true if success, false otherwise
	 * @throws SQLException
     */

	public boolean changeReservation(String email , Date date, int partySize) throws SQLException {
		boolean success = false;
		PreparedStatement updateReservation = null;
		int customerID = getCID(email);
		String query = "update reservation set reservationDate = ?, partysize = ? where cid = ?";

		try {
			connection.setAutoCommit(false);
			updateReservation = (PreparedStatement) connection.prepareStatement(query);
			updateReservation.setDate(1, date);
			updateReservation.setInt(2, partySize);
			updateReservation.setInt(3, customerID);
			updateReservation.execute();

			connection.commit();
			success = true;

		} catch (SQLException e) {
			e.printStackTrace();
			success = false;
		} finally {
			if (updateReservation != null) {
				updateReservation.close();
			}

			connection.setAutoCommit(true);
			return success;
		}
	}

	/**
	 * Rate a restaurant and give a feedback
	 * Rate uses SQL INSERT INTO
	 * @param stars number of stars
	 * @param feedback user's feedback
	 * @param customer a customer
	 * @return true if rate works fine, false otherwise
	 * @throws SQLException
     */
	public boolean rate(int stars, String feedback, Customer customer) throws SQLException {
		boolean success = false;
		PreparedStatement insertStatement = null;
		int customerID = getCID(customer.getEmail());
		String query = "INSERT INTO Rating (cid, stars, feedback) VALUES (?,?,?)";

		try {
			connection.setAutoCommit(false);
			insertStatement = (PreparedStatement) connection.prepareStatement(query);
			insertStatement.setInt(1, customerID);
			insertStatement.setInt(2, stars);
			insertStatement.setString(3, feedback);
			insertStatement.execute();

			connection.commit();
			success = true;

		} catch (SQLException e) {
			e.printStackTrace();
			success = false;
		} finally {
			if (insertStatement != null) {
				insertStatement.close();
			}

			connection.setAutoCommit(true);
			return success;
		}
	}


	/**
	 * Get ratings and feedbacks
	 * @return list of ratings and feedbacks in Rating objects
	 * @throws SQLException
     */
	public ArrayList<Rating> getRatingsAndFeedbacks() throws SQLException {
		String sql ="SELECT * FROM Rating";

		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			ArrayList<Rating> list = new ArrayList<>();

			while (rs.next()) {
				Rating rating = new Rating();
				rating.setCID(rs.getInt("cID"));
				rating.setFeedback(rs.getString("feedback"));
				rating.setStars(rs.getInt("stars"));

				list.add(rating);

			}

			if (statement != null) {
				statement.close();
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error! Cannot get ratings and feedbacks");
			return null;
		}
	}


	/**
	 * Get average rating
	 * @return average rating in double or -1 if fails
	 * @throws SQLException
     */
	public double getAverageRating () throws SQLException {
		String sql = "SELECT avg(stars)" +
				"FROM Rating";
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			double avgRating = 0;

			if (rs.next()) {
				avgRating = rs.getDouble(1);
			}

			if (statement != null) statement.close();
			return avgRating;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error! Cannot get average rating ");
			return -1;
		}

	}


	public Receipt printReceipt() {
		return null;
	}
	//SELECT *	FROM Model.Receipt 	WHERE cID=? AND date=?


	/**
	 * Get the number of employees by position
	 * @param position position in string
	 * @return the number of employees by position; 0 if position is not specified, -1 if there are errors
     */
	public int numOfEmployeesByType(String position) {
		String sql = "SELECT count(*)\n" +
				"FROM Employee\n" +
				"WHERE position=?";
		if (position == null) {
			return 0;
		}

		try {
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
			statement.setString(1, position);
			ResultSet rs = statement.executeQuery();
			return rs.getInt(0);


		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error! Cannot get numeber of employees by position");
			return -1;
		}
	}


	/**
	 * Get a number of available tables on a specific date
	 * @param date date
	 * @return number of available tables, 0 if date is not specified, -1 if we have sql exception
     */
	public int numOfAvailableTableOn(Date date) {
		String sql = "select (select count(*) from atable) - count(*)\n" +
				"from reservation\n" +
				"where date=?";

		if (date == null) {
			return 0;
		}

		try {
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
			statement.setDate(1, date);
			ResultSet rs = statement.executeQuery();
			statement.close();
			return rs.getInt(0);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error at number of available table on date");
			return -1;
		}
	}


	public ArrayList<Item> checkItemQuantity() {
		return null;
	}

	/**
	 * Get revenue by date
	 * @param date date
	 * @return revenue, -1 if there is sql error
     */
	public double revenue(Date date) {
		String sql = "SELECT sum(subtotal)\n" +
				"FROM Receipt\n" +
				"WHERE date=?";

		try {
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
			statement.setDate(1, date);
			ResultSet rs = statement.executeQuery();
			return rs.getInt(0);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error at get revenue");
			return -1;
		}
	}


	/**
	 * Track previous items ordered by a customer
	 * @param customerID a customer id
	 * @return a list of items that customer ordered. Item objects contain item name and total quantity
     */
	public ArrayList<Item> getItemsOrderedByCustomer(int customerID) {
		String sql = "SELECT itemName, sum(quantity)\n" +
				"\tFROM Receipt JOIN Receipt_Item JOIN Menu\n" +
				"\tWHERE cID=?\n" +
				"\tGROUP BY itemName";

		try {
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
			statement.setInt(1, customerID);
			ResultSet rs = statement.executeQuery();
			ArrayList<Item> list = new ArrayList<>();
			while (rs.next()) {
				String itemName = rs.getString("itemName");
				int sum = rs.getInt(1);
				Item item = new Item();
				item.setItemName(itemName);
				item.setQuantityAvailable(sum);
				list.add(item);
			}

			if (statement != null) {
				statement.close();
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error at get items ordered by a customer");
			return null;
		}
	}

	/**
	 * Get all reservations
	 * @return a list of reservations
     */
	public ArrayList<Reservation> getAllReservations() {
		String sql = "SELECT * FROM Reservation";

		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			ArrayList<Reservation> list = new ArrayList<>();

			while (rs.next()) {
				int tid = rs.getInt("tID");
				int cid = rs.getInt("cID");
				int partySize = rs.getInt("partySize");
				String date = rs.getDate("reservationDate").toString();

				Reservation reservation = new Reservation(tid, cid, partySize, date);
				list.add(reservation);
			}

			if (statement != null) statement.close();

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}


	/**
	 * Get subtotal price of a meal
	 * @param receiptID receipt ID
	 * @return a subtotal price of a meal
     */
	public double getTotalPriceOfAMeal(int receiptID) {
		String sql = "SELECT subtotal\n" +
				"\tFROM Receipt\n" +
				"\tWHERE receiptID=?";

		try {
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
			statement.setInt(1, receiptID);
			ResultSet rs = statement.executeQuery();

			double subtotal = rs.getInt(0);

			if (statement != null) {
				statement.close();
			}

			return subtotal;


		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error at get total price of a meal");
			return -1;
		}
	}


	/**
	 * Get a list of spenders who spend more than 100
	 * @return a list of customers with id, first name, last name
     */
	public ArrayList<Customer> getCustomersWhoSpendsMoreThan100() {
		String sql = "SELECT * FROM Customer " +
				"NATURAL JOIN Receipt " +
				"GROUP BY cID " +
				"HAVING avg(subtotal)> 100;";

		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			ArrayList<Customer> list = new ArrayList<>();
			while (rs.next()) {
				int cid = rs.getInt("cid");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				Customer customer = new Customer();
				customer.setFirstName(firstName);
				customer.setLastName(lastName);
				customer.setId(cid);
				list.add(customer);
			}

			if (statement != null) {
				statement.close();

			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error at get customer who spends more than 100");
			return null;
		}
	}

	/**
	 * Get all customers and all employees
	 * @return a list of all people
     */
	public ArrayList<Person> getAllCustomersAndEmployees() {
		String sql = "SELECT firstname, lastname, email, updatedAt, discount, lastWorked\n" +
				"FROM customer LEFT JOIN employee using(firstname, lastname, email)\n" +
				"UNION\n" +
				"SELECT firstname, lastname, email, updatedAt, discount, lastWorked\n" +
				"FROM customer RIGHT JOIN employee using(firstname, lastname, email)";
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			ArrayList<Person> list = new ArrayList<>();

			while (rs.next()) {
				String fn = rs.getString("firstName");
				String ln = rs.getString("lastName");
				String email = rs.getString("email");

				Person person = new Person(fn, ln, email);
				list.add(person);
			}

			if (statement != null) statement.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Get All Employees
	 * @return a list of all employees
     */
	public ArrayList<Employee> getAllEmployees() {
		String sql = "SELECT * FROM Employee";

		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			ArrayList<Employee> list = new ArrayList<>();

			while (rs.next()) {
				int eid = rs.getInt("eid");
				String fn = rs.getString("firstName");
				String ln = rs.getString("lastName");
				String position = rs.getString("position");
				String email = rs.getString("email");
				String lastworked = rs.getDate("lastworked").toString();

				Employee em = new Employee(fn, ln, email, position, lastworked);
//				em.setId(eid);

				list.add(em);
			}

			if (statement != null) statement.close();

			return  list;


		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Get a list of employees who are also customers
	 * @return a list of employees
     */
	public ArrayList<Employee> getEmployeesWhoAreCustomers() {
		String sql = "select * from employee " +
				"where exists(" +
				"select * from customer " +
				"where Employee.firstName = customer.firstname " +
				"AND employee.lastname = customer.lastname)";

		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			ArrayList<Employee> list = new ArrayList<>();

			while (rs.next()) {
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				int id = rs.getInt("eID");
				String position = rs.getString("position");
				String email = rs.getString("email");
				Date lastWorked = rs.getDate("lastWorked");

				Employee em = new Employee(firstName, lastName, email, position, lastWorked.toString());
//				em.setId(id);
				list.add(em);
			}

			if (statement != null) {
				statement.close();
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error at get a list of employees who are also customers");
			return null;
		}
	}

	/**
	 * Get all customers
	 * @return a list of all customers
     */
	public ArrayList<Customer> getAllCustomers() {
		String sql = "SELECT * FROM Customer";

		try {
			Statement statement = (Statement) connection.createStatement();
			ArrayList<Customer> list = new ArrayList<>();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				int cid = rs.getInt("cid");
				String fn = rs.getString("firstName");
				String ln = rs.getString("lastName");
				String email = rs.getString("email");
				String time = rs.getString("updatedAt");
				String updatedAt = "null";
				if (time != null)
					updatedAt = time.toString();
				int discount = rs.getInt("discount");

				Customer customer = new Customer(fn, ln, email, updatedAt, discount);
				list.add(customer);
			}

			if (statement != null) {
				statement.close();
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Get customers who do not tip
	 * @return a list of customers
     */
	public ArrayList<Customer> getCustomersWhoDoNotTip() {
		String sql = "SELECT *\n" +
				"\tFROM Customer\n" +
				"\tWHERE EXISTS (\n" +
				"\t\tSELECT *\n" +
				"\t\tFROM Receipt\n" +
				"\t\tWHERE Customer.cID=Receipt.cID AND gratuity=0)";

		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			ArrayList<Customer> list = new ArrayList<>();

			while (rs.next()) {
				int customerID = rs.getInt("cid");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("email");
				Date updatedAt = rs.getDate("updatedAt");
				int discount = rs.getInt("discount");

				Customer customer = new Customer(firstName, lastName, email, updatedAt.toString(), discount);

				list.add(customer);
			}

			if (statement != null) {
				statement.close();
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error at get customer who do not tip");
			return null;
		}
	}

	/**
	 * Archive customers with a given date
	 * @param date
	 * @return true if succeed, false otherwise
     */
	public boolean archive(String date) {
		String sql = "call archiveCustomers(?)";


		try {
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
			statement.setString(1, date);
			statement.execute();

			if (statement != null) {
				statement.close();

			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Get all archived Customers
	 * @return a list of all archived customers
     */
	public ArrayList<Customer> getArchivedCustomers() {
		String sql = "SELECT * From arc_customer";
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			ArrayList<Customer> list = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("cid");
				String firstname = rs.getString("firstName");
				String lastname = rs.getString("lastName");
				String email = rs.getString("email");
				Timestamp time = rs.getTimestamp("updatedAt");
				String updatedAt = "null";
				if (time != null)
					updatedAt = time.toString();
				int discount = rs.getInt("discount");

				Customer customer = new Customer(firstname, lastname, email, updatedAt, discount);
//				customer.setId(id);
				list.add(customer);
			}

			if (statement != null) statement.close();

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}




}
