package jdbcdemo;

/**
 * Basic Java class for guest. Getters and Setters for params of guest.
 *
 */
public class Guest {

	private int id;
	private String firstname;
	private String lastname;
	private String airline;
	private int flightnr;
	private int rownr;
	private String seatposition;
	
	public Guest(String firstname, String lastname, String airline, int flightnr, int rownr, String seatposition) {
		this(2001, firstname, lastname, airline, flightnr, rownr, seatposition); 
	}
	
	public Guest(int id, String firstname, String lastname, String airline, int flightnr, int rownr,
			String seatposition) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.airline = airline;
		this.flightnr = flightnr;
		this.rownr = rownr;
		this.seatposition = seatposition;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public int getFlightnr() {
		return flightnr;
	}

	public void setFlightnr(int flightnr) {
		this.flightnr = flightnr;
	}

	public int getRownr() {
		return rownr;
	}

	public void setRownr(int rownr) {
		this.rownr = rownr;
	}

	public String getSeatposition() {
		return seatposition;
	}

	public void setSeatposition(String seatposition) {
		this.seatposition = seatposition;
	}
	
	@Override
	public String toString() {
		return String
				.format("Guest [id=%s, firstname=%s, lastname=%s, airline=%s, flightnr=%s, rownr=%s, seatposition=%s]", 
						id, firstname, lastname, airline, flightnr, rownr, seatposition);
	}
	
}
