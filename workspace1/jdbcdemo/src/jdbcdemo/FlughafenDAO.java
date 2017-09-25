package jdbcdemo;

import java.util.*;
import java.sql.*;
import java.io.*;
	


/**
 * 
 * @author dzenan
 *
 */

public class FlughafenDAO {

	private Connection myConn;
	
	public FlughafenDAO() throws Exception {
		
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/flightdata", "root", "Butz");
	}
	
	public List<Flughaefen> getAllFlughaefen() throws Exception {
		List <Flughaefen> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from airports");
			
			while (myRs.next()) {
				Flughaefen tempFlughaefen = convertRowToFlughaefen(myRs);
				list.add(tempFlughaefen);
			}
			
			return list;
		}
		finally {	
			close(myStmt, myRs);
		}
	}
	
	public List<Flughaefen> searchFlughaefen(String city) throws Exception {
		List<Flughaefen> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			city += "%";
			myStmt = myConn.prepareStatement("select * from airports where city like ?");
			
			myStmt.setString(1, city);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Flughaefen tempFlughaefen = convertRowToFlughaefen(myRs);
				list.add(tempFlughaefen);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	} 
	
	private Flughaefen convertRowToFlughaefen(ResultSet myRs) throws SQLException {
		
		String airportcode = myRs.getString("airportcode");
		String name = myRs.getString("name");
		String country = myRs.getString("country");
		String city = myRs.getString("city");
		
		Flughaefen tempFlughaefen = new Flughaefen(airportcode, name, country, city);
		
		return tempFlughaefen;
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
		
		FlughafenDAO dao = new FlughafenDAO();
		System.out.println(dao.searchFlughaefen("AAT"));

	}
}
