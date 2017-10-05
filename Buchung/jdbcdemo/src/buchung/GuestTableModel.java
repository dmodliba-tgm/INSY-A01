package buchung;

import java.util.List;

import javax.swing.table.AbstractTableModel;


class GuestTableModel extends AbstractTableModel {
	
	// constants that refer to these positions of the column names (will be used for getting the value)
	private static final int FIRSTNAME_COL= 0;
	private static final int LASTNAME_COL = 1;
	private static final int AIRLINE_COL = 2;
	private static final int FLIGHTNR_COL = 3;
	private static final int ROWNR_COL = 4;
	private static final int SEATPOSITION_COL = 5;

	 // define column names
	private String[] columnNames = { "Firstname", "Lastname", "Airline", "Flightnr", "Rownr", "Seatposition"};
	
	private List<Guest> guests;

	// reference of guests. Based on the constructor, when they create the model
	public GuestTableModel(List<Guest> theGuests) {
		guests = theGuests;
	}

	// swing J Table will ask how many colums are there.
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	// swing J Table will ask how many rows are there.
	@Override
	public int getRowCount() {
		return guests.size();
	}

	// get a column name for a specific column to index and semi column names array and returns that information
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	// get the actual data value or a given row and column
	@Override
	public Object getValueAt(int row, int col) {

		Guest tempGuest = guests.get(row); // give me the temporary guest that is needed

		// now it simply find the appropriate data element
		switch (col) {
		case FIRSTNAME_COL:
			return tempGuest.getFirstname();		//lastname column returns lastname of guest
		case LASTNAME_COL:
			return tempGuest.getLastname();			//firstname column returns firstname of guest
		case AIRLINE_COL:
			return tempGuest.getAirline();			//airline column returns airline of guest
		case FLIGHTNR_COL:
			return tempGuest.getFlightnr();			//flightnr column returns flightnr of guest
		case ROWNR_COL:
			return tempGuest.getRownr();			//rownr column returns rownr of guest
		case SEATPOSITION_COL:
			return tempGuest.getSeatposition();		//lastname seatposition returns seatposition of guest
		default:
			return tempGuest.getLastname();			// if no data is given it simply returns last name
		}
	}

	// is it a String, double or an int
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
