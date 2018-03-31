/**
 * This is a POJO to represent a State. It has the attributes of location (lat/long) and 
 * the name of the state (Alabama, Arkansas, etc...).
 * 
 * @author sgb
 *
 */
public class State {
	private String name;
	private double lat;
	private double lng;
	
	/**
	 * The constructor takes in the name and lat/lng
	 * @param name
	 * @param lat
	 * @param lng
	 */
	public State(String name, double lat, double lng) {
		this.name = name;
		this.lat = lat;
		this.lng = lng;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * @param lat the lat to set
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * @return the lng
	 */
	public double getLng() {
		return lng;
	}

	/**
	 * @param lng the lng to set
	 */
	public void setLng(double lng) {
		this.lng = lng;
	}
	


}
