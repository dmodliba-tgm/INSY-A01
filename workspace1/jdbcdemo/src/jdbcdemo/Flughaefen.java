package jdbcdemo;



/**
 * 
 * @author dzenan
 *
 */
public class Flughaefen {
	
	private String airportcode;
	private String name;
	private String country;
	private String city;
	
	public Flughaefen(String airportcode, String name, String country, String city) {
		super();
		this.airportcode = airportcode;
		this.name = name;
		this.country = country;
		this.city = city;
	}

	public String getAirportcode() {
		return airportcode;
	}

	public void setAirportcode(String airportcode) {
		this.airportcode = airportcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return String
				.format("Flughaefen [airportcode=%s, name=%s, country=%s, city=%s]", airportcode, name, country, city);
	}
	

	
}