package buchung;

import java.util.*;
import java.sql.*;
import java.io.*;

/**
 * Helper class for handling low-level JDBC calls to the database
 *
 */
public class GuestDAO {

	private Connection myConn;
	
	public GuestDAO() throws Exception {
		
		// connect to the database. Takes the information from flightdata.properties
		
		Properties props = new Properties();
		
		// connection to an file with the given properties
		props.load(new FileInputStream("flightdata.properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		
		myConn = DriverManager.getConnection(dburl, user, password);
		
		System.out.println("DB connection successful to:" + dburl);
		
	}
	
	// Use the object theGuest for inserting information into the database
	public void addGuest(Guest theGuest) throws Exception {
		PreparedStatement myStmt = null;

		try {
			
			//uses a placeholder and protects against SQL injection. Uses the column names firstname, lastname, etc
			myStmt = myConn.prepareStatement("insert into passengers"
					+ " (firstname, lastname, airline, flightnr, rownr, seatposition)"
					+ " values (?, ?, ?, ?, ?, ?)");
			
			// value is set. 1 = first placeholder, 2 = second placeholder, etc.
			myStmt.setString(1, theGuest.getFirstname());
			myStmt.setString(2, theGuest.getLastname());
			myStmt.setString(3, theGuest.getAirline());
			myStmt.setInt(4, theGuest.getFlightnr());
			myStmt.setInt(5, theGuest.getRownr());
			myStmt.setString(6, theGuest.getSeatposition());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {

		}
		
	}
	
	/**
	 * returns a list of all the guests, ordered by lastname
	 * @throws Exception
	 */
	public List<Guest> getAllGuests() throws Exception {
		
		//empty list of guests
		List<Guest> list = new ArrayList<>(); 						
		
		//null = placeholder
		Statement myStmt = null; 
		
		// null = placeholder
		ResultSet myRs = null; 										
		
		try {
			
			//creates an object for sending SQL statements to the database
			myStmt = myConn.createStatement(); 					   
			
			 //executes the given SQL statement
			myRs = myStmt.executeQuery("select * from passengers order by lastname");
			
			// for each element in the ResultSet
			while (myRs.next()) { 				
				// Takes the result set and creates a guest object and returns it
				Guest tempGuest = convertRowToGuest(myRs); 	
				// After the guest is created it adds the guest to the list
				list.add(tempGuest); 					 		   
			}

			//returns the list after objects are added
			return list; 
		}
		finally {
			//closes the ResultSet and the Statement
			close(myStmt, myRs); 									
		}
	}
	
	/**
	 * search for guests by lastname
	 * @param lastname
	 * @return
	 * @throws Exception
	 */
	public List<Guest> searchGuests(String lastname) throws Exception {
		
		//empty list of guests
		List<Guest> list = new ArrayList<>(); 						 

		//null = placeholder
		PreparedStatement myStmt = null;	
		
		// null = placeholder
		ResultSet myRs = null;										 

		try {
			
			//whatever lastname the user writes. appended with the "%", so it can use the like command.
			lastname += "%";										 

			 //uses a placeholder and protects against SQL injection
			myStmt = myConn.prepareStatement("select * from passengers where lastname like ? order by lastname");
			
			// value is set
			myStmt.setString(1, lastname); 							
			
			//execute the given SQL statement
			myRs = myStmt.executeQuery(); 							
			
			// for each element in the ResultSet
			while (myRs.next()) { 	
				
				//Takes the result set and creates a guest object and returns it
				Guest tempGuest = convertRowToGuest(myRs);	
				
				//After the guest is created it adds the guest to the list
				list.add(tempGuest);								
			}
			
			//returns the list after objects are added
			return list;											 
		}
		finally {
			
			//closes the ResultSet and the Statement
			close(myStmt, myRs);									
		}
	}
	
	/**
	 * Takes the result set and creates a guest object and returns it
	 * @param myRs
	 * @return
	 * @throws SQLException
	 */
	private Guest convertRowToGuest(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("id");
		String firstname = myRs.getString("firstname");
		String lastname = myRs.getString("lastname");
		String airline = myRs.getString("airline");
		int flightnr = myRs.getInt("flightnr");
		int rownr = myRs.getInt("rownr");
		String seatposition = myRs.getString("seatposition");
		
		Guest tempGuest = new Guest(id, firstname, lastname, airline, flightnr, rownr, seatposition);
		
		return tempGuest;
	}

	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}

	public static void main(String[] args) throws Exception {
		
		GuestDAO dao = new GuestDAO();
	}
}
