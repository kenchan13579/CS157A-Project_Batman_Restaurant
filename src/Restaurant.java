//import java.sql.*;
//import java.util.Scanner;
//
//public class Restaurant
//{
//
//	public static void main(String[] argv) {
//		Connection connection = ConnectionFactory.getMYSQLConnection();
//		try {
//			Scanner scanner = new Scanner(System.in);
//
//			int choice = 0;
//			do {
//				System.out.println("\n\nWelcome to Restaurant Rerservation System");
//				System.out.println("Options:\n" +
//						"1. Print a list of all customers\n" +
//						"2. Print a full menu\n" +
//						"3. Make a reservation\n" +
//						"4. Print all reservations\n" +
//						"0. Quit\n" +
//						"Please enter your choice: ");
//				choice = scanner.nextInt();
//
//				switch (choice) {
//					case 0:
//						System.out.println("Quiting...");
//						break;
//					case 1:
//						printAllCustomers(connection);
//						break;
//					case 2:
//						printFullMenu(connection);
//						break;
//					case 3:
//						System.out.println("Enter # of the table you want (0-10): ");
//						int tableID = scanner.nextInt();
//						System.out.println("Enter customer ID: ");
//						int customerID = scanner.nextInt();
//						System.out.println("Enter party size (0-4): ");
//						int partySize = scanner.nextInt();
//						System.out.println("Enter reservation date (YYYY-MM-DD):");
//						String date = scanner.next();
//
//						makeReservations(connection, tableID, customerID, partySize, date);
//						break;
////					case 4:
////						System.out.println("Enter # of your reservation: ");
////						int reservationID = scanner.nextInt();
////						break;
//					case 4:
//						printAllReservations(connection);
//				}
//			} while (choice != 0);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//
//	public static void printAllCustomers(Connection connection) throws SQLException {
//		String query = "select * from customer";
//
//		try (Statement statement = connection.createStatement()) {
//			ResultSet resultSet = statement.executeQuery(query);
//
//			System.out.println("======================= ALL CUSTOMERS ======================");
//			System.out.println("cID   First Name	Last Name      Discount     Last Visited");
//
//			while (resultSet.next()) {
//				int customerID = resultSet.getInt("cID");
//				String firstName = resultSet.getString("firstName");
//				String lastName = resultSet.getString("lastName");
//				int discount = resultSet.getInt("discount");
//				String lastVisited = resultSet.getString("lastVisited");
//
//				System.out.format("%-5d %-15s %-10s %5d %20s\n", customerID, firstName, lastName, discount, lastVisited);
//			}
//		} catch (SQLException e) {
//			System.out.println("printAllCustomers fails!");
//			e.printStackTrace();
//		}
//
//	}
//
//	public static void printFullMenu(Connection connection) throws SQLException {
//		String query = "SELECT itemName, price, description from MENU";
//
//		try (Statement statement = connection.createStatement()) {
//			ResultSet resultSet = statement.executeQuery(query);
//
//			System.out.println("================================= FULL MENU =============================");
//			System.out.println("Model.Item Name							Price			Description");
//			while (resultSet.next()) {
//				String itemName = resultSet.getString("itemName");
//				double price = resultSet.getFloat("price");
//				String description = resultSet.getString("description");
//
//				System.out.format("%-35s %-10.2f %-50s\n", itemName, price, description);
//			}
//		} catch (SQLException e) {
//			System.out.println("printFullMenu fails!");
//			e.printStackTrace();
//		}
//	}
//
//	public static void makeReservations(Connection connection, int tableID, int customerID, int partySize, String date) throws SQLException {
//		String query = "INSERT INTO RESERVATION (tID, cID, partySize, reservationDate) VALUES (" +
//				tableID + "," + customerID + "," + partySize +", " + date ")"";
//		try (Statement statement = connection.createStatement()) {
//			System.out.println("Making reservation....");
//			statement.executeUpdate(query);
//			System.out.println("Reservation has been placed.");
//		} catch (SQLException e) {
//			System.out.println("Making reservation fails!");
//			e.printStackTrace();
//		}
//	}
//
////	public static void changeReservations(Connection connection, int tableID, int customerID, int partySize, String date) throws SQLException {
////		String query = "UPDATE RESSERVATION SET tID =" + tableID + "";
////		try (Statement statement = connection.createStatement()) {
////			System.out.println("Making reservation....");
////			statement.executeUpdate(query);
////			System.out.println("Reservation has been placed.");
////		} catch (SQLException e) {
////			System.out.println("Making reservation fails!");
////			e.printStackTrace();
////		}
////	}
//
//	public static void printAllReservations(Connection connection) throws SQLException {
//		String query = "SELECT * from RESERVATION";
//
//		try (Statement statement = connection.createStatement()) {
//			ResultSet resultSet = statement.executeQuery(query);
//
//			System.out.println("=================== RESERVATIONS ===================");
//			System.out.println("Model.Table ID	Model.Customer ID		Party Size			Date");
//			while (resultSet.next()) {
//				int tableID = resultSet.getInt("tID");
//				int customerID = resultSet.getInt("cID");
//				int partySize = resultSet.getInt("partySize");
//				String date = resultSet.getString("reservationDate");
//
//				System.out.format("%-15d %-15d %-15d %-10s\n", tableID, customerID, partySize, date);
//			}
//		} catch (SQLException e) {
//			System.out.println("printAllReservations fails!");
//			e.printStackTrace();
//		}
//	}
//}
//
//
